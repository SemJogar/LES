// router.js
export function gerenciarRotas() {
    const container = document.getElementById('ProgressBar'); // ou o ID do seu container principal
    const rotaAtual = window.location.pathname;

    console.log("Roteador ativado! Rota atual:", rotaAtual);

    // Exemplo de lógica de rotas
    // if (rotaAtual === '/barra' || rotaAtual === '/' || rotaAtual.endsWith('index.html')) {
    //     fetch('ProgressBar.html')
    //         .then(res => res.text())
    //         .then(html => {
    //             container.innerHTML = html;
    //         });
    // }
}