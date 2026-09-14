import { apiRequest } from "./api.js";
import { limparErros, mostrarErrosCampo } from "./form.js"

function showToast(mensagem, tipo) {
    alert(mensagem);
}


async function salvar(event) {
    event.preventDefault(); 
    limparErros();
   
    const btn = document.getElementById("btnSalvar");
    btn.disabled = true;
    btn.innerHTML = "Salvando...";

    const conta = {
        nome: document.getElementById("nome").value,
        sobrenome: document.getElementById("sobrenome").value,
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value,
        confirmar_senha: document.getElementById("confirmar_senha").value,
        genero: document.getElementById("genero").value,
        dt_nascimento: document.getElementById("dt_nascimento").value,
        cpf: document.getElementById("cpf").value,
    };
    try {
        await apiRequest ("/conta",{
            method: "POST",
            body: JSON.stringify(conta),
        });
        showToast("Conta criada com sucesso!", "success");
        setTimeout(() => {
            window.location.href = "inicio.html";
        }, 2000);
    } catch (erro) {
        if (erro.status === 400) {
            const erros = await erro.json();
            for (const campo in erros) {
                mostrarErrosCampo(campo, erros[campo]);
            }
        } else {
            showToast("Ocorreu um erro ao criar a conta.", "danger");
        }
    } finally {
        btn.disabled = false;
        btn.innerHTML = "Salvar";
    }
}
document.getElementById("formConta").addEventListener("submit", salvar);
