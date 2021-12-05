package com.taller.proyecto.security;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.taller.proyecto.model.Usuario;
import com.taller.proyecto.service.IUsuarioService;
import com.taller.proyecto.service.impl.UserServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserServiceImpl jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
System.out.print(request.toString());
System.err.println("****************");
System.err.println(request.getHeaderNames().toString());
		final String requestTokenHeader = request.getHeader("Authorization");
		System.err.println(requestTokenHeader);
		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				throw new IllegalArgumentException("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				throw new AuthenticationException("WT Token has expired");
			}
		} else {
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//                    "jwt token is invalid or incorrect");
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				Usuario userSession = usuarioService.findUserByEmail(username);
				
				GlobalUsuarioSession.GlobalUsuarioSessionBuilder globalUsuarioSession =   GlobalUsuarioSession.builder();
				 request.setAttribute("email", userSession.getEmail());
				 request.setAttribute("name", userSession.getRazonSocial());
		         request.setAttribute("idUser", userSession.getIdUsuario());
		         request.setAttribute("ruc", userSession.getRuc());
		         request.setAttribute("celular", userSession.getCelular());
		         request.setAttribute("direccion", userSession.getDireccion());
		         request.setAttribute("userType", userSession.getRoles().get(0).getNombre());
		         
		         globalUsuarioSession.email(userSession.getEmail())
		         			.name(userSession.getRazonSocial())
		                    .userId(Integer.valueOf(userSession.getIdUsuario()))
		                    .ruc(userSession.getRuc())
		                    .celular(userSession.getCelular())
		                    .direccion(userSession.getDireccion())
		                    .userType(userSession.getRoles().get(0).getNombre());
		         
		         request.setAttribute("globalUsuarioSession", globalUsuarioSession.build());
		            
				// 
			}
		}
		chain.doFilter(request, response);
	}

}