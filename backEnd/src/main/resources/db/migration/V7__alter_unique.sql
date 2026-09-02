-- 1. Garante que cada País tenha um código ISO único
ALTER TABLE paises
ADD CONSTRAINT uk_paises_pai_iso UNIQUE (pai_iso);

-- 2. Garante que não existam dois Estados com a mesma sigla ISO dentro do mesmo País
ALTER TABLE estados
ADD CONSTRAINT uk_estados_iso_pais UNIQUE (est_iso, est_pai_id);

-- 3. Garante que não existam duas Cidades com o mesmo nome dentro do mesmo Estado
ALTER TABLE cidades
ADD CONSTRAINT uk_cidades_nome_estado UNIQUE (cid_nome, cid_est_id);