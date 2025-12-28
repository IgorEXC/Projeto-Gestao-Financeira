//package com.financas.gestaofinanceira.domain.hateoas;
//
//import com.financas.gestaofinanceira.configuration.security.CurrentUserLogged;
//import com.financas.gestaofinanceira.controller.UserController;
//import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
//import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;
//import org.springframework.stereotype.Component;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//@Component
//public class UserHateoasBuilder {
//    private final Long USER_ID = CurrentUserLogged.getCurrentUserId();
//
//    public void addHateoasLinksSingle(UserResponseDTO dtoResponse, UserRequestDTO dtoRequest) {
//        dtoResponse.add(linkTo(methodOn(UserController.class).findById(USER_ID))
//                .withSelfRel().withType("GET"));
//        dtoResponse.add(linkTo(methodOn(UserController.class).createUser(dtoRequest)).withRel("Create")
//                .withType("POST"));
//        dtoResponse.add(linkTo(methodOn(UserController.class).updateUser(dtoRequest))
//                .withRel("Update").withType("PUT"));
//    }
//
//    public void addHateoasLinksList(UserResponseDTO dtoResponse) {
//        dtoResponse.add(linkTo(methodOn(UserController.class).findById(USER_ID))
//                .withSelfRel().withType("GET"));
//        dtoResponse.add(linkTo(methodOn(UserController.class).updateUser(null))
//                .withRel("Update").withType("PUT"));
//    }
//}
