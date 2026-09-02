'use client';

import React, { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';

export default function DetalhesLivroPage() {
  const [livro, setLivro] = useState(null);
  const [loading, setLoading] = useState(true);

  // Pega o ID diretamente da URL (ex: /livros/8 -> id = 8)
  const params = useParams();
  const id = params?.id;

  useEffect(() => {
    if (!id) return;

    fetch(`http://localhost:8080/livros/${id}`)
      .then((response) => response.json())
      .then((data) => {
        setLivro(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Erro ao buscar o livro:', error);
        setLoading(false);
      });
  }, [id]);

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
            <Link href="/" className="me-3">Início</Link>
            <Link href="/livros" className="me-3">Catálogo</Link>
            <Link href="/carrinho" className="me-3">Carrinho</Link>
            <Link href="/conta">Conta</Link>
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
            <Link href={`/livros/${livro.id}/editar`} className="btn btn-warning">
              Editar
            </Link>
          </div>
        </div>
      </div>
    </>
  );
}