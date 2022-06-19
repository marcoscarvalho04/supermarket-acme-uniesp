CREATE TABLE tb_notification(
    id serial PRIMARY KEY,
    sendAt timestamp,
    sender varchar(100),
    customermail varchar(200),
    idcustomer integer not null,
    message varchar(200)
);

CREATE SEQUENCE notification_id_sequence START 1;