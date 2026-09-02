FROM node:20-alpine

WORKDIR /app

# Copia os arquivos de dependência primeiro para aproveitar o cache do Docker
COPY package*.json ./

RUN npm install

# Copia o restante do código fonte
COPY . .

EXPOSE 3000

CMD ["npm", "run", "dev"]
