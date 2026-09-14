import { apiRequest } from "./api.js";

let subtotal = 0;
let valorFrete = 0;
let valorDesconto = 0;

async function inicializarCheckout() {
    try {
        const [carrinho, enderecos, cartoes] = await Promise.all([
            apiRequest("/carrinho"),
            apiRequest("/enderecos"),
            apiRequest("/cartao")
        ]);

        renderizarItens(carrinho);
        renderizarSelecao(enderecos, "selecao-endereco", "endereco");
        renderizarSelecao(cartoes, "selecao-cartao", "cartao");
        
        subtotal = carrinho.reduce((acc, item) => acc + (item.preco * item.quantidade), 0);
        atualizarResumo();
    } catch (e) { console.error(e); }
}

function renderizarSelecao(lista, containerId, name) {
    const container = document.getElementById(containerId);
    container.innerHTML = lista.map((item, i) => `
        <div class="col-md-6">
            <input type="radio" class="btn-check" name="${name}" id="${name}-${item.id}" ${i===0?'checked':''}>
            <label class="btn btn-outline-light text-dark border w-100 p-3 text-start shadow-sm" for="${name}-${item.id}">
                <small class="fw-bold d-block">${item.logradouro || item.nome_cartao}</small>
                <span class="small text-muted">${item.numero || '**** ' + item.numero_cartao.slice(-4)}</span>
            </label>
        </div>
    `).join('');
}

window.calcularFrete = function() {
    // Simulação de frete fixo para teste
    valorFrete = 15.00;
    document.getElementById("resumo-frete").textContent = `R$ ${valorFrete.toFixed(2)}`;
    atualizarResumo();
}

function atualizarResumo() {
    const total = (subtotal + valorFrete) - valorDesconto;
    document.getElementById("resumo-subtotal").textContent = `R$ ${subtotal.toFixed(2)}`;
    document.getElementById("resumo-total").textContent = `R$ ${Math.max(0, total).toFixed(2)}`;
}

inicializarCheckout();