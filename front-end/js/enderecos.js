import { apiRequest } from "./api.js";

const form = document.getElementById("formEndereco");
const listaContainer = document.getElementById("lista-enderecos");

async function carregarEnderecos() {
    try {
        const enderecos = await apiRequest("/enderecos", { method: "GET" });
        listaContainer.innerHTML = "";

        enderecos.forEach(end => {
            listaContainer.innerHTML += `
                <div class="col-md-6">
                    <div class="card p-3 shadow-sm">
                        <p class="mb-1"><strong>${end.logradouro}, ${end.numero}</strong></p>
                        <p class="text-muted small mb-2">${end.bairro} - ${end.cidade} | CEP: ${end.cep}</p>
                        <button class="btn btn-outline-danger btn-sm w-25" onclick="excluirEndereco(${end.id})">
                            Excluir
                        </button>
                    </div>
                </div>
            `;
        });
    } catch (erro) {
        console.error("Erro ao carregar endereços:", erro);
    }
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const novoEnd = {
        logradouro: document.getElementById("logradouro").value,
        numero: document.getElementById("numero").value,
        bairro: document.getElementById("bairro").value,
        cidade: document.getElementById("cidade").value,
        cep: document.getElementById("cep").value,
    };

    try {
        await apiRequest("/enderecos", {
            method: "POST",
            body: JSON.stringify(novoEnd)
        });
        location.reload(); // Recarrega para mostrar o novo endereço
    } catch (erro) {
        alert("Erro ao salvar endereço.");
    }
});

window.excluirEndereco = async function(id) {
    if (confirm("Deseja excluir este endereço?")) {
        try {
            await apiRequest(`/enderecos/${id}`, { method: "DELETE" });
            carregarEnderecos();
        } catch (erro) {
            alert("Erro ao excluir.");
        }
    }
};

carregarEnderecos();