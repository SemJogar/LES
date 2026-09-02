package app.dto.endereco.request;

import jakarta.validation.constraints.*;

public record EnderecoRequest(
    @NotBlank
    @Size(min = 10, max = 70)
    String residencia,
    @NotBlank
    @Size(max = 15)
    String tpLogradouro,
    @NotBlank
    @Size(max = 50)
    String logradouro,
    @NotBlank
    @Size(max = 5)
    String numero,
    @NotBlank
    @Size(max = 20)
    String bairro,
    @NotBlank
    @Size(max = 8)
    String cep,
    Integer cidadeId,
    String observacoes
) {}