package com.financas.gestaofinanceira.services;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

//   UserMockFactory mockFactory;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserMapper mapper;
//
//    @Mock
//    private UserHateoasBuilder hateoasBuilder;
//
//    @BeforeEach
//    void setUp() {
//        mockFactory = new UserMockFactory();
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void findById() {
//        Long userId = 1L;
//        User userMock = UserMockFactory.createUserMock();
//        UserResponseDTO responseMock = UserMockFactory.createUserResponseDTOMock();
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(userMock));
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(userMock));
//        when(mapper.entityToResponse(userMock)).thenReturn(responseMock);
//
//        UserResponseDTO responseDTO = userService.findById(userId);
//
//        assertNotNull(responseDTO);
//        assertEquals(userMock.getId(), responseDTO.getId());
//        assertEquals(userMock.getName(), responseDTO.getName());
//        assertEquals(userMock.getEmail(), responseDTO.getEmail());
//
//        Mockito.verify(userRepository).findById(userId);
//        Mockito.verify(mapper).entityToResponse(userMock);
//    }
//
//    @Test
//    void insert() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void findAllPerPage() {
//    }
}