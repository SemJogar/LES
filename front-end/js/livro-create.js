import { apiRequest } from "./api.js";
import { limparErros, mostrarErrosCampo } from "./form.js";

function showToast(mensagem, tipo) {
    alert(mensagem);
}

async function salvar(event) {
    event.preventDefault(); 
    limparErros();

    const btn = document.getElementById("btnSalvar");
    btn.disabled = true;
    btn.innerHTML = "Salvando...";


    const livro = {
        titulo: document.getElementById("titulo").value,
        subtitulo: document.getElementById("subtitulo").value,
        autor: document.getElementById("autor").value,
        editora: document.getElementById("editora").value,
        isbn: document.getElementById("isbn").value,
        preco: document.getElementById("preco").value,
        estoque: document.getElementById("estoque").value,
        categoria: document.getElementById("categoria").value,
        sinopse: document.getElementById("sinopse").value,
    };

    try {
        await apiRequest("/livro", {
            method: "POST",
            body: JSON.stringify(livro),
        });

        showToast("Livro criado com sucesso!", "success");
        
        setTimeout(() => {
            window.location.href = "livro.html"; 
        }, 2000);

    } catch (erro) {
        if (erro.validation) {
            const erros = erro.validation;
            for (const campo in erros) {
                mostrarErrosCampo(campo, erros[campo]);
            }
        } else {
            showToast("Ocorreu um erro ao criar o livro.", "danger");
        }
    } finally {
        btn.disabled = false;
        btn.innerHTML = "Registrar livro";
    }
}

document.getElementById("formLivro").addEventListener("submit", salvar);