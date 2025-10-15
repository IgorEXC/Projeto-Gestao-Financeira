create table tb_financial_planning
(
    financial_planning_id  bigserial
        constraint tb_financial_planning_pk
            primary key,
    annual_goal            double precision,
    monthly_goal           double precision,
    user_id                bigint,
    data_atualizacao       timestamp,
    data_inclusao          timestamp,
    constraint fk_financial_planning_user
        foreign key (user_id) references tb_user (user_id)
);
