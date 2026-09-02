package app.service;

import org.springframework.stereotype.Service;

import app.dto.compra.request.CompraRequest;
import app.dto.compra.response.CompraResponse;
import app.mapper.CompraMapper;
import app.model.compra.Compra;
import app.repository.compra.CompraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {

	private final CompraRepository compraRepository;
	private final CompraMapper compraMapper;


	@Transactional
	public CompraResponse criarCompra (CompraRequest request){
		Compra compra = compraMapper.requestToCompra(request);

		compra = compraRepository.save(compra);

		return compraMapper.compraToResponse(compra);
	}

	public CompraResponse visualizarCompra (Integer id){
		Compra compra = compraRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Compra não encontrado com o ID: " + id));

		CompraResponse response = compraMapper.compraToResponse(compra);

		return response;
	}

	@Transactional
	public CompraResponse editarCompra (CompraRequest request, Integer id){
		Compra compra = compraRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Compra não encontrado com o ID: " + id));

		compraMapper.atualizarCompra(compra, request);

		compra = compraRepository.save(compra);

		CompraResponse response = compraMapper.compraToResponse(compra);

		return response;
	}

	public void deletarCompra(Integer id){
		Compra compra = compraRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Compra não encontrado com o ID: " + id));

		compraRepository.delete(compra);
	}
}