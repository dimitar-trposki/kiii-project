package mk.ukim.finki.emc.bookeshop.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Configuration
@EnableWebSecurity
public class JwtSecurityWebConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    public JwtSecurityWebConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider, JwtFilter jwtFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer ->
                        corsCustomizer.configurationSource(corsConfigurationSource())
                )
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
                        authorizeHttpRequestsCustomizer.anyRequest().permitAll());
        return http.build();
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(corsCustomizer ->
//                        corsCustomizer.configurationSource(corsConfigurationSource())
//                )
//                .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
//                        authorizeHttpRequestsCustomizer
//                                .requestMatchers(
//                                        "/swagger-ui/**",
//                                        "/v3/api-docs/**",
//                                        "/api/user/register",
//                                        "/api/user/login"
//                                )
//                                .permitAll()
//                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                                .requestMatchers(
//                                        "/api/books/**",
//                                        "/api/authors/**",
//                                        "/api/countries/**"
//                                ).permitAll()
//                                //.hasAnyRole("USER", "LIBRARIAN")
//                                .anyRequest()
//                                .permitAll()
//                )
//                .sessionManagement(sessionManagementConfigurer ->
//                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
    }

}