package app.model.cliente;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import app.model.cliente.Perfil;
import app.model.cliente.Funcao;

import java.util.List;

@Entity
@Table(name = "notificacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "not_per_id")
    private Perfil perfil;

    @Column(name = "not_titulo", length = 50)
    private String titulo;

    @Column(name = "not_msg", length = 100)
    private String msg;

    @Column(name = "not_tp", length = 25)
    private String tp;

    @Column(name = "not_visualizacao")
    private Boolean visualizacao;

    @Column(name = "not_dt")
    private LocalDate dt;
}