package app.dto.endereco.request;

import jakarta.validation.constraints.*;

import app.dto.endereco.request.CidadeRequest;

public record EnderecoRequest(
    String residencia,
    String tpLogradouro,
    String logradouro,
    String numero,
    String bairro,
    String cep,
    CidadeRequest cidade,
    String observacoes
) {}