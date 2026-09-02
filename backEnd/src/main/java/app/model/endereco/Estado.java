package app.model.endereco;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "est_id")
	private Integer id;

	@Column(name = "est_iso", length = 2)
	private String iso;

	@Column(name = "est_nome", length = 100)
	private String nome;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "est_pai_id")
	private	Pais pais;
}