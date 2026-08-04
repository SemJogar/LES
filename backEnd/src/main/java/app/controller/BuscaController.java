// package app.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.Collections;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/produtos")
// @CrossOrigin(origins = "http://localhost:3000") // Permite requisições vindas do seu Docker/Nginx
// public class BuscaController {

//     @GetMapping("/preditiva")
//     public ResponseEntity<List<String>> buscarPreditiva(@RequestParam("termo") String termo) {
//         // Se o usuário digitou menos de 2 letras, nem busca no banco para poupar processamento
//         if (termo == null || termo.trim().length() < 2) {
//             return ResponseEntity.ok(Collections.emptyList());
//         }
        
//         // Exemplo estático para testar. Na prática, você injetará seu Repository aqui:
//         // ex: return ResponseEntity.ok(produtoRepository.buscarNomes(termo));
//         List<String> bancoSimulado = List.of(
//             "Teclado Mecânico RGB", 
//             "Mouse Gamer Wireless", 
//             "Monitor IPS 144hz", 
//             "Mousepad Speed Extra Grande", 
//             "Teclado de Membrana Office"
//         );
        
//         List<String> resultados = bancoSimulado.stream()
//                 .filter(nome -> nome.toLowerCase().contains(termo.toLowerCase()))
//                 .collect(Collectors.toList());

//         return ResponseEntity.ok(resultados);
//     }
// }