import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

export function DetalhesLivro() {
  const [livro, setLivro] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('http://localhost:8080/livros/8')
      .then((response) => response.json())
      .then((data) => {
        setLivro(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Erro ao buscar o livro:', error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div className="container mt-5">
        <p>Carregando dados do livro...</p>
      </div>
    );
  }

  if (!livro) {
    return (
      <div className="container mt-5">
        <p>Livro não encontrado.</p>
      </div>
    );
  }

  return (
    <>
      {/* Header */}
      <header className="p-3 bg-light border-bottom">
        <div className="container d-flex justify-content-between align-items-center">
          <figure className="mb-0">
            <img src="/assets/img/DomCasmurro.jpg" alt="Logo / Capa" height="50" />
          </figure>

          <form action="/buscar" method="get" className="d-flex">
            <input
              type="text"
              name="q"
              className="form-control me-2"
              placeholder="Digite sua busca..."
              required
            />
            <button type="submit" className="btn btn-outline-primary">
              Buscar
            </button>
          </form>

          <nav>
            <a href="inicio.html" className="me-3">Início</a>
            <a href="livro.html" className="me-3">Catálogo</a>
            <a href="carrinho.html" className="me-3">Carrinho</a>
            <a href="#">Conta</a>
          </nav>
        </div>
      </header>

      {/* Conteúdo Principal */}
      <div className="container mt-5">
        <div className="card p-4 shadow-sm">
          <h2>Detalhes do Livro</h2>

          <img
            src={livro.imagem?.[0]?.url || "/assets/img/capa.jpg"}
            alt={livro.titulo}
            className="img-fluid rounded mb-3"
            style={{ maxWidth: '250px' }}
          />

          <p><strong>Título:</strong> {livro.titulo}</p>
          <p><strong>Autor:</strong> {livro.autor}</p>
          <p><strong>Editora:</strong> {livro.editora} ({livro.edicao})</p>
          <p><strong>Ano:</strong> {livro.ano}</p>
          <p><strong>ISBN:</strong> {livro.isbn}</p>

          <p>
            <strong>Categoria:</strong>{' '}
            {livro.categoria?.map((cat) => cat.nome).join(', ')}
          </p>

          <p><strong>Páginas:</strong> {livro.qtPag}</p>
          <p><strong>Sinopse:</strong></p>
          <p>{livro.sinopse}</p>

          <div>
            <a href={`editar-livro.html?id=${livro.id}`} className="btn btn-warning">
              Editar
            </a>
          </div>
        </div>
      </div>
    </>
  );
}

export default DetalhesLivro;