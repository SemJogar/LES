import { apiRequest } from "./api.js";

// Não consigo confirmar o nome exato da rota de pedidos com o código enviado.
// Estou assumindo que o endpoint para histórico é "/pedidos".
async function carregarHistorico() {
    try {
        const pedidos = await apiRequest("/pedidos", { method: "GET" });
        const tabela = document.getElementById("lista-pedidos");
        tabela.innerHTML = "";

        pedidos.forEach(pedido => {
            const tr = document.createElement("tr");
            
            // Lógica do botão de troca: só aparece se o status for 'ENTREGUE'
            let acaoBotao = "";
            if (pedido.status === "ENTREGUE") {
                acaoBotao = `<button class="btn btn-outline-warning btn-sm" onclick="solicitarTroca(${pedido.id})">Solicitar Troca</button>`;
            } else if (pedido.status === "EM TROCA") {
                acaoBotao = `<span class="badge bg-info">Troca em análise</span>`;
            }

            tr.innerHTML = `
                <td>#${pedido.id}</td>
                <td>${new Date(pedido.data).toLocaleDateString('pt-BR')}</td>
                <td>R$ ${pedido.total.toFixed(2)}</td>
                <td><span class="badge bg-secondary">${pedido.status}</span></td>
                <td>${acaoBotao}</td>
            `;
            tabela.appendChild(tr);
        });
    } catch (erro) {
        console.error("Erro ao carregar histórico:", erro);
    }
}

// Tornando a função global para o botão do HTML conseguir chamar
window.solicitarTroca = async function(idPedido) {
    if (confirm("Deseja realmente solicitar a troca deste pedido?")) {
        try {
            // Chamada para alterar o status para 'EM TROCA' conforme solicitado
            await apiRequest(`/pedidos/${idPedido}/troca`, {
                method: "POST"
            });
            alert("Solicitação de troca enviada com sucesso!");
            carregarHistorico(); // Atualiza a lista
        } catch (erro) {
            alert("Erro ao solicitar troca.");
        }
    }
};

carregarHistorico();