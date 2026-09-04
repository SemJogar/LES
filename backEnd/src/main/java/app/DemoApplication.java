package app;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Carrega o arquivo .env (ignoreIfMissing evita erros em ambientes de produção/Docker onde .env não existe)
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        
        // Injeta as variáveis do .env no System Properties do Java para o Spring conseguir ler
        dotenv.entries().forEach(entry -> 
            System.setProperty(entry.getKey(), entry.getValue())
        );

        SpringApplication.run(DemoApplication.class, args);
    }

}