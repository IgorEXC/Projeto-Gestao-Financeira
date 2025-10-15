create table tb_category
(
    id                         bigserial
        constraint tb_category_pk
            primary key,
    category_name              varchar(40) unique,
    predicted_category_limit   double precision,
    data_atualizacao           timestamp,
    data_inclusao              timestamp
);
