package doc.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import doc.service.AccountService;
import doc.service.IAccountService;

public class JWTFilter extends UsernamePasswordAuthenticationFilter {

	//@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IAccountService accountService;
	
	
	
	public JWTFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			doc.entities.User user = null;
			user = new ObjectMapper().readValue(request.getInputStream(), doc.entities.User.class);
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Probleme in request content");
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		List<String> roles = new ArrayList<String>();
		user.getAuthorities().forEach(au -> {
			roles.add(au.getAuthority());
		});
		
		
	
		
		
		
		String jwt = JWT.create().withIssuer(request.getRequestURI()).withSubject(user.getUsername())
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				//.withArrayClaim("infos", (String[])infos.toArray())
				// .withExpiresAt(new Date(System.currentTimeMillis()+Ses))
				.sign(Algorithm.HMAC256("secret1"));
		response.addHeader("Authorization", jwt);
	}

}
