export function inputRequest(){
    axios.get("/api/input").then(response => {

        const mensagem = response.data.conteudo;
        document.getElementById('mensagem').innerText = mensagem;
    })

}