package com.opuslex.service;

import com.opuslex.domain.user.User;
import com.opuslex.domain.user.UserRepository;
import com.opuslex.dto.UserDto;
import com.opuslex.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToDto(user);
    }

    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        // The domain entity doesn't have getters, assuming this will be filled out once domain is completed.
        // Doing this as a convention placeholder.
        // dto.setId(user.getId());
        return dto;
    }
}
