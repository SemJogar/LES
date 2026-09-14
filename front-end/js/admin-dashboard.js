import { apiRequest } from "./api.js";

let meuGrafico = null;
const categoriasDisponiveis = ["Romance", "Fantasia", "Terror", "Suspense", "Ficção cientifica"];

function iniciarCategorias() {
    const container = document.getElementById("categoriaChecks");
    if (!container) return;
    
    container.innerHTML = ""; 
    categoriasDisponiveis.forEach(cat => {
        container.innerHTML += `
            <div class="form-check form-check-inline">
                <input class="form-check-input check-cat" type="checkbox" value="${cat}" id="check-${cat}" checked>
                <label class="form-check-label" for="check-${cat}">${cat}</label>
            </div>
        `;
    });
}

async function renderizarGrafico() {
    console.log("Tentando renderizar o gráfico..."); 

    const dInicio = document.getElementById("dataInicio").value;
    const dFim = document.getElementById("dataFim").value;

    const selecionadas = Array.from(document.querySelectorAll(".check-cat:checked")).map(c => c.value);
    
    try {
        const dados = await apiRequest("/admin/vendas-analise");
        console.log("Dados recebidos do Mock:", dados); 

        const canvas = document.getElementById('graficoVendas');
        if (!canvas) {
            console.error("Canvas 'graficoVendas' não encontrado!");
            return;
        }
        
        const ctx = canvas.getContext('2d');
        
        if (meuGrafico) meuGrafico.destroy();

        meuGrafico = new Chart(ctx, {
            type: 'line',
            data: {
                labels: dados.meses,
                datasets: selecionadas.map(cat => ({
                    label: cat,
                    data: dados.valores[cat],
                    borderColor: stringToColor(cat),
                    backgroundColor: 'transparent',
                    borderWidth: 3,
                    tension: 0.3
                }))
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: { legend: { position: 'top' } }
            }
        });
        console.log("Gráfico criado com sucesso!");
    } catch (e) {
        console.error("Erro no processo do gráfico:", e);
    }
}

function stringToColor(str) {
    const colors = {
        "Romance": "#ff6384",
        "Fantasia": "#36a2eb",
        "Terror": "#4bc0c0",
        "Suspense": "#ffcd56",
        "Ficção cientifica": "#9966ff"
    };
    return colors[str] || "#000000";
}

document.getElementById("btnFiltrar").addEventListener("click", renderizarGrafico);

iniciarCategorias();
renderizarGrafico();