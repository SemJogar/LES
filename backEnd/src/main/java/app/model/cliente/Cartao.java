package app.model.cliente;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cartoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cartao{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Integer id;

	@Column(name = "car_numero")
	private Long numero;

	@Column(name = "car_nome")
	private String nome;

	@Column(name = "car_codigo")
	private Integer codigo;

	// No caso não usamos nenhuma propriedade do cascade
	// pois o objeto bandeira já está previamento salvo no banco 
	// e de forma nenhuma deve ser alterado apenas conectado
	// só usaria o persist e o merge se o fosse criar um objeto do zero
	@ManyToOne
	@JoinColumn(name = "car_ban_id")
	private Bandeira bandeira;

	@ManyToOne
	@JoinColumn(name = "car_cli_id")
	private Cliente cliente;
}