package com.taller.proyecto.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.proyecto.model.ResetToken;
import com.taller.proyecto.security.JwtRequest;
import com.taller.proyecto.security.JwtResponse;
import com.taller.proyecto.security.JwtTokenUtil;
import com.taller.proyecto.service.ILoginService;
import com.taller.proyecto.service.IResetTokenService;
import com.taller.proyecto.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin
public class UserController {

	private static   Logger log =  LogManager.getLogger("logins-all");
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsuarioServiceImpl userDetailsService;
	
	@Autowired
	private ILoginService service;
	
	@Autowired	
	private IResetTokenService tokenService;
	
	@Autowired
	private PasswordEncoder bcrypt;
	
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		if (userDetails.getAuthorities().isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado.");
		}
		userDetails.getAuthorities().forEach(rol -> {
			
			if(rol == null ) {
				throw new UsernameNotFoundException("Error al iniciar sesion.");
			}else {
				if(!"ADMIN".contains(rol.getAuthority()) ) {
					throw new UsernameNotFoundException("Usuario no encontrado.");
				}	
			}			
		});
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		log.debug("Logueo exitoso");
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/provider")
	public ResponseEntity<?> createAuthenticationTokenProvider(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		if (userDetails.getAuthorities().isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado.");
		}
		userDetails.getAuthorities().forEach(rol -> {
			
			if(rol == null ) {
				throw new UsernameNotFoundException("Error al iniciar sesion.");
			}else {
				if(!"PROVIDER".contains(rol.getAuthority()) ) {
					throw new UsernameNotFoundException("Usuario no encontrado.");
				}	
			}			
		});
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		log.debug("Logueo exitoso");
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	
	@PostMapping(value = "/client")
	public ResponseEntity<?> createAuthenticationTokenClient(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		if (userDetails.getAuthorities().isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado.");
		}
		userDetails.getAuthorities().forEach(rol -> {
			
			if(rol == null ) {
				throw new UsernameNotFoundException("Error al iniciar sesion.");
			}else {
				if(!"CLIENT".contains(rol.getAuthority()) ) {
					throw new UsernameNotFoundException("Usuario no encontrado.");
				}	
			}			
		});
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		log.debug("Logueo exitoso");
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}
	
	
	public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token) {
		int rpta = 0;
		try {
			if (token != null && !token.isEmpty()) {
				ResetToken rt = tokenService.findByToken(token);
				if (rt != null && rt.getId() > 0) {
					if (!rt.estaExpirado()) {
						rpta = 1;
					}
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@PostMapping(value = "/restablecer/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> restablecerClave(@PathVariable("token") String token, @RequestBody String clave) {		
		try {
			ResetToken rt = tokenService.findByToken(token);			
			service.cambiarClave(clave, rt.getUser().getEmail());
			tokenService.eliminar(rt);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
		
	@GetMapping(value = "/generar/{token}")
	public ResponseEntity<String> generar(@PathVariable("token") String token) {
		bcrypt = 	new BCryptPasswordEncoder();
		return new ResponseEntity<String>(bcrypt.encode(token), HttpStatus.OK);
	}
}
