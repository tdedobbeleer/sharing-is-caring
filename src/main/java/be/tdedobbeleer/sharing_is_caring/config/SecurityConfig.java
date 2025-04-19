package be.tdedobbeleer.sharing_is_caring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(withDefaults())
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/actuator/health").anonymous()
                        .requestMatchers("/**").hasRole("USER")
                        .anyRequest()
                        .authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(@Value("${app.user.name}") String u, @Value("${app.user.password}") String p) {
        UserDetails user = User.builder()
                .username(u)
                .roles("USER")
                .password(p)
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}