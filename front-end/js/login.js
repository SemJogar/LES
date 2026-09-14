import { apiRequest } from "./api.js";
import { limparErros, mostrarErrosCampo } from "./form.js";

function showToast(mensagem, tipo) {
    alert(mensagem);
}

async function login(event) {
    event.preventDefault();
    limparErros();

    const btn = document.getElementById("btnSalvar"); 
    btn.disabled = true;
    btn.innerHTML = "Entrando...";

    const usuario = {
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value
    };

    try {
        const resposta = await apiRequest("/login", {
            method: "POST",
            body: JSON.stringify(usuario)
        });

        localStorage.setItem("usuario", JSON.stringify(resposta));

        // NOVA LÓGICA: Redirecionamento baseado no cargo
        if (resposta.cargo === "admin") {
            window.location.href = "admin-painel.html"; // Admin vai para o painel
        } else {
            window.location.href = "inicio.html"; // Cliente vai para a loja
        }

    } catch (erro) {
        showToast("E-mail ou senha incorretos", "danger");
    }
    
    if (resposta.cargo === "admin") {
        window.location.href = "admin-painel.html"; // Página exclusiva
    } else {
        window.location.href = "conta.html"; // Página exclusiva
    }
}

document.getElementById("btnSalvar").addEventListener("click", login);