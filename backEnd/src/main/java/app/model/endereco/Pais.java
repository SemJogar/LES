package app.model.endereco;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paises")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pai_id")
	private Integer id;

	@Column(name = "pai_iso", length = 2)
	private String iso;

	@Column(name = "pai_nome", length = 100)
	private String nome;
}