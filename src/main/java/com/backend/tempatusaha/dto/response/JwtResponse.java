package com.backend.tempatusaha.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type;
	private String refreshToken;
	private Long id;
	private String username;
	private String email;
	private List<String> role;

}
