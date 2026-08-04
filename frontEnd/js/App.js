// app.js
import {gerenciarRotas} from './Router.js';
import {mostrarBarraProgresso} from './components/ProgressBar.js'

export function iniciarAplicacao() {
  console.log("App inicializado pelo main.js!");

  // 1. Carrega o menu fixo que sempre aparece
  fetch('/html/NavBar.html')
    .then(res => res.text())
    .then(html => {
        document.getElementById('NavBar').innerHTML = html;
  });

  // 2. Passa o controle para o roteador decidir o resto da tela
  gerenciarRotas();
  mostrarBarraProgresso();
}