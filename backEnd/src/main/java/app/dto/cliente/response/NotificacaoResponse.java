package app.dto.cliente.response;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NotificacaoResponse(
    PerfilResponse perfil,
    String titulo,
    String mensagem,
    String tipo,
    Boolean visualizacao,
    LocalDate data
) {}