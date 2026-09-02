package app.model.compra;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cupom_usos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CupomUso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmu_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cmu_cup_id")
    private Cupom cupom;
    @ManyToOne
    @JoinColumn(name = "cmu_com_id")
    private Compra compra;

    @Column(name = "cmu_data_uso")
    private LocalDate dtUso;
}