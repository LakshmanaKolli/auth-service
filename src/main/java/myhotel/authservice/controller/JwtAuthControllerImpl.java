package myhotel.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import myhotel.authservice.models.AuthenticationRequest;
import myhotel.authservice.models.AuthenticationResponse;
import myhotel.authservice.services.CustomUserDetailsService;
import myhotel.authservice.util.JwtTokenProvider;

@RestController
public class JwtAuthControllerImpl implements JwtAuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	public ResponseEntity<?> generateJwtToket(AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("wrong username or password!!", e);
		}
		final UserDetails userDetails = customUserDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTokenProvider.generateToken(userDetails);
		return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
	}
}
