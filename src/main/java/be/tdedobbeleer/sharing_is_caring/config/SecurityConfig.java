package be.tdedobbeleer.sharing_is_caring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/login.html", "/*.css").permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
//                .formLogin(formLoginSpec -> formLoginSpec.loginPage("/login.html"));
        return http.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(@Value("${app.user.name}") String u, @Value("${app.user.password}") String p) {
        UserDetails user = User.builder()
                .username(u)
                .roles("USER")
                .password(p)
                .build();
        return new MapReactiveUserDetailsService(user);
    }
}