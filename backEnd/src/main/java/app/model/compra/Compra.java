package app.model.compra;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// é necessario importar este em especifico, pois está em outro pacote
import app.model.cliente.Cliente;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Compra {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_id")
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "com_cli_id")
    private Cliente cliente;

	// public enum SttPagamento{
	// 	APROVADO, 
	// 	REPROVADO
	// }
	// public enum SttTransporte{
	// 	EM_TRANSPORTE, 
	// 	ENTREGUE
	// }
	// public enum SttTroca{
	// 	EM_TROCA, 
	// 	TROCADO
	// }

	@Column(name = "com_stt_pagamento", length = 25)
	private String sttPagamento;

	@Column(name = "com_stt_transporte", length = 25)
	private String sttTransporte;
	
	@Column(name = "com_stt_troca", length = 25)
	private String sttTroca;

	@Column(name = "com_dt_entrada")
	private LocalDateTime dtEntrada;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompraLivro> itens = new ArrayList<>();

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CupomUso> cuponsUsados = new ArrayList<>();
}

