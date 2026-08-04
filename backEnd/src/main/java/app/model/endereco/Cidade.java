package app.model.endereco;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid_id")
	private Integer id;

	@Column(name = "cid_nome", length = 100)
	private String nome;

	@OneToOne
	@JoinColumn(name = "cid_est_id")
	private	Estado estado;
}