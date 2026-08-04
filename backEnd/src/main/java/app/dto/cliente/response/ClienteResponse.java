package app.dto.cliente.response;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

import app.dto.endereco.response.EnderecoResponse;
import app.dto.cliente.response.TelefoneResponse;
import app.dto.cliente.response.CartaoResponse;
import app.dto.compra.CompraResponse;

public record ClienteResponse(
    Integer id,
    String genero,
    String nome,
    LocalDate dtNascimento,
    String cpf,
    String email,
    String senha,
    EnderecoResponse endereco,
    List<TelefoneResponse> telefones,
    List<CartaoResponse> cartoes
) {}
