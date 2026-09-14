import { apiRequest } from "./api.js";

async function carregarDadosEsteticos() {
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    if (!usuario) return;

    try {
        const dados = await apiRequest("/conta");

        document.getElementById("nome-exibicao").textContent = `${dados.nome} ${dados.sobrenome}`;
        document.getElementById("email-exibicao").textContent = dados.email;
        document.getElementById("cpf-exibicao").textContent = dados.cpf;
        document.getElementById("dt_nascimento-exibicao").textContent = dados.dt_nascimento;
        // Se o backend tiver telefone, você preenche aqui tmb
    } catch (e) { console.error(e); }
}

carregarDadosEsteticos();