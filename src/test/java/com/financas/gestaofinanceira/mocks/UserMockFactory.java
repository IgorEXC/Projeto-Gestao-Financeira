package com.financas.gestaofinanceira.mocks;


import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class UserMockFactory {

    public static User createUserMock(){
       return User.builder()
                .id(1L)
                .name("John Stuart")
                .email("john@gmail.com")
                .password("<PASSWORD>")
                .monthlyIncome(10000.00)
                .username("johnstuart")
                .cpf("98562391087")
                .birthdate(LocalDate.of(1990, 2, 13))
                .build();
    }

    public static List<User> createUserListMock(){
        return Arrays.asList(
                createUserMock(),
                User.builder()
                        .id(1L)
                        .name("Angeline Jowl")
                        .email("angeline@gmail.com")
                        .password("<Security>")
                        .monthlyIncome(80000.00)
                        .username("jowl_jowllie")
                        .cpf("91345089387")
                        .birthdate(LocalDate.of(1976, 5, 29))
                        .build());
    }

    public static UserRequestDTO createUserRequestDTOMock(){
        return UserRequestDTO.builder()
                .name("John Stuart")
                .email("john@gmail.com")
                .password("<PASSWORD>")
                .monthlyIncome(10000.00)
                .username("johnstuart")
                .cpf("98562391087")
                .birthdate(LocalDate.of(1990, 2, 13))
                .build();
    }

    public static List<UserRequestDTO> createUserRequestDtoListMock(){
        return Arrays.asList(
                createUserRequestDTOMock(),
                UserRequestDTO.builder()
                        .name("Maria Angelica")
                        .email("marimar@gmail.com")
                        .password("<Security_1>")
                        .monthlyIncome(89000.10)
                        .username("mariul")
                        .cpf("11234456387")
                        .birthdate(LocalDate.of(1999, 12, 31))
                        .build());
    }

    public static UserResponseDTO createUserResponseDTOMock(){
        return UserResponseDTO.builder()
                .id(1L)
                .name("John Stuart")
                .email("john@gmail.com")
                .monthlyIncome(10000.00)
                .username("johnstuart")
                .cpf("98562391087")
                .birthdate(LocalDate.of(1990, 2, 13))
                .build();
    }

    public static List<UserResponseDTO> createUserResponseDtoListMock(){
        return Arrays.asList(
                createUserResponseDTOMock(),
                UserResponseDTO.builder()
                        .name("Maria Angelica")
                        .email("marimar@gmail.com")
                        .monthlyIncome(89000.10)
                        .username("mariul")
                        .cpf("11234456387")
                        .birthdate(LocalDate.of(1999, 12, 31))
                        .build());
    }
}
