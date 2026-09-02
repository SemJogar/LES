package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Habilita o CORS com a configuração personalizada abaixo
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Desabilita o CSRF (padrão para APIs REST stateless)
            .csrf(csrf -> csrf.disable())
            
            // Define quais rotas são públicas e quais precisam de autenticação
            .authorizeHttpRequests(auth -> auth
                // Rotas públicas (livros, frete, cadastro, etc.)
                .requestMatchers("/livros/**", "/frete/**", "/clientes/**").permitAll()
                
                // Qualquer outra requisição precisa estar autenticada
                .anyRequest().permitAll() // Altere para .authenticated() quando implementar o login/JWT
            );

        return http.build();
    }

    // Bean do BCrypt para criptografar e validar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuração de CORS para permitir requisições do Next.js
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Origem permitida (seu frontend Next.js)
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        
        // Métodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Cabeçalhos permitidos
        config.setAllowedHeaders(List.of("*"));
        
        // Permitir envio de credenciais/cookies se necessário
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}