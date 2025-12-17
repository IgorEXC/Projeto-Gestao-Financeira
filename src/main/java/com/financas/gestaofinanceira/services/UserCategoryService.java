package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.dto.response.CategoriesWithExpensesByUserResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoriesWithExpensesResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesWithUserCategoryResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserCategoriesByUserIdResponseDTO;
import com.financas.gestaofinanceira.exceptions.BusinessException;
import com.financas.gestaofinanceira.repositories.UserCategoryRepository;
import com.financas.gestaofinanceira.utils.FuncaoUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserCategoryService implements FuncaoUtil {

    private final UserCategoryRepository repository;

    public List<UserCategoriesByUserIdResponseDTO> getUserCategoriesByUserId(Long userId) {
        return repository.getAllUserCategoriesByUserId(userId);
    }

    public CategoriesWithExpensesByUserResponseDTO getCategoriesWithExpenseByUserId(Long userId){
        List<UserCategoriesByUserIdResponseDTO> queryResult = repository.getAllUserCategoriesByUserId(userId);
        Map<String, List<UserCategoriesByUserIdResponseDTO>> response = queryResult.stream()
                .collect(Collectors.groupingBy(
                        UserCategoriesByUserIdResponseDTO::getUserName,
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
            @NotNull Map.Entry<String, List<UserCategoriesByUserIdResponseDTO>> entry
    ){
        var firstResult = entry.getValue().getFirst();
        return CategoriesWithExpensesByUserResponseDTO.builder()
                .name(firstResult.getUserName())
                .cpf(firstResult.getCpf())
                .categories(montaDadosCategory(entry.getValue()))
                .build();
    }

    private List<CategoriesWithExpensesResponseDTO> montaDadosCategory(@NotNull List<UserCategoriesByUserIdResponseDTO> lista){
        var result = lista.stream()
                .collect(
                        Collectors.groupingBy(UserCategoriesByUserIdResponseDTO::getCategoryName, LinkedHashMap::new, Collectors.toList()
                        )).entrySet().stream().map(this::buildUserCategory).toList();
        int index = 0;
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(CategoriesWithExpensesResponseDTO dto : result){
            totalPrice = totalPrice.add(getSafeValue(dto.getExpenses().get(index++).getPrice()));
            if(index > result.size()){
                dto.setTotalPrice(totalPrice);
                break;
            }
        }
        return result;
    }

    private CategoriesWithExpensesResponseDTO buildUserCategory(@NotNull Map.Entry<String, List<UserCategoriesByUserIdResponseDTO>> entry) {
        var firstResult = entry.getValue().getFirst();
        return CategoriesWithExpensesResponseDTO.builder()
                .categoryName(firstResult.getCategoryName())
                .qtdExpenses(entry.getValue().size())
                .expenses(montaDadosExpenses(entry.getValue()))
                .build();
    }

    private List<ExpensesWithUserCategoryResponseDTO> montaDadosExpenses(@NotNull List<UserCategoriesByUserIdResponseDTO> lista){
        return lista.stream()
                .map(this::buildExpense)
                .toList();
    }

    private ExpensesWithUserCategoryResponseDTO buildExpense(@NotNull UserCategoriesByUserIdResponseDTO dto){
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
