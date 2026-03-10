package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.domain.dto.request.CreateCategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoriesWithExpensesByUserResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.CreateCategoryResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserCategoryResponseDTO;
import com.financas.gestaofinanceira.services.UserCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CreateCategoryResponseDTO> createUserCategory(
            @Valid @RequestBody CreateCategoryRequestDTO request){
        var userCategory = userCategoryService.createUserCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateCategoryResponseDTO(userCategory.getId(), userCategory.getName()));
    }
}
