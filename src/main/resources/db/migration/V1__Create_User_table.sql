create table tb_user
(
    user_id          bigserial
        constraint tb_user_pk
            primary key,
    cpf              varchar(11) not null,
    name             varchar(40),
    username         varchar(40),
    email            varchar(50) not null,
    monthly_income   decimal,
    data_atualizacao timestamp,
    data_inclusao    timestamp
);

