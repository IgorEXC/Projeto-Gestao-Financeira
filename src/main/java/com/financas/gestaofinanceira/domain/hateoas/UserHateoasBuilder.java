package com.financas.gestaofinanceira.domain.hateoas;

import com.financas.gestaofinanceira.domain.dto.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.UserResponseDTO;
import com.financas.gestaofinanceira.resources.UserResource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoasBuilder {

    public void addHateoasLinksSingle(UserResponseDTO dtoResponse, UserRequestDTO dtoRequest) {
        dtoResponse.add(linkTo(methodOn(UserResource.class).findById(dtoResponse.getId()))
                .withSelfRel().withType("GET"));
        dtoResponse.add(linkTo(methodOn(UserResource.class).createUser(dtoRequest)).withRel("Create")
                .withType("POST"));
        dtoResponse.add(linkTo(methodOn(UserResource.class).updateUser(dtoResponse.getId(), dtoRequest))
                .withRel("Update").withType("PUT"));
    }

    public void addHateoasLinksList(UserResponseDTO dtoResponse) {
        dtoResponse.add(linkTo(methodOn(UserResource.class).findById(dtoResponse.getId()))
                .withSelfRel().withType("GET"));
        dtoResponse.add(linkTo(methodOn(UserResource.class).updateUser(dtoResponse.getId(), null))
                .withRel("Update").withType("PUT"));
    }
}
