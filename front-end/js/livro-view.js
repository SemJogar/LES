import { apiRequest } from "./api.js";

async function carregarDetalhesLivro() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    if (!id) {
        alert("Livro não encontrado!");
        window.location.href = "inicio.html";
        return;
    }
    const btnEditar = document.getElementById("btnEditarLivro");
    btnEditar.href = `livro-edit.html?id=${id}`;

    try {
        const livro = await apiRequest(`/livros/${id}`, { method: "GET" });

        document.getElementById("capa").src = livro.imagem;
        document.getElementById("titulo").textContent = livro.titulo;
        document.getElementById("autor").textContent = livro.autor;
        document.getElementById("editora").textContent = livro.editora;
        document.getElementById("isbn").textContent = livro.isbn;
        document.getElementById("preco").textContent = `R$ ${livro.preco}`;
        document.getElementById("estoque").textContent = livro.estoque;
        document.getElementById("categoria").textContent = livro.categoria;
        document.getElementById("sinopse").textContent = livro.sinopse;

    } catch (erro) {
        console.error("Erro ao carregar detalhes:", erro);
        alert("Erro ao carregar as informações do livro.");
    }
}

carregarDetalhesLivro();

function verificarPermissoes() {
    const usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
    const btnCarrinho = document.getElementById("btnAdicionarCarrinho");
    const btnEditar = document.getElementById("btnEditarLivro");

    if (!usuarioLogado) { 
        return;
    }

    if (usuarioLogado.cargo === "admin") {
        btnEditar.classList.remove("d-none");
    } else {
        btnCarrinho.classList.remove("d-none");
    }
}

verificarPermissoes();