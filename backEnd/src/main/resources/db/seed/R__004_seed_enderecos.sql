-- 1. Insere o País (Brasil)
INSERT INTO paises (pai_iso, pai_nome)
VALUES ('BR', 'Brasil') ON CONFLICT (pai_iso) DO NOTHING;

-- 2. Insere o Estado (São Paulo) associado ao Brasil
INSERT INTO estados (est_iso, est_nome, est_pai_id)
VALUES (
    'SP', 
    'São Paulo', 
    (SELECT pai_id FROM paises WHERE pai_iso = 'BR')
) ON CONFLICT (est_iso, est_pai_id) DO NOTHING;

-- 3. Insere a Cidade (Mogi das Cruzes) associada ao estado de SP
INSERT INTO cidades (cid_nome, cid_est_id)
VALUES (
    'Mogi das Cruzes', 
    (
        SELECT e.est_id 
        FROM estados e 
        JOIN paises p ON e.est_pai_id = p.pai_id 
        WHERE e.est_iso = 'SP' AND p.pai_iso = 'BR'
    )
) ON CONFLICT (cid_nome, cid_est_id) DO NOTHING;
-- preciso colocar unique em cidade, estado e pais
