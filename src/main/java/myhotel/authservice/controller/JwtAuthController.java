package myhotel.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import myhotel.authservice.models.AuthenticationRequest;

@RequestMapping("/authenticate")
public interface JwtAuthController {

	@PostMapping
	public ResponseEntity<?> generateJwtToket(@RequestBody AuthenticationRequest authenticationRequest) throws Exception;
}
