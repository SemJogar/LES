let timerDebounce = null;
let controllerAtual = null;

const inputBusca = document.getElementById('input-busca');
const listaResultados = document.getElementById('resultados-busca');

inputBusca.addEventListener('input', (event) => {
    const termo = event.target.value.trim();

    clearTimeout(timerDebounce);

    // Cancela requisições anteriores em andamento
    if (controllerAtual) {
        controllerAtual.abort();
    }

    // Oculta o menu se tiver menos de 2 caracteres
    if (termo.length < 2) {
        listaResultados.style.display = 'none';
        listaResultados.innerHTML = '';
        return;
    }

    // Debounce de 300ms
    timerDebounce = setTimeout(() => {
        fazerBuscaPreditiva(termo);
    }, 300);
});

async function fazerBuscaPreditiva(termo) {
    controllerAtual = new AbortController();

    try {
        const response = await fetch(`/api/livros/buscar?termo=${encodeURIComponent(termo)}`, {
            signal: controllerAtual.signal
        });

        if (!response.ok) return;

        const dados = await response.json();
        exibirResultadosBootstrap(dados);
    } catch (erro) {
        if (erro.name !== 'AbortError') {
            console.error('Erro na busca preditiva:', erro);
        }
    }
}

function exibirResultadosBootstrap(lista) {
    listaResultados.innerHTML = '';

    if (lista.length === 0) {
        listaResultados.innerHTML = `<li class="dropdown-item disabled text-muted">Nenhum resultado encontrado</li>`;
        listaResultados.style.display = 'block';
        return;
    }

    // Preenche os resultados usando componentes do Bootstrap
    lista.forEach(item => {
        const li = document.createElement('li');
        li.innerHTML = `
            <a class="dropdown-item d-flex align-items-center py-2" href="/livro.html?id=${item.id}">
                <i class="bi bi-book me-2 text-primary"></i>
                <span>${item.titulo}</span>
            </a>
        `;
        listaResultados.appendChild(li);
    });

    listaResultados.style.display = 'block'; // Exibe o menu
}

// Oculta o menu se o usuário clicar fora da busca
document.addEventListener('click', (e) => {
    if (!inputBusca.contains(e.target) && !listaResultados.contains(e.target)) {
        listaResultados.style.display = 'none';
    }
});