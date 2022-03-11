package com.backend.tempatusaha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Set<String> role;
}
