package app.model.cliente;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Telefone{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tel_id")
	private Integer id;

	@Column(name = "tel_tp", length = 2)
	private Integer tp;

	@Column(name = "tel_ddd", length = 2)
	private Integer ddd;

	@Column(name = "tel_num", length = 9)
	private Integer num;

	@ManyToOne
	@JoinColumn(name = "tel_cli_id")
	private Cliente cliente;
}