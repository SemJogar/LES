import { apiRequest } from "./api.js";

async function carregarCupons() {
    try {
        // Assume-se que o endpoint para cupons do usuário é "/cupons"
        const cupons = await apiRequest("/cupons", { method: "GET" });
        const container = document.getElementById("container-cupons");
        const msgVazio = document.getElementById("mensagem-vazio");

        if (cupons.length === 0) {
            msgVazio.classList.remove("d-none");
            return;
        }

        container.innerHTML = "";
        cupons.forEach(cupom => {
            container.innerHTML += `
                <div class="col-md-4">
                    <div class="card border-warning shadow-sm">
                        <div class="card-body text-center">
                            <h6 class="text-muted small">CUPOM DE TROCA</h6>
                            <h3 class="text-success fw-bold">R$ ${cupom.valor.toFixed(2)}</h3>
                            <div class="bg-light p-2 my-2 border dashed">
                                <span class="fw-bold text-dark">${cupom.codigo}</span>
                            </div>
                            <p class="card-text small">Status: <strong>${cupom.status}</strong></p>
                        </div>
                    </div>
                </div>
            `;
        });
    } catch (erro) {
        console.error("Erro ao carregar cupons:", erro);
    }
}

carregarCupons();