import { apiRequest } from "./api.js";

async function carregarTrocas() {
    try {
        const pedidos = await apiRequest("/admin/trocas");
        const container = document.getElementById("lista-trocas-admin");
        container.innerHTML = "";

        pedidos.forEach(pedido => {
            // Criando um Card Estético para cada troca
            container.innerHTML += `
                <div class="col-md-6 col-lg-4">
                    <div class="card h-100 shadow-sm border-0">
                        <div class="row g-0">
                            <div class="col-4">
                                <img src="${pedido.imagemProduto || '../img/livros/sem-capa.jpg'}" 
                                     class="img-fluid rounded-start h-100 object-fit-cover" alt="Produto">
                            </div>
                            <div class="col-8">
                                <div class="card-body p-3">
                                    <h6 class="fw-bold mb-1">Pedido #${pedido.id}</h6>
                                    <p class="small text-muted mb-2">${pedido.nomeCliente}</p>
                                    <div class="mb-3">
                                        <span class="badge ${pedido.status === 'EM TROCA' ? 'bg-warning text-dark' : 'bg-info'}">
                                            ${pedido.status}
                                        </span>
                                    </div>
                                    <div class="d-flex gap-2">
                                        <button onclick="autorizarTroca(${pedido.id})" class="btn btn-success btn-sm flex-grow-1">Aceitar</button>
                                        <button onclick="abrirModalRecusa(${pedido.id})" class="btn btn-outline-danger btn-sm">Negar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        });
    } catch (e) { console.error(e); }
}

// Funções para Recusa
window.abrirModalRecusa = function(id) {
    document.getElementById("idTrocaAtual").value = id;
    const modal = new bootstrap.Modal(document.getElementById('modalJustificativa'));
    modal.show();
}

window.confirmarRecusa = async function() {
    const id = document.getElementById("idTrocaAtual").value;
    const justificativa = document.getElementById("txtJustificativa").value;

    if (!justificativa) {
        alert("Por favor, informe a justificativa.");
        return;
    }

    try {
        await apiRequest(`/admin/trocas/${id}/recusar`, {
            method: "POST",
            body: JSON.stringify({ justificativa })
        });
        alert("Troca recusada com sucesso.");
        location.reload();
    } catch (e) { alert("Erro ao processar."); }
}

carregarTrocas();