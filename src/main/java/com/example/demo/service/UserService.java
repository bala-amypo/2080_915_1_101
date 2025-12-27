// package com.example.demo.service;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.UserRegisterDto;
// import com.example.demo.model.User;

// public interface UserService {
//     User registerUser(UserRegisterDto dto);
//     AuthResponse login(AuthRequest request);
// }

package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.User;

public interface UserService {
    // Registers a new user
    User registerUser(UserRegisterDto dto);

    // Authenticates a user and returns a JWT token
    AuthResponse login(AuthRequest request);
}