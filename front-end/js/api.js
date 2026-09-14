// services/api.js
const BASE_URL = "http://localhost:8080"; 

export async function apiRequest(endpoint, options = {}) {
    const url = `${BASE_URL}${endpoint}`;
    
    const config = {
        ...options,
        headers: {
            "Content-Type": "application/json",
            ...options.headers,
        },
    };

    try {
        const res = await fetch(url, config);

        if (!res.ok) {
            let errorData;
            try {
                errorData = await res.json();
            } catch (e) {
                throw { status: res.status, message: "Erro de comunicação com o servidor" };
            }
            throw { status: res.status, ...errorData };
        }

        if (res.status === 204) return null;

        return await res.json();
    } catch (error) {
        // Se o erro for de rede (servidor desligado, por exemplo)
        if (!error.status) {
            throw { status: 500, message: "Não foi possível conectar ao servidor. Verifique se o backend está rodando." };
        }
        throw error;
    }
}