package com.itesm.ecommerce.application.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.itesm.ecommerce.domain.model.User;
import com.itesm.ecommerce.domain.repository.UserRepository;
import com.itesm.ecommerce.infrastructure.dto.user.request.LoginRequestDTO;
import com.itesm.ecommerce.infrastructure.dto.user.request.RegisterUserRequestDTO;
import com.itesm.ecommerce.infrastructure.dto.user.response.LoginResponseDTO;
import com.itesm.ecommerce.infrastructure.dto.user.response.RegisterUserResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.UUID;

@ApplicationScoped
public class AuthService {
    @Inject UserRepository userRepository;

    @ConfigProperty(name = "firebase.api.key")
    String firebaseApiKey;

    @Transactional
    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO requestDTO){
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(requestDTO.getEmail())
                .setPassword(requestDTO.getPassword())
                .setDisplayName(requestDTO.getFirstName() + " " + requestDTO.getLastName());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            User user = requestDTO.toDomain();
            user.setUuid(UUID.randomUUID().toString());
            user.setFirebaseId(userRecord.getUid());
            userRepository.registerUser(user);

            RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO();
            responseDTO.setFirebaseId(userRecord.getUid());
            responseDTO.setUuid(user.getUuid());
            return responseDTO;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Firebase user", e);
        }
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO){
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            String json = String.format("""
                {
                  "email": "%s",
                  "password": "%s",
                  "returnSecureToken": true
                }
                """, requestDTO.getEmail(), requestDTO.getPassword());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + firebaseApiKey))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .timeout(Duration.ofSeconds(10))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() == 200) {
            JSONObject jsonObject = new JSONObject(response.body());
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setAccessToken(jsonObject.optString("idToken"));
            responseDTO.setRefreshToken(jsonObject.optString("refreshToken"));
            responseDTO.setExpiresIn(jsonObject.optString("expiresIn"));
            return responseDTO;
        } else {
            throw new RuntimeException("Login failed: " + response.body());
        }
    }
}
