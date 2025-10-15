create table tb_expense
(
    id                      bigserial
        constraint tb_expense_pk
            primary key,
    name                    varchar(50) not null,
    description             varchar(100),
    price                   double precision not null,
    "date"                  date,
    necessary_expense       boolean,
    financial_planning_id   bigint,
    category_id             bigint,
    data_atualizacao        timestamp,
    data_inclusao           timestamp,
    constraint fk_expense_financial_planning
        foreign key (financial_planning_id) references tb_financial_planning (financial_planning_id),
    constraint fk_expense_category
        foreign key (category_id) references tb_category (id)
);
