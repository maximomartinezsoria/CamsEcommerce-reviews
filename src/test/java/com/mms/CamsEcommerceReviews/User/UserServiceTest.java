package com.mms.CamsEcommerceReviews.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void shouldCallFindAllOnce() {
        userService.findAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldCallSaveOnce() {
        User user = new User((long) 1, "John Doe", "Intro", "john@doe.com", null);
        userService.save(user);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue()).isEqualTo(user);
    }

    @Test
    void shouldCallFindByIdOnce() {
        Long id = (long) 1;
        User user = new User(id, "John Doe", "Intro", "john@doe.com", null);
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        userService.findById(id);
        ArgumentCaptor<Long> userIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository, times(1)).findById(userIdArgumentCaptor.capture());
        assertThat(userIdArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void shouldCallFindByIdOnceAndThrowException() {
        Long id = (long) 1;
        assertThatThrownBy(() -> userService.findById(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User with ID=" + id + " doesn't exist");
        ArgumentCaptor<Long> userIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository, times(1)).findById(userIdArgumentCaptor.capture());
        assertThat(userIdArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void shouldCallDeleteOnce() {
        Long id = (long) 1;
        userService.delete(id);
        ArgumentCaptor<Long> userIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository, times(1)).deleteById(userIdArgumentCaptor.capture());
        assertThat(userIdArgumentCaptor.getValue()).isEqualTo(id);
    }
}