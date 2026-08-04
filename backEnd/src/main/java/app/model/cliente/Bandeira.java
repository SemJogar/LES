package app.model.cliente;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "bandeiras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Bandeira{
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ban_id")
	private Integer id;

	@Column (name = "ban_tipo")
	private  String tipo;

	@OneToMany(mappedBy = "bandeira", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Cartao> cartoes;
}