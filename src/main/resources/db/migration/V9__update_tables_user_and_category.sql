alter table tb_user
    add predicted_category_limit decimal;

alter table tb_category
      drop column predicted_category_limit;