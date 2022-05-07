CREATE TABLE tb_customer(
    id serial PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL
);

CREATE SEQUENCE customer_id_sequence START 1;