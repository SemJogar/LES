import { apiRequest } from "./api.js";

async function carregarCartoes() {
    try {
        const cartoes = await apiRequest("/cartao", { method: "GET" });
        const container = document.getElementById("lista-cartoes");
        container.innerHTML = "";

        if (cartoes.length === 0) {
            container.innerHTML = "<p class='text-muted'>Nenhum cartão cadastrado.</p>";
            return;
        }

        cartoes.forEach(cartao => {
            const numeroMascarado = "**** **** **** " + cartao.numero_cartao.slice(-4);

            container.innerHTML += `
                <div class="col-md-4">
                    <div class="card p-3 shadow-sm border-secondary">
                        <div class="d-flex justify-content-between align-items-start">
                            <div>
                                <i class="bi bi-credit-card-2-front fs-3 text-warning"></i>
                                <p class="mb-0 mt-2"><strong>${cartao.nome_cartao}</strong></p>
                                <p class="text-muted small">${numeroMascarado}</p>
                                <p class="text-muted small">Validade: ${cartao.validade}</p>
                            </div>
                            <button class="btn btn-outline-danger btn-sm" onclick="excluirCartao(${cartao.id})">
                                <i class="bi bi-trash"></i>
                            </button>
                        </div>
                    </div>
                </div>
            `;
        });
    } catch (erro) {
        console.error("Erro ao carregar cartões:", erro);
    }
}

window.excluirCartao = async function(id) {
    if (confirm("Deseja realmente remover este cartão?")) {
        try {
            await apiRequest(`/cartao/${id}`, { method: "DELETE" });
            carregarCartoes(); 
        } catch (erro) {
            alert("Erro ao excluir o cartão.");
        }
    }
};

carregarCartoes();