package com.example.userservice.service;

import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterRequest;
import com.example.userservice.dto.ResponseApi;
import com.example.userservice.model.AdminInfo;
import com.example.userservice.model.PatientDoctor;
import com.example.userservice.model.PatientInfo;
import com.example.userservice.model.User;
import com.example.userservice.repository.AdminInfoRepository;
import com.example.userservice.repository.PatientDoctorRepository;
import com.example.userservice.repository.PatientInfoRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AdminInfoRepository adminInfoRepository;
    private final PatientInfoRepository patientInfoRepository;
    private final PatientDoctorRepository patientDoctorRepository;
    private final WalletService walletService;

    public ResponseEntity<ResponseApi<Void>> register(RegisterRequest request) {
        String role = request.getRole();
        if (role == null || (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("patient") && !role.equalsIgnoreCase("doctor"))) {
            return ResponseEntity.badRequest().body(
                    new ResponseApi<>(false, "Invalid role. Allowed roles: admin, patient, doctor.", null)
            );
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(
                    new ResponseApi<>(false, "Username already exists", null)
            );
        }

        // Auto-generate wallet address
        String walletAddress;
        try {
            walletAddress = walletService.generateWallet(request.getUsername());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ResponseApi<>(false, "Failed to generate wallet: " + e.getMessage(), null)
            );
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .walletAddress(walletAddress)
                .build();
        userRepository.save(user);

        switch (role.toLowerCase()) {
            case "admin" -> adminInfoRepository.save(AdminInfo.builder()
                    .userId(user.getId())
                    .department(request.getDepartment())
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .hospital(request.getHospital())
                    .address(request.getAddress())
                    .build());
            case "patient" -> patientInfoRepository.save(PatientInfo.builder()
                    .userId(user.getId())
                    .medicalHistory(request.getMedicalHistory())
                    .address(request.getAddress())
                    .gender(request.getGender())
                    .phone(request.getPhone())
                    .dateOfBirth(request.getDateOfBirth())
                    .emergencyContactName(request.getEmergencyContactName())
                    .emergencyContactPhone(request.getEmergencyContactPhone())
                    .name(request.getName())
                    .bloodGroup(request.getBloodGroup())
                    .build());
            case "doctor" -> patientDoctorRepository.save(PatientDoctor.builder()
                    .userId(user.getId())
                    .specialization(request.getSpecialization())
                    .licenseNumber(request.getLicenseNumber())
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .hospital(request.getHospital())
                    .address(request.getAddress())
                    .gender(request.getGender())
                    .build());
        }

        return ResponseEntity.ok(new ResponseApi<>(true, "User registered successfully", null));
    }

    public ResponseEntity<ResponseApi<String>> login(LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body(
                    new ResponseApi<>(false, "Username and password are required", null)
            );
        }
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> ResponseEntity.ok(
                        new ResponseApi<>(true, "Login successful", jwtUtil.generateToken(user.getUsername()))
                ))
                .orElse(ResponseEntity.status(401).body(
                        new ResponseApi<>(false, "Invalid credentials", null)
                ));
    }
}