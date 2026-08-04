export function mostrarBarraProgresso(){
    
    // 1. Seleciona a div vazia do seu HTML
    const container = document.getElementById('ProgressBar');

    // 2. Injeta o HTML da barra de progresso e do botão
        container.innerHTML = `
        <div class="progress mb-3">
            <div 
            id="BarraDinamica" 
            class="progress-bar progress-bar-striped progress-bar-animated" 
            role="progressbar" 
            style="width: 0%" 
            aria-valuenow="0" 
            aria-valuemin="0" 
            aria-valuemax="100">
            0%
            </div>
        </div>

        <button type="button" class="btn btn-primary" onclick="avancarProgresso()">
            Avançar Tarefa (+10%)
        </button>
        `;

        // 3. A função que faz a barra andar (agora no mesmo arquivo)
        window.avancarProgresso = function() {
        const barra = document.getElementById('BarraDinamica');
        
        if (barra) {
            let progressoAtual = parseInt(barra.getAttribute('aria-valuenow'));
            let novoProgresso = progressoAtual + 10;
            
            if (novoProgresso > 100) {
            novoProgresso = 100;
            }
            
            // Atualiza o Bootstrap 5 com os novos valores
            barra.setAttribute('aria-valuenow', novoProgresso);
            barra.style.width = novoProgresso + '%';
            barra.innerText = novoProgresso + '%';
        }
    }
}