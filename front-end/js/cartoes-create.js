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

    const cartao = {
        numero_cartao: document.getElementById("numero_cartao").value,
        nome_cartao: document.getElementById("nome_cartao").value,
        cvv: document.getElementById("cvv").value,
        validade: document.getElementById("validade").value,
    };

    try {
        await apiRequest("/cartao", {
            method: "POST",
            body: JSON.stringify(cartao),
        });
        showToast("Cartão registrado com sucesso!", "success");
        setTimeout(() => {
            window.location.href = "conta.html"; 
        }, 2000);
    } catch (erro) {
        
        if (erro.validation) {
            const erros = erro.validation;
            for (const campo in erros) {
                mostrarErrosCampo(campo, erros[campo]);
            }
        } else {
            showToast("Ocorreu um erro ao registrar o cartão.", "danger");
        }
    } finally {
        btn.disabled = false;
        btn.innerHTML = "Registrar"; 
    }
}

document.getElementById("formCartao").addEventListener("submit", salvar);