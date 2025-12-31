package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.configuration.security.CurrentUserLogged;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.UserCategory;
import com.financas.gestaofinanceira.domain.UserCategory_;
import com.financas.gestaofinanceira.domain.dto.request.CreateCategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.*;
import com.financas.gestaofinanceira.exceptions.BusinessException;
import com.financas.gestaofinanceira.repositories.UserCategoryRepository;
import com.financas.gestaofinanceira.repositories.utils.BaseSpecs;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserCategoryService implements BaseSpecs<UserCategory> {

    private final UserCategoryRepository repository;
    private final UserService userService;

    @Transactional
    public UserCategory createUserCategory(CreateCategoryRequestDTO request) {
        Long userId = CurrentUserLogged.getCurrentUserId();
        User user = userService.findById(userId);
        if (repository.exists(byEquals(UserCategory_.name, request.categoryName())
                .and(byEquals(UserCategory_.user, user)))) {
            throw new BusinessException("Category exists!");
        }
        UserCategory newUserCategory = UserCategory.builder()
                .name(request.categoryName())
                .user(user)
                .build();
       return repository.save(newUserCategory);
    }

    public ExpensesByUserCategoryResponseDTO findCategoryIdByCategoryName(String categoryName) {
        Long categoryId = repository.findUserCategoryIdByCategoryName(categoryName);
        return expensesByUserCategoryResponseDTO(categoryId);
    }

    private ExpensesByUserCategoryResponseDTO expensesByUserCategoryResponseDTO(Long categoryId) {
        Long userId = CurrentUserLogged.getCurrentUserId();
        if (!repository.existsById(categoryId) && ObjectUtils.isEmpty(userService.findById(userId))) {
            throw new BusinessException("User or Category not found!");
        }
        List<UserCategoriesByUserIdProjection> result = repository.getExpensesByUserCategory(userId, categoryId);
        return result.stream().map(projection -> {
            ExpensesByUserCategoryResponseDTO dto = new ExpensesByUserCategoryResponseDTO();
            dto.setCategoryName(projection.getCategoryName());
            dto.setExpenses(result.stream().map(this::buildExpense).toList());
            return dto;
        }).findFirst().orElseThrow(() -> new BusinessException("Category not found!"));
    }

    public CategoriesWithExpensesByUserResponseDTO getCategoriesWithExpenseByUserId() {
        List<UserCategoriesByUserIdProjection> queryResult = repository.getAllUserCategoriesByUserId(CurrentUserLogged.getCurrentUserId());
        Map<String, List<UserCategoriesByUserIdProjection>> response = queryResult.stream()
                .collect(Collectors.groupingBy(
                        UserCategoriesByUserIdProjection::getUserName,
                        LinkedHashMap::new,
                        Collectors.toList())
                );
        return response
                .entrySet()
                .stream()
                .map(this::buildUser)
                .findFirst()
                .orElseThrow(() -> new BusinessException("User not found"));
    }

    private CategoriesWithExpensesByUserResponseDTO buildUser(
            @NotNull Map.Entry<String, List<UserCategoriesByUserIdProjection>> entry
    ) {
        var firstResult = entry.getValue().getFirst();
        return CategoriesWithExpensesByUserResponseDTO.builder()
                .name(firstResult.getUserName())
                .cpf(firstResult.getCpf())
                .categories(buildDataCategory(entry.getValue()))
                .build();
    }

    private List<CategoriesWithExpensesResponseDTO> buildDataCategory(@NotNull List<UserCategoriesByUserIdProjection> lista) {
        var result = lista.stream()
                .collect(
                        Collectors.groupingBy(UserCategoriesByUserIdProjection::getCategoryName, LinkedHashMap::new, Collectors.toList()
                        )).entrySet().stream().map(this::buildUserCategory).toList();
        for (CategoriesWithExpensesResponseDTO dto : result) {
            var total = buildTotalExpensesByCategory(dto.getExpenses());
            dto.setTotalPrice(total);
        }
        return result;
    }

    private BigDecimal buildTotalExpensesByCategory(@NotNull List<ExpensesWithUserCategoryResponseDTO> result) {
        return result.stream()
                .map(ExpensesWithUserCategoryResponseDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private CategoriesWithExpensesResponseDTO buildUserCategory(@NotNull Map.Entry<String, List<UserCategoriesByUserIdProjection>> entry) {
        var firstResult = entry.getValue().getFirst();
        return CategoriesWithExpensesResponseDTO.builder()
                .categoryName(firstResult.getCategoryName())
                .qtdExpenses(entry.getValue().size())
                .expenses(buildDataExpenses(entry.getValue()))
                .build();
    }

    private List<ExpensesWithUserCategoryResponseDTO> buildDataExpenses(@NotNull List<UserCategoriesByUserIdProjection> lista) {
        return lista.stream()
                .map(this::buildExpense)
                .toList();
    }

    private ExpensesWithUserCategoryResponseDTO buildExpense(@NotNull UserCategoriesByUserIdProjection dto) {
        return ExpensesWithUserCategoryResponseDTO.builder()
                .name(dto.getExpense())
                .description(
                        dto.getDescription() != null && !dto.getDescription().isEmpty()
                                ? dto.getDescription() : "Sem descrição para a despesa.")
                .price(dto.getExpensePrice())
                .dateOfPurchase(dto.getDateOfPurchase())
                .necessaryExpense(dto.getNecessaryExpense())
                .build();
    }
}
