package com.CodingShuttle.SecurityApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponseDto {

	private Long id;
	private String accessToken;
	private String refreshToken;
}
