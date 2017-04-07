package com.teci.gereteci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.teci.gereteci.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**");
	}
	
	//problema aqui
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/gereteci/servicosmanutencao/novo").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicosmanutencao/novo").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicosinternet/novo").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicosinternet/novo").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicosemail/novo").hasRole("CADASTRAR_SERVICO") 
				.antMatchers("/gereteci/servicosemail/novo").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicosrede/novo").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicosrede/novo").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicostelefone/novo").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicostelefone/novo").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicosemail/").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicosemail/").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicosinternet/").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicosinternet/").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicosmanutencao/").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicosmanutencao/").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicosrede/").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicosrede/").hasRole("HOME_TECI")
				.antMatchers("/gereteci/servicostelefone/").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/servicostelefone/").hasRole("HOME_TECI")
				.antMatchers("/gereteci/computadores").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/computadores").hasRole("HOME_TECI")
				.antMatchers("/gereteci/computadores/novo").hasRole("CADASTRAR_SERVICO")
				.antMatchers("/gereteci/computadores/novo").hasRole("HOME_TECI")
			
				
			.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
