package app.model.endereco;

import jakarta.persistence.*;
import lombok.*;

// import app.model.cliente.Cliente;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "end_id")
	private Integer id;

	@Column(name = "end_residencia", length = 70)
	private String residencia;

	@Column(name = "end_tp_logradouro", length = 15)
	private String tp_logradouro;

	@Column(name = "end_logradouro", length = 50)
	private String logradouro;

	@Column(name = "end_numero")
	private Integer numero;

	@Column(name = "end_bairro", length = 20)
	private String bairro;

	@Column(name = "end_cep", length = 8)
	private String cep;

	@OneToOne
	@JoinColumn(name = "end_cid_id")
	private	Cidade cidade;

	// @ManyToOne
	// @JoinColumn(name = "end_cli_id")
	// private	Cliente cliente;

	@Column(name = "end_observacoes")
	private String observacoes;
}

