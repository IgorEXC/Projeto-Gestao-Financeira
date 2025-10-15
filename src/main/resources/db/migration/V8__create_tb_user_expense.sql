create table tb_user_expense
(
    user_id           bigint   not null,
    expense_id        bigint   not null,
    quantity          integer,
    data_atualizacao  timestamp,
    data_inclusao     timestamp,
    constraint tb_user_expense_pk
        primary key (user_id, expense_id),
    constraint fk_user_expense_user
        foreign key (user_id) references tb_user (user_id),
    constraint fk_user_expense_expense
        foreign key (expense_id) references tb_expense (id)
);
