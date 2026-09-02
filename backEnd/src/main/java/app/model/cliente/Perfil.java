package app.model.cliente;

import java.math.BigInteger;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "perfis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Integer id;

    @Column(name = "per_email", length = 12)
    private String email;
    @Column(name = "per_senha", length = 25)
    private String senha;
    @Column(name = "per_rank")
    private Long rank;

    @OneToOne
    @JoinColumn(name = "per_cli_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "per_fun_id")
    private Funcao funcao;
}