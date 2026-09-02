package app.model.compra;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cup_id")
    private Integer id;

    @Column(name = "cup_codigo", length = 12)
    private String codigo;
    @Column(name = "cup_tp_desconto", length = 25)
    private String tpDesconto;
    @Column(name = "cup_desconto", precision = 5, scale = 2)
    private BigDecimal desconto;
    @Column(name = "cup_valor_min", precision = 5, scale = 2)
    private BigDecimal valor;

    @Column(name = "cup_lmt_total")
    private Integer ltTotal;
    @Column(name = "cup_lmt_cliente")
    private Integer ltCliente;

    @Column(name = "cup_dt_inicial")
    private LocalDate dtInicial;
    @Column(name = "cup_dt_final")
    private LocalDate dtFinal;
    @Column(name = "cup_stt")
    private Boolean stt;


    @JsonIgnore // Recomendado para evitar loop infinito na serialização JSON
    @OneToMany(mappedBy = "cupom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CupomUso> cupons = new ArrayList<>();
}