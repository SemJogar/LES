package app.dto.cliente.response;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

import app.dto.endereco.response.EnderecoResponse;

public record ClienteResponse(
    Integer id,
    String genero,
    String nome,
    LocalDate dtNascimento,
    String cpf,
    EnderecoResponse endereco,
    List<TelefoneResponse> telefones,
    List<CartaoResponse> cartoes,
    PerfilResponse perfil
) {}
