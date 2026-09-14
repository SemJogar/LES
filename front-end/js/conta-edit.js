import { apiRequest } from "./api.js";

const form = document.getElementById("formEditarConta");

// 1. Carregar dados atuais do usuário
async function carregarDados() {
    try {
        const usuario = await apiRequest("/conta", { method: "GET" });
        
        document.getElementById("nome").value = usuario.nome;
        document.getElementById("sobrenome").value = usuario.sobrenome;
        document.getElementById("email").value = usuario.email;
        document.getElementById("dt_nascimento").value = usuario.dt_nascimento;
        document.getElementById("genero").value = usuario.genero;
        document.getElementById("cpf").value = usuario.cpf;
    } catch (erro) {
        alert("Erro ao carregar seus dados.");
    }
}

// 2. Enviar atualizações
form.addEventListener("submit", async (event) => {
    event.preventDefault();
    
    const btn = document.getElementById("btnSalvar");
    btn.disabled = true;
    btn.innerHTML = "Salvando...";

    const dadosAtualizados = {
        nome: document.getElementById("nome").value,
        sobrenome: document.getElementById("sobrenome").value,
        email: document.getElementById("email").value,
        dt_nascimento: document.getElementById("dt_nascimento").value,
        genero: document.getElementById("genero").value,
    };

    // Só adiciona a senha se o usuário digitou algo
    const senha = document.getElementById("senha").value;
    if (senha) {
        if (senha !== document.getElementById("confirmar_senha").value) {
            alert("As senhas não coincidem!");
            btn.disabled = false;
            btn.innerHTML = "Salvar Alterações";
            return;
        }
        dadosAtualizados.senha = senha;
    }

    try {
        await apiRequest("/conta", {
            method: "PUT",
            body: JSON.stringify(dadosAtualizados)
        });
        alert("Dados atualizados com sucesso!");
        window.location.href = "conta.html";
    } catch (erro) {
        alert("Erro ao atualizar dados.");
    } finally {
        btn.disabled = false;
        btn.innerHTML = "Salvar Alterações";
    }
});

carregarDados();

const btnDeletar = document.getElementById("btnDeletarConta");

btnDeletar.addEventListener("click", async () => {
    const confirmacao = confirm("AVISO: Esta ação é irreversível. Todos os seus dados e histórico de pedidos serão excluídos. Deseja realmente excluir sua conta?");
    
    if (confirmacao) {
        try {
            await apiRequest("/conta", {
                method: "DELETE"
            });
            
            alert("Sua conta foi excluída com sucesso.");
            
            // Limpa o localStorage para que o sistema não tente mais usar o usuário excluído
            localStorage.removeItem("usuario");
            
            // Redireciona para a página inicial ou de login
            window.location.href = "login.html";
            
        } catch (erro) {
            alert("Ocorreu um erro ao tentar excluir sua conta. Tente novamente mais tarde.");
        }
    }
});