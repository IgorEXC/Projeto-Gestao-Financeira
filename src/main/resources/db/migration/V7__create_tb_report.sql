create table tb_report
(
    id                bigserial
        constraint tb_report_pk
            primary key,
    user_id           bigint,
    data_atualizacao  timestamp,
    data_inclusao     timestamp,
    constraint fk_report_user
        foreign key (user_id) references tb_user (user_id)
);
