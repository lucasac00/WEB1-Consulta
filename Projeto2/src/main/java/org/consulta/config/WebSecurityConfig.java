package org.consulta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.consulta.security.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/error", "/login/**", "/js/**", "/medicos/listagemMedicos", "medicos/especialidade", "medicos/listagemEspecialidades").permitAll()
                        .requestMatchers("/index").authenticated()
                        .requestMatchers("/css/**", "/image/**", "/webjars/**").permitAll()
                        .requestMatchers("/consultas/consultasPorCpf/**", "/consultas/criarConsulta/**").hasRole("PACIENTE")
                        .requestMatchers("/consultas/consultasPorCrm/**").hasRole("MEDICO")
                        .requestMatchers("/medicos/**", "/pacientes/**", "/usuarios/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index", true)
                        .failureHandler((request, response, exception) -> {
                            request.getSession().setAttribute("error", "Invalid username or password");
                            response.sendRedirect("/login?error=true");
                        })
                        .permitAll())
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll())
        ;

        return http.build();
    }
}
