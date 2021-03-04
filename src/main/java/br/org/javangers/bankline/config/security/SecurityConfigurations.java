package br.org.javangers.bankline.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.org.javangers.bankline.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * Injeta em AutenticacaoTokenFilter TokenService e UsuarioRepository
	 */
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/usuarios").permitAll()
//		.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
//		.antMatchers(HttpMethod.POST, "/auth").permitAll()
//		.antMatchers("/h2-console/**").permitAll()
//		.and()
//        .authorizeRequests().antMatchers("/h2-console/**").permitAll()
//		.anyRequest().authenticated()
//        .and()
//        .headers().frameOptions().disable()
//        .and()
//        .csrf().ignoringAntMatchers("/h2-console/**")
//        .and()
//		//.antMatchers(HttpMethod.GET, "/usuarios/*").permitAll()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
		.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
		.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
		//.antMatchers(HttpMethod.GET, "/usuarios/*").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
	//new BCryptPasswordEncoder().encode("123456")
	//$2a$10$N2UxmQhPEGJoAIXLjTTSwuxyXM8NJ3rjKHfW/IKe9Env24tMPcjMa

}
