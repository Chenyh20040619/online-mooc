package com.chenyh.commonutils;

import lombok.Data;

import java.util.List;

@Data
public class JwtEntity {
	private String userId;
	private String username;
	private List<String> roles;

	private String nickname;


	public JwtEntity() {
	}
}
