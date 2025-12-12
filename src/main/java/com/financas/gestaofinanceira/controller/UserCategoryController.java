package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserCategoriesByUserIdResponseDTO;
import com.financas.gestaofinanceira.services.UserCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/category-users")
public class UserCategoryController {

    private final UserCategoryService userCategoryService;

    @GETMultiFormat("/{id}")
    public ResponseEntity<List<ExpensesByUserResponseDTO>> getAllUserCategories(@PathVariable Long id) {
        return ResponseEntity.ok().body(userCategoryService.getCategoriesWithExpenseByUserId(id));
    }

    @GETMultiFormat("/teste/{id}")
    public ResponseEntity<List<UserCategoriesByUserIdResponseDTO>> getTeste(@PathVariable Long id) {
        return ResponseEntity.ok().body(userCategoryService.getUserCategoriesByUserId(id));
    }
}
