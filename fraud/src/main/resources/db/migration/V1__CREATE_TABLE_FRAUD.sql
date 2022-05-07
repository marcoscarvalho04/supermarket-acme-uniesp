CREATE TABLE tb_fraud(
    id serial PRIMARY KEY,
    description varchar(200),
    costumer_id integer NOT NULL ,
    is_fraud boolean,
    created_at timestamp


);

CREATE SEQUENCE fraud_id_sequence START 1;