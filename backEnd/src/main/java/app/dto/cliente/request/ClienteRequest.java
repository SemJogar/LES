package app.dto.cliente.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

import app.dto.endereco.request.EnderecoRequest;
import app.dto.cliente.request.TelefoneRequest;
import app.dto.cliente.request.CartaoRequest;
import app.dto.compra.CompraRequest;

public record ClienteRequest(
    String genero,
    String nome,
    LocalDate dtNascimento,
    String cpf,
    String email,
    String senha,
    EnderecoRequest endereco,
    List<TelefoneRequest> telefones,
    List<CartaoRequest> cartoes
) {}
