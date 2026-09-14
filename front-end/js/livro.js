import { apiRequest } from "./api.js";

async function carregarCatalogo() {
    try {
        const livros = await apiRequest("/livros");
        mostrarLivros(livros);
        verificarAdmin(); // Adicionado para mostrar o botão de Novo Livro se for Admin
    } catch (erro) {
        console.error("Erro ao carregar catálogo:", erro);
    }
}

function verificarAdmin() {
    const usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
    const btnCriar = document.getElementById("btnCriarLivro");

    // Se houver botão no HTML e o usuário for admin, mostramos ele
    if (btnCriar && usuarioLogado && usuarioLogado.cargo === "admin") {
        btnCriar.classList.remove("d-none");
    }
}

function mostrarLivros(livros) {
    const catalogo = document.getElementById("catalogo");
    catalogo.innerHTML = "";

    livros.forEach(livro => {
        const card = document.createElement("div");
        card.className = "col-md-4 mb-4";
        card.innerHTML = `
            <div class="card h-100 shadow-sm">
                <img src="${livro.imagem || '../img/livros/sem-capa.jpg'}" class="card-img-top" alt="${livro.titulo}">
                <div class="card-body">
                    <h5 class="card-title">${livro.titulo}</h5>
                    <p class="card-text text-muted small">Autor: ${livro.autor}</p>
                    <p class="fw-bold text-warning">R$ ${livro.preco}</p>
                    <!-- Link para a página de visualização -->
                    <a href="livro-view.html?id=${livro.id}" class="btn btn-warning btn-sm w-100 mt-2">
                        Ver detalhes
                    </a>
                </div>
            </div>
        `;
        catalogo.appendChild(card);
    });
}

carregarCatalogo();