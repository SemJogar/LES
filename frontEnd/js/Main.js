// main.js
import { iniciarAplicacao } from './App.js';
import { mensagemResponse } from './service/response/mensagemResponse.js';

// Dá a partida no sistema
iniciarAplicacao();

const btnMensagem = document.getElementById('btnMensagem');

btnMensagem.addEventListener('click', () => {
    // Certifique-se de que a função importada é chamada corretamente aqui
    mensagemResponse(); 
});

const campoBusca = document.getElementById('campo-busca');
const listaSugestoes = document.getElementById('sugestoes');
let timeoutId;

campoBusca.addEventListener('input', () => {
    clearTimeout(timeoutId);
    const termo = campoBusca.value.trim();

    if (termo.length < 2) {
        fecharSugestoes();
        return;
    }

    // Debounce: evita fazer requisições a cada letra digitada correndo
    timeoutId = setTimeout(() => {
        fazerBusca(termo);
    }, 300);
});

async function fazerBusca(termo) {
    try {
        // Usando o Axios que já está no seu HTML
        // IMPORTANTE: Ajuste a porta se o seu Spring Boot não estiver na 8080
        const resposta = await axios.get(`http://localhost:8080/api/produtos/preditiva`, {
            params: { termo: termo }
        });
        
        exibirSugestoes(resposta.data);
    } catch (erro) {
        console.error("Erro ao buscar dados do Spring Boot:", erro);
    }
}

function exibirSugestoes(itens) {
    listaSugestoes.innerHTML = ''; 

    if (itens.length === 0) {
        fecharSugestoes();
        return;
    }

    itens.forEach(item => {
        const botao = document.createElement('button');
        botao.type = 'button';
        botao.className = 'list-group-item list-group-item-action';
        botao.textContent = item;

        // Quando clica na sugestão, ela vai para o input e fecha a lista
        botao.addEventListener('click', () => {
            campoBusca.value = item;
            fecharSugestoes();
        });

        listaSugestoes.appendChild(botao);
    });

    listaSugestoes.style.display = 'block'; 
}

function fecharSugestoes() {
    listaSugestoes.style.display = 'none';
    listaSugestoes.innerHTML = '';
}

// Fecha a lista se clicar fora dela ou do input
document.addEventListener('click', (evento) => {
    if (evento.target !== campoBusca && evento.target !== listaSugestoes) {
        fecharSugestoes();
    }
});