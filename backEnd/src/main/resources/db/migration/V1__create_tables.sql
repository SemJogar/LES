CREATE TABLE bandeiras(
    ban_id SERIAL PRIMARY KEY, 
    ban_tipo VARCHAR(20) NOT NULL
);

CREATE TABLE paises (
    pai_id SERIAL PRIMARY KEY,
    pai_iso CHAR(2) UNIQUE NOT NULL, -- Ex: 'BR', 'US', 'CA' (Padrão ISO 3166-1 alpha-2)
    pai_nome VARCHAR(100) NOT NULL
);

CREATE TABLE estados (
    est_id SERIAL PRIMARY KEY,
    est_iso CHAR(2) NOT NULL,
    est_nome VARCHAR(100) NOT NULL,

    est_pai_id INTEGER NOT NULL,
    CONSTRAINT FK_EST_PAI FOREIGN KEY (est_pai_id) REFERENCES paises(pai_id),

    -- Garante que não existam dois estados com a mesma sigla DENTRO do mesmo país
    CONSTRAINT UQ_EST_PAI UNIQUE (est_iso, est_pai_id)
);

CREATE TABLE cidades (
    cid_id SERIAL PRIMARY KEY,
    cid_nome VARCHAR(100) NOT NULL,
    
    cid_est_id INTEGER NOT NULL,
    CONSTRAINT FK_CID_EST FOREIGN KEY (cid_est_id) REFERENCES estados(est_id)
);

CREATE TABLE enderecos(
    end_id SERIAL PRIMARY KEY,
    end_residencia VARCHAR(70) NOT NULL,
    end_tp_logradouro VARCHAR(15),
    end_logradouro VARCHAR(50) NOT NULL,
    end_numero VARCHAR(5) NOT NULL,
    end_bairro VARCHAR(20) NOT NULL,
    end_cep VARCHAR(8) NOT NULL,

    end_cid_id INTEGER NOT NULL,
    CONSTRAINT FK_END_CID FOREIGN KEY (end_cid_id) REFERENCES cidades(cid_id),
    
    end_observacoes TEXT
);

CREATE TABLE clientes(
    cli_id SERIAL PRIMARY KEY,
    cli_genero VARCHAR(10) NOT NULL,
    cli_nome VARCHAR(100) NOT NULL,
    cli_dt_nascimento DATE NOT NULL,
    cli_cpf VARCHAR(11) NOT NULL,

    cli_end_id INTEGER NOT NULL,
    CONSTRAINT FK_CLI_END FOREIGN KEY (cli_end_id) REFERENCES enderecos(end_id)
);

CREATE TABLE cartoes(
    car_id SERIAL PRIMARY KEY,
    car_numero VARCHAR(16) NOT NULL,
    car_nome VARCHAR(100) NOT NULL,
    car_codigo VARCHAR(4)  NOT NULL, 

    car_ban_id INTEGER NOT NULL, 
    CONSTRAINT FK_CAR_BAN FOREIGN KEY (car_ban_id) REFERENCES bandeiras(ban_id),
    car_cli_id INTEGER NOT NULL,
    CONSTRAINT FK_CAR_CLI FOREIGN KEY (car_cli_id) REFERENCES clientes(cli_id)
);

CREATE TABLE telefones(
    tel_id SERIAL PRIMARY KEY,
    tel_tp VARCHAR(2) NOT NULL,
    tel_ddd VARCHAR(2) NOT NULL,
    tel_num VARCHAR(9) NOT NULL,
    
    tel_cli_id INTEGER NOT NULL,
    CONSTRAINT FK_TEL_CLI FOREIGN KEY (tel_cli_id) REFERENCES clientes(cli_id)
);




CREATE TABLE funcoes(
    fun_id SERIAL PRIMARY KEY,
    fun_tp VARCHAR(25)
);

CREATE TABLE perfis(
    per_id SERIAL PRIMARY KEY,
    per_email VARCHAR(150) NOT NULL UNIQUE,
    per_senha VARCHAR(255) NOT NULL,
    per_rank BIGINT,
    per_cli_id INTEGER,
    CONSTRAINT FK_PER_CLI FOREIGN KEY (per_cli_id) REFERENCES clientes(cli_id),
    per_fun_id INTEGER,
    CONSTRAINT FK_PER_FUN FOREIGN KEY (per_fun_id) REFERENCES funcoes(fun_id)
);


CREATE TABLE notificacoes(
    not_id SERIAL PRIMARY KEY,
    not_per_id INTEGER,
    CONSTRAINT FK_NOT_PER FOREIGN KEY (not_per_id) REFERENCES perfis(per_id),
    not_titulo VARCHAR(50),
    not_msg VARCHAR(100),
    not_tp VARCHAR(25),
    not_visualizacao BOOL NOT NULL DEFAULT FALSE, --tenho que colocar como false por padrão
    not_dt DATE DEFAULT CURRENT_DATE
);



CREATE TABLE categorias (
    cat_id SERIAL PRIMARY KEY,
    cat_nome VARCHAR(50) NOT NULL
);

CREATE TABLE precificacoes(
    pre_id SERIAL PRIMARY KEY,
    pre_grupo VARCHAR(20),
    pre_lucro NUMERIC(5,2) CONSTRAINT pre_lucro_positivo CHECK (pre_lucro > 0)
);

CREATE TABLE dimensoes(
    dim_id SERIAL PRIMARY KEY,
    --Ele está criando constraints para evitar aceitar dados absurdos   
    -- NUMERIC(5,2) significa: até 5 dígitos no total, sendo 2 deles decimais (Ex: 143,23)
    dim_altura NUMERIC(5,2) CONSTRAINT dim_altura_positivo CHECK (dim_altura > 0),
    dim_largura NUMERIC(5,2) CONSTRAINT dim_largura_positivo CHECK (dim_largura > 0),
    dim_peso NUMERIC(5,2) CONSTRAINT dim_peso_positivo CHECK (dim_peso > 0),
    dim_profundidade NUMERIC(5,2) CONSTRAINT dim_profundidade_positivo CHECK (dim_profundidade > 0)
);

-- 2. Agora criamos a tabela de Livros com os campos obrigatórios (NOT NULL)
CREATE TABLE livros (
    liv_id SERIAL PRIMARY KEY,                        -- integer auto-incremento
    liv_autor VARCHAR(100) NOT NULL,                  -- obrigatório
    liv_ano SMALLINT NOT NULL,                      -- obrigatório
    liv_titulo VARCHAR(150) NOT NULL,                  -- obrigatório
    liv_editora VARCHAR(100) NOT NULL,                 -- obrigatório
    liv_edicao VARCHAR(20) NOT NULL,                  -- obrigatório
    liv_isbn VARCHAR(13) NOT NULL UNIQUE,             -- obrigatório e único
    liv_qt_pag SMALLINT NOT NULL,                   -- obrigatório
    liv_sinopse TEXT NOT NULL,                -- obrigatório  
    liv_cod_barras VARCHAR(20) NOT NULL,             -- obrigatório

    liv_pre_id INTEGER NOT NULL,
    CONSTRAINT FK_LIV_PRE FOREIGN KEY (liv_pre_id) REFERENCES precificacoes(pre_id),
    liv_dim_id INTEGER NOT NULL,
    CONSTRAINT FK_LIV_DIM FOREIGN KEY (liv_dim_id) REFERENCES dimensoes(dim_id),

    liv_status BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE imagens(
    ima_id SERIAL PRIMARY KEY,
    ima_url VARCHAR(200),
    
    ima_liv_id INTEGER NOT NULL,
    CONSTRAINT FK_IMA_LIV FOREIGN KEY (ima_liv_id) REFERENCES livros(liv_id) ON DELETE CASCADE
);

CREATE TABLE livros_categorias(
    lvc_id SERIAL PRIMARY KEY,
    lvc_liv_id INTEGER NOT NULL, 
    -- Caso liv_id relacionado for deletado ele deleta o registro inteiro por causa do ON DELETE CASCADE
    CONSTRAINT FK_LVC_LIV FOREIGN KEY (lvc_liv_id) REFERENCES livros(liv_id) ON DELETE CASCADE,
    lvc_cat_id INTEGER NOT NULL, 
    -- Caso cat_id relacionado for deletado ele deleta o registro inteiro por causa do ON DELETE CASCADE
    CONSTRAINT FK_LVC_CAT FOREIGN KEY (lvc_cat_id) REFERENCES categorias(cat_id) ON DELETE CASCADE,

    CONSTRAINT UQ_LVC UNIQUE (lvc_liv_id, lvc_cat_id)
);



CREATE TABLE estoques(
    est_id SERIAL PRIMARY KEY,

    est_liv_id INTEGER NOT NULL,
    CONSTRAINT FK_EST_LIV FOREIGN KEY (est_liv_id) REFERENCES livros(liv_id) ON DELETE CASCADE,

    est_qt INTEGER NOT NULL,
    est_custo NUMERIC(5,2),
    est_fornecedor VARCHAR(100),
    est_dt_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE compras(
    com_id SERIAL PRIMARY KEY,
    
    com_cli_id INTEGER NOT NULL,
    CONSTRAINT FK_COM_CLI FOREIGN KEY (com_cli_id) REFERENCES clientes(cli_id), 
    
    com_stt_pagamento VARCHAR(25) NOT NULL,
    com_stt_transporte VARCHAR(25) NOT NULL,
    com_stt_troca VARCHAR(25) DEFAULT NULL,
    com_dt_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE compras_livros(
    col_id SERIAL PRIMARY KEY,

    col_com_id INTEGER NOT NULL,
    CONSTRAINT FK_COL_COM FOREIGN KEY (col_com_id) REFERENCES compras(com_id),

    col_liv_id INTEGER NOT NULL,
    CONSTRAINT FK_COL_LIV FOREIGN KEY (col_liv_id) REFERENCES livros(liv_id),
    
    col_qt SMALLINT NOT NULL,
    col_valor NUMERIC(5,2) NOT NULL
);



CREATE TABLE cupons(
    cup_id SERIAL PRIMARY KEY,
    cup_codigo VARCHAR(12) NOT NULL UNIQUE,
    cup_tp_desconto VARCHAR(25),
    cup_desconto NUMERIC(5,2),
    cup_valor_mn NUMERIC(5,2),
    cup_lt_total INTEGER,
    cup_lt_cliente INTEGER,
    cup_dt_inicial DATE,
    cup_dt_final DATE,
    cup_stt BOOL NOT NULL DEFAULT TRUE
);

CREATE TABLE cupom_usos(
    cmu_id SERIAL PRIMARY KEY,
    cmu_cup_id INTEGER,
    CONSTRAINT FK_CMU_CUP FOREIGN KEY (cmu_cup_id) REFERENCES cupons(cup_id),
    cmu_com_id INTEGER,
    CONSTRAINT FK_CMU_COM FOREIGN KEY (cmu_com_id) REFERENCES compras(com_id),
    cmu_data_uso DATE DEFAULT CURRENT_DATE
);