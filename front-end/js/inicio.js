import { apiRequest } from "./api.js";

const btnChatbot = document.getElementById("btn-chatbot");
const chatWindow = document.getElementById("chat-window");
const btnCloseChat = document.getElementById("close-chat");

btnChatbot.addEventListener("click", () => {
    chatWindow.style.display = chatWindow.style.display === "flex" ? "none" : "flex";
});

btnCloseChat.addEventListener("click", () => {
    chatWindow.style.display = "none";
});

const btnEnviar = document.getElementById("btn-enviar-chat");
const chatInput = document.getElementById("chat-input");
const chatMessages = document.getElementById("chat-messages");

btnEnviar.addEventListener("click", () => {
    const texto = chatInput.value.trim();
    if (texto) {
        chatMessages.innerHTML += `<p class="text-end"><strong>Você:</strong> ${texto}</p>`;
        chatInput.value = "";
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }
});

function criarCardLivro(livro) {
    return `
        <div class="col">
            <div class="card h-100 shadow-sm">
                <img src="${livro.imagem || '../img/capa-padrao.jpg'}" class="card-img-top" alt="${livro.titulo}">
                <div class="card-body">
                    <h5 class="card-title">${livro.titulo}</h5>
                    <p class="card-text text-muted small">${livro.autor}</p>
                    <p class="fw-bold text-warning">R$ ${livro.preco}</p>
                    <a href="livro-view.html?id=${livro.id}" class="btn btn-warning btn-sm w-100 mt-2">
                    Ver detalhes
                    </a>
                </div>
            </div>
        </div>
    `;
}

async function carregarLivros() {
    try {
        const livros = await apiRequest("/livros", { method: "GET" });
        
        const containerRec = document.getElementById("recomendacoes");
        const containerVend = document.getElementById("mais-vendidos");

        if (!containerRec || !containerVend) return;

        containerRec.innerHTML = "";
        containerVend.innerHTML = "";

        livros.forEach((livro, index) => {
            const cardHtml = criarCardLivro(livro);
            // Exemplo: Primeiros 4 em recomendações, o restante em mais vendidos
            if (index < 4) {
                containerRec.innerHTML += cardHtml;
            } else {
                containerVend.innerHTML += cardHtml;
            }
        });
    } catch (erro) {
        console.error("Erro ao carregar livros:", erro);
    }
}

carregarLivros();