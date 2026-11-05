package ma.fstg.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Ressources publiques accessibles sans authentification
                .requestMatchers("/", "/login", "/access-denied", "/css/**", "/js/**", "/images/**").permitAll()
                // Espace reserve aux utilisateurs ayant le role USER
                .requestMatchers("/user/**").hasRole("USER")
                // Espace reserve aux administrateurs ayant le role ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Toute autre requete necessite une authentification
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/user/dashboard", false)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied")
            );

        return http.build();
    }
}
