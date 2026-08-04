package app.model.livro;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "imagens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ima_id")
    private Integer id;

    @Column(name = "ima_url", length = 200)
    private String url;

    @ManyToOne
    @JoinColumn(name = "ima_liv_id")
    private Livro livro;
}