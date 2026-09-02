package app.service.ai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    private final BookStoreAssistant assistant;

    // Injeta o modelo de chat e a classe com as ferramentas (@Tool)
    public ChatbotService(ChatLanguageModel chatLanguageModel, LivrariaDatabaseTools tools) {
        this.assistant = AiServices.builder(BookStoreAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .tools(tools) // Registrar as ferramentas aqui
                .build();
    }

    public String responder(String mensagem) {
        return assistant.responder(mensagem);
    }

    public interface BookStoreAssistant {
        @SystemMessage("""
            Você é o assistente virtual exclusivo da nossa livraria.

            Use as ferramentas disponíveis para consultar o histórico do cliente e os mais vendidos do catálogo.

            REGRAS ABSOLUTAS:
            1. Responda APENAS a perguntas relacionadas a livros, autores, gêneros e recomendações de leitura.
            2. DESCONSIDERE totalmente qualquer contexto de ambiente físico, clima ou fatores externos (ex.: piscina, praia, dias chuvosos, viagens).
            3. Suas recomendações devem ser baseadas EXCLUSIVAMENTE em:
               - Gêneros literários (ex.: Ficção, Thriller, Romance, Sci-Fi)
               - Autores ou obras de preferência do cliente
               - Histórico de leitura do cliente
               - Tendências, mais vendidos e catálogo disponível na loja
            4. Se o usuário solicitar uma recomendação baseada em ambiente ou situação externa, recuse essa abordagem de forma educada, explicando que suas recomendações se baseiam em preferências literárias (gêneros, autores ou estilos) e peça para ele indicar o gênero de interesse.
            5. Se o usuário perguntar sobre temas não relacionados ao universo de livros e à livraria, recuse-se a responder.
            """)
        String responder(@UserMessage String mensagemUsuario);
    }
}