package com.financas.gestaofinanceira.domain.hateoas;

import com.financas.gestaofinanceira.controller.UserController;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoasBuilder {

    public void addHateoasLinksSingle(UserResponseDTO dtoResponse, UserRequestDTO dtoRequest) {
        dtoResponse.add(linkTo(methodOn(UserController.class).findById(dtoResponse.getId()))
                .withSelfRel().withType("GET"));
        dtoResponse.add(linkTo(methodOn(UserController.class).createUser(dtoRequest)).withRel("Create")
                .withType("POST"));
        dtoResponse.add(linkTo(methodOn(UserController.class).updateUser(dtoResponse.getId(), dtoRequest))
                .withRel("Update").withType("PUT"));
    }

    public void addHateoasLinksList(UserResponseDTO dtoResponse) {
        dtoResponse.add(linkTo(methodOn(UserController.class).findById(dtoResponse.getId()))
                .withSelfRel().withType("GET"));
        dtoResponse.add(linkTo(methodOn(UserController.class).updateUser(dtoResponse.getId(), null))
                .withRel("Update").withType("PUT"));
    }
}
