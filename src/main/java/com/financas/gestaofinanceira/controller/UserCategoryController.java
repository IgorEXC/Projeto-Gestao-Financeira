package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.domain.dto.response.CategoriesWithExpensesByUserResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserCategoryResponseDTO;
import com.financas.gestaofinanceira.services.UserCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/category-users")
public class UserCategoryController {

    private final UserCategoryService userCategoryService;

    @GETMultiFormat
    public ResponseEntity<CategoriesWithExpensesByUserResponseDTO> getAllUserCategories() {
        return ResponseEntity.ok().body(userCategoryService.getCategoriesWithExpenseByUserId());
    }

    @GETMultiFormat(value = "/expenses-by-user-category")
    public ResponseEntity<ExpensesByUserCategoryResponseDTO> expensesByUserCategory(
            @RequestParam(name = "category_name") String categoryName){
        return ResponseEntity.ok().body(userCategoryService.findCategoryIdByCategoryName(categoryName));
    }

    //criar endpoint para criar categorias unicas
}
