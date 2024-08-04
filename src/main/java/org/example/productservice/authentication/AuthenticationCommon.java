package org.example.productservice.authentication;

import org.example.productservice.dtos.FakeStoreDtos;
import org.example.productservice.dtos.UserDtos;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.logging.Logger;

@Component
public class AuthenticationCommon {

    private RestTemplate restTemplate;

    public AuthenticationCommon(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDtos validate(String token) {
        String baseUrl = "http://localhost:9000/user/validate";  // Ensure the URL includes the protocol

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserDtos userDtos = new UserDtos();
        HttpEntity<UserDtos> requestEntity = new HttpEntity<>(userDtos, headers);

        try {
            ResponseEntity<UserDtos> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.POST,
                    requestEntity,
                    UserDtos.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                userDtos = response.getBody();
            } else {
                // Handle non-OK status codes or null body appropriately
                Logger.getAnonymousLogger().info("Failed to validate token: " + response.getStatusCode());

            }
        } catch (HttpClientErrorException e) {
            // Handle HTTP errors
            Logger.getAnonymousLogger().info("HTTP error during token validation: " + e.getStatusCode());
            e.printStackTrace();
        } catch (ResourceAccessException e) {
            // Handle connection errors
            Logger.getAnonymousLogger().info("ResourceAccessException during token validation: " + e.getCause());

            e.printStackTrace();
        } catch (Exception e) {
            // Handle general errors
            Logger.getAnonymousLogger().info("Exception during token validation: " + e);
            e.printStackTrace();
        }

        Logger.getAnonymousLogger().info("user details: " + userDtos.getEmail());

        return userDtos;
    }
}
