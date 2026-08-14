package org.ijse.nexusestate_aad.service.impl;
import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.*;
import org.ijse.nexusestate_aad.repository.*;
import org.ijse.nexusestate_aad.security.JwtUtil;
import org.ijse.nexusestate_aad.service.AuthService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @Override
    public String register(RegisterRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) return "Error: Username taken!";
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<Role> roles = new HashSet<>();
        dto.getRoles().forEach(r -> {
            Role role = roleRepository.findByName(r).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(role);
        });
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully!";
    }
    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail());
        String jwt = jwtUtil.generateToken(userDTO);
        String role = auth.getAuthorities().iterator().next().getAuthority();
        return new AuthResponseDTO(jwt, dto.getUsername(), role);
    }
}