package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.dto.response.ExpenseResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserCategoriesByUserIdResponseDTO;
import com.financas.gestaofinanceira.repositories.UserCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserCategoryService {

    private final UserCategoryRepository repository;

    public List<UserCategoriesByUserIdResponseDTO> getUserCategoriesByUserId(Long userId) {
        return repository.getAllUserCategoriesByUserId(userId);
    }

    //retornar categorias com as despesas de cada categoria por id do user
    //use streams para agrupar por categorias e ordenar tambem por categorias
    //crie outro dto que comportara o nome do usuario e dentro dele crie uma lista - ok
    //que recebera o resultado dessa consulta. Creio que ele sim pode ser um record - ok
    public List<ExpensesByUserResponseDTO> getCategoriesWithExpenseByUserId(Long userId){
        List<UserCategoriesByUserIdResponseDTO> queryResult = repository.getAllUserCategoriesByUserId(userId);
        Map<String, List<UserCategoriesByUserIdResponseDTO>> response = queryResult.stream()
                .collect(Collectors.groupingBy(
                        UserCategoriesByUserIdResponseDTO::getCategoryName,
                        LinkedHashMap::new,
                        Collectors.toList())
                );
        return response
                .entrySet()
                .stream()
                .map(this::buildCategoriesWithExpense)
                .toList();
    }

    private ExpensesByUserResponseDTO buildCategoriesWithExpense(
            Map.Entry<String, List<UserCategoriesByUserIdResponseDTO>> entry
    ){
        var firstResult = entry.getValue().getFirst();
        return ExpensesByUserResponseDTO.builder()
                .name(firstResult.getUserName())
                .cpf(firstResult.getCpf())
                .expenses(montaDadosExpenses(entry.getValue()))
                .build();
    }

    private List<ExpenseResponseDTO> montaDadosExpenses(List<UserCategoriesByUserIdResponseDTO> lista){
        return lista.stream()
                .map(this::buildExpense)
                .toList();
    }

    private ExpenseResponseDTO buildExpense(UserCategoriesByUserIdResponseDTO dto){
        return ExpenseResponseDTO.builder()
                .name(dto.getExpense())
                .price(dto.getExpensePrice().doubleValue())
                .dateOfPurchase(dto.getDateOfPurchase())
                .necessaryExpense(dto.getNecessaryExpense())
                .build();
    }
}
