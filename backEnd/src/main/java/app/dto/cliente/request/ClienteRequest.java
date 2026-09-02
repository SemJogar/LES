package app.dto.cliente.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

import app.dto.endereco.request.EnderecoRequest;

public record ClienteRequest(
    @NotBlank
    //tenho que criar uma tabela para genero, ou um enum, ou algo assim
    String genero,

    @NotBlank
    @Size(min = 2, max = 100)
    String nome,

    @NotNull(message = "A data de nascimento é obrigatória") // Use @NotNull para datas e objetos
    @Past(message = "A data de nascimento deve ser no passado") // Opcional, mas recomendado para nascimento
    LocalDate dtNascimento,

    @NotBlank
    @Size(min = 11, max = 11)
    String cpf,

    EnderecoRequest endereco,
    List<TelefoneRequest> telefones,
    List<CartaoRequest> cartoes,
    PerfilRequest perfil
) {}
