package app.controller;

import app.service.ai.ChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    public record ChatRequest(String mensagem) {}

    @PostMapping
    public ResponseEntity<String> enviarMensagem(@RequestBody ChatRequest request) {
        String respostaIa = chatbotService.responder(request.mensagem());
        return ResponseEntity.ok(respostaIa);
    }
}