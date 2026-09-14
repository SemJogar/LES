import { apiRequest } from "./api.js";
import { limparErros, mostrarErrosCampo } from "./form.js";

function showToast(mensagem, tipo) {
    alert(mensagem);
}

const params = new URLSearchParams(window.location.search);
const id = params.get("id");

async function carregarLivro() {
    if (!id) return;
    try {
        const livro = await apiRequest(`/livros/${id}`);

        document.getElementById("titulo").value = livro.titulo;
        document.getElementById("subtitulo").value = livro.subtitulo;
        document.getElementById("isbn").value = livro.isbn;
        document.getElementById("autor").value = livro.autor;
        document.getElementById("editora").value = livro.editora;
        document.getElementById("sinopse").value = livro.sinopse;
        document.getElementById("preco").value = livro.preco;
        document.getElementById("estoque").value = livro.estoque;
        document.getElementById("categoria").value = livro.categoria;
    } catch (error) {
        showToast("Erro ao carregar livro", "danger");
    }
}

async function salvar(event) {
    event.preventDefault(); 
    limparErros();

    const btn = document.getElementById("btnSalvar");
    btn.disabled = true;
    btn.innerHTML = "Atualizando...";

    const livro = {
        titulo: document.getElementById("titulo").value,
        subtitulo: document.getElementById("subtitulo").value,
        isbn: document.getElementById("isbn").value,
        autor: document.getElementById("autor").value,
        editora: document.getElementById("editora").value,
        sinopse: document.getElementById("sinopse").value,
        preco: document.getElementById("preco").value,
        estoque: document.getElementById("estoque").value,
        categoria: document.getElementById("categoria").value
    };

    try {
        await apiRequest(`/livros/${id}`, {
            method: "PUT",
            body: JSON.stringify(livro)
        });

        showToast("Livro atualizado!", "success");
        setTimeout(() => {
            window.location.href = "livro.html";
        }, 1500);
    } catch (error) {
        if (error.validation) {
            for (const campo in error.validation) {
                mostrarErrosCampo(campo, error.validation[campo]);
            }
        } else {
            showToast("Erro ao atualizar livro", "danger");
        }
    } finally {
        btn.disabled = false;
        btn.innerHTML = "Salvar alterações";
    }
}

document.getElementById("btnSalvar").addEventListener("click", salvar);
carregarLivro();