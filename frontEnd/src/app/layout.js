export default function RootLayout({ children }) {
  return (
    <html lang="pt-BR">
      <head>
        {/* Import do Bootstrap via CDN para testes rápidos */}
        <link
          rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        />
        <link
          rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
        />
      </head>
      <body>{children}</body>
    </html>
  );
}