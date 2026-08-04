export class Me
export function mensagemResponse() {

    axios.get('http://localhost:8080/api/mensagem')

        .then(response => {
            //contem a resposta do back, no caso a mensagem "ola, o spring funciona"
            //ou algo parecido
            // data.countedo ai ser equivalente ao Map<string,string> e ao Json { "tipo": "conteudo"}
            const mensagem = response.data.conteudo;

            document.getElementById('mensagem').innerText = mensagem;

        })
        .catch(error => {

            console.error(error);
            console.warn("Backend offline, mas o front continua rodando normalmente.");
        });
}