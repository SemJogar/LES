import { apiRequest } from "./api.js";

async function carregarClientes() {
    try {
        const clientes = await apiRequest("/admin/clientes");
        renderizarTabela(clientes);
    } catch (e) { console.error(e); }
}

function renderizarTabela(lista) {
    const tbody = document.getElementById("tabela-clientes");
    tbody.innerHTML = lista.map(c => `
        <tr>
            <td>
                <div class="fw-bold">${c.nome} ${c.sobrenome}</div>
                <div class="small text-muted">${c.email}</div>
            </td>
            <td>${c.cpf}</td>
            <td><span class="badge bg-success">Ativo</span></td>
            <td class="text-end">
                <button onclick="verDetalhes(${c.id})" class="btn btn-sm btn-outline-dark">
                    <i class="bi bi-eye"></i> Ver Detalhes
                </button>
            </td>
        </tr>
    `).join('');
}

window.verDetalhes = async function(id) {
    try {
        const cliente = await apiRequest(`/admin/clientes/${id}`);
        const container = document.getElementById("conteudo-detalhes");
        
        container.innerHTML = `
            <div class="row">
                <div class="col-md-6 border-end">
                    <h6><i class="bi bi-geo-alt"></i> Endereços Cadastrados</h6>
                    ${cliente.enderecos.map(e => `<p class="small mb-1 border-bottom">${e.logradouro}, ${e.numero}</p>`).join('')}
                </div>
                <div class="col-md-6">
                    <h6><i class="bi bi-cart"></i> Últimos Pedidos</h6>
                    ${cliente.pedidos.map(p => `<p class="small mb-1 border-bottom">#${p.id} - R$ ${p.total} (${p.status})</p>`).join('')}
                </div>
            </div>
        `;
        
        new bootstrap.Modal(document.getElementById('modalDetalhesCliente')).show();
    } catch (e) { alert("Erro ao carregar detalhes."); }
}

carregarClientes();