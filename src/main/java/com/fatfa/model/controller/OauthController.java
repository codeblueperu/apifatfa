package com.fatfa.model.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class OauthController {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/recoverpassword")
	public ResponseEntity<?> getSaludo() {

		HashMap<String, Object> map = new HashMap<>();
		map.put("saludo", encoder.encode("admin"));

		return ResponseEntity.ok(map);
	}

}
