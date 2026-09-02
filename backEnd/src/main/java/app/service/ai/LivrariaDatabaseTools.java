package app.service.ai;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import java.util.List;

// Importe as classes corretas do seu projeto aqui:
// import app.repository.SuaClasseClienteRepository;
// import app.repository.SuaClasseLivroRepository;

@Component
public class LivrariaDatabaseTools {

    // Substitua pelos tipos de Repository reais do seu projeto
    // private final ClienteRepository clienteRepository;
    // private final LivroRepository livroRepository;

    @Tool("Busca os gêneros literários favoritos e o histórico do cliente pelo ID")
    public String buscarHistoricoCliente(@P("ID do cliente") Long clienteId) {
        // Retorno temporário para teste até conectar ao banco
        return "Gêneros preferidos: Ficção Científica, Suspense. Última compra: Duna.";
    }

    @Tool("Busca os livros mais vendidos da loja por gênero literário")
    public List<String> buscarMaisVendidosPorGeneros(@P("Nome do gênero, ex: Suspense, Sci-Fi") String genero) {
        // Retorno temporário para teste até conectar ao banco
        return List.of("O Hobbit", "Neuromancer", "Fundação");
    }
}