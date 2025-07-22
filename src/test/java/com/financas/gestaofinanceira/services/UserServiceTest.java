package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.dto.UserResponseDTO;
import com.financas.gestaofinanceira.domain.hateoas.UserHateoasBuilder;
import com.financas.gestaofinanceira.domain.mapper.UserMapper;
import com.financas.gestaofinanceira.mocks.UserMockFactory;
import com.financas.gestaofinanceira.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

   UserMockFactory mockFactory;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserHateoasBuilder hateoasBuilder;

    @BeforeEach
    void setUp() {
        mockFactory = new UserMockFactory();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Long userId = 1L;
        User userMock = UserMockFactory.createUserMock();
        UserResponseDTO responseMock = UserMockFactory.createUserResponseDTOMock();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userMock));

        when(userRepository.findById(userId)).thenReturn(Optional.of(userMock));
        when(mapper.entityToResponse(userMock)).thenReturn(responseMock);

        UserResponseDTO responseDTO = userService.findById(userId);

        assertNotNull(responseDTO);
        assertEquals(userMock.getId(), responseDTO.getId());
        assertEquals(userMock.getName(), responseDTO.getName());
        assertEquals(userMock.getEmail(), responseDTO.getEmail());

        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(mapper).entityToResponse(userMock);
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void findAllPerPage() {
    }
}