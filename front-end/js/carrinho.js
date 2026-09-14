import { apiRequest } from "./api.js";

async function carregarCarrinho() {
    try {
        const itens = await apiRequest("/carrinho", { method: "GET" });
        const lista = document.getElementById("lista-carrinho");
        const totalSpan = document.getElementById("total-carrinho");

        lista.innerHTML = "";
        let totalGeral = 0;

        itens.forEach(item => {
            const totalItem = item.preco * item.quantidade;
            totalGeral += totalItem;

            lista.innerHTML += `
                <tr>
                    <td>${item.titulo}</td>
                    <td>${item.quantidade}</td>
                    <td>R$ ${item.preco}</td>
                    <td>
                        <button class="btn btn-danger btn-sm" onclick="removerDoCarrinho(${item.id})">
                            <i class="bi bi-trash"></i>
                        </button>
                    </td>
                </tr>
            `;
        });

        totalSpan.textContent = `R$ ${totalGeral.toFixed(2)}`;

    } catch (erro) {
        console.error("Erro ao carregar carrinho:", erro);
    }
}

carregarCarrinho();