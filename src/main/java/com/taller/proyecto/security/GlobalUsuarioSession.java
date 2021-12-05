package com.taller.proyecto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalUsuarioSession {
	private Integer userId;
	private String email;
	private String name;
	private String userType;
	private String platform;
	private String authorization;
	private String accessToken;
	private String ipDevice;
	private String ruc;
}
