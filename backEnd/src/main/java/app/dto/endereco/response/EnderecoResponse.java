package app.dto.endereco.response;

import jakarta.validation.constraints.*;

import app.dto.endereco.response.CidadeResponse;

public record EnderecoResponse(
    Integer id,
    String residencia,
    String tpLogradouro,
    String logradouro,
    String numero,
    String bairro,
    String cep,
    CidadeResponse cidade,
    String observacoes
) {}