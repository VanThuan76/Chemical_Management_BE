package com.chemical.services;

import com.chemical.dto.request.UserCreateRequestDTO;
import com.chemical.dto.request.UserUpdateRequestDTO;
import com.chemical.dto.response.UserResponseDTO;
import com.chemical.entity.User;
import java.util.List;

public interface UserService {
    User save(UserCreateRequestDTO request);
    UserResponseDTO currentUserDetails();
    UserResponseDTO findByEmailAuth(String email);
    List<UserResponseDTO> getAllUsers();
    User update(Long userId, UserUpdateRequestDTO request);
    void delete(Long userId);
}
