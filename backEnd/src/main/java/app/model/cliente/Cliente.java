package app.model.cliente;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

// é necessario importar este em especifico, pois está em outro pacote
import app.model.endereco.Endereco;
import app.model.compra.Compra;

@Entity
@Table(name = "clientes") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private Integer id;

	@Column(name = "cli_genero", length = 10)
	private String genero;

	@Column(name = "cli_nome", length = 100)
	private String nome;

	@Column(name = "cli_dt_nascimento")
	private LocalDate dtNascimento;

	@Column(name = "cli_cpf", length = 11)
	private String cpf;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cli_end_id")
	private Endereco endereco;

	// o mappedBy = "cliente" da linha abaixo se refere ao private Cliente cliente de Endereco
	// o CascadeType.REMOVE é para deletar todos os enderecos relacionados, caso cliente seja excluido
	// no caso do orphan removal é diferente
	// exemplo: se eu REMOVER (não é deletar), um dos enderecos da lista
	// ele automaticamente deleta esse enderco

	// @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true)
	// private List<Endereco> enderecos;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefones;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cartao> cartoes;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Compra> compras;

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Perfil perfil;
}