INSERT INTO tb_user (name, username, cpf, email, password, birthdate, monthly_income, data_inclusao, data_atualizacao) VALUES
('Carlos Silva', 'carlossilva', '12345678901', 'carlos.silva@email.com', 'senha1234', '1985-04-12', 5500.00, NOW(), NOW()),
('Ana Souza', 'anasouza', '23456789012', 'ana.souza@email.com', 'senha1235', '1990-07-23', 7200.50, NOW(), NOW()),
('Marcos Oliveira', 'marcosoli', '34567890123', 'marcos.oliveira@email.com', 'senha1236', '1988-01-15', 3400.00, NOW(), NOW()),
('Fernanda Lima', 'felima', '45678901234', 'fernanda.lima@email.com', 'senha1237', '1995-11-30', 9800.00, NOW(), NOW()),
('Roberto Santos', 'betosantos', '56789012345', 'roberto.santos@email.com', 'senha1238', '1982-05-18', 4100.00, NOW(), NOW()),
('Juliana Costa', 'jucosta', '67890123456', 'juliana.costa@email.com', 'senha1239', '1998-09-09', 2500.00, NOW(), NOW()),
('Pedro Alves', 'pedrinho', '78901234567', 'pedro.alves@email.com', 'senha123', '2000-02-20', 3000.00, NOW(), NOW()),
('Mariana Dias', 'maridias', '89012345678', 'mariana.dias@email.com', 'senha1231', '1992-12-12', 6700.00, NOW(), NOW()),
('Lucas Pereira', 'lucasp', '90123456789', 'lucas.pereira@email.com', 'senha1232', '1987-03-25', 12000.00, NOW(), NOW()),
('Beatriz Rocha', 'biarocha', '01234567890', 'beatriz.rocha@email.com', 'senha1233', '1996-06-06', 4500.00, NOW(), NOW());

INSERT INTO tb_category (category_name, data_inclusao, data_atualizacao) VALUES
('Alimentação', NOW(), NOW()),
('Transporte', NOW(), NOW()),
('Moradia', NOW(), NOW()),
('Saúde', NOW(), NOW()),
('Educação', NOW(), NOW()),
('Lazer', NOW(), NOW()),
('Vestuário', NOW(), NOW()),
('Eletrônicos', NOW(), NOW()),
('Serviços', NOW(), NOW()),
('Investimentos', NOW(), NOW());

INSERT INTO tb_user_category (category_name, user_user_id, data_inclusao, data_atualizacao) VALUES
('Gastos Fixos', (SELECT user_id FROM tb_user WHERE username = 'carlossilva'), NOW(), NOW()),
('Reserva de Emergência', (SELECT user_id FROM tb_user WHERE username = 'carlossilva'), NOW(), NOW()),
('Viagem de Férias', (SELECT user_id FROM tb_user WHERE username = 'anasouza'), NOW(), NOW()),
('Despesas do Carro', (SELECT user_id FROM tb_user WHERE username = 'marcosoli'), NOW(), NOW()),
('Reforma da Casa', (SELECT user_id FROM tb_user WHERE username = 'felima'), NOW(), NOW()),
('Faculdade', (SELECT user_id FROM tb_user WHERE username = 'jucosta'), NOW(), NOW()),
('Lazer Fim de Semana', (SELECT user_id FROM tb_user WHERE username = 'pedrinho'), NOW(), NOW()),
('Investimento Ações', (SELECT user_id FROM tb_user WHERE username = 'lucasp'), NOW(), NOW()),
('Presentes', (SELECT user_id FROM tb_user WHERE username = 'betosantos'), NOW(), NOW()),
('Assinaturas', (SELECT user_id FROM tb_user WHERE username = 'maridias'), NOW(), NOW());

INSERT INTO tb_expense (name, description, price, date, necessary_expense, data_inclusao, data_atualizacao) VALUES
('Supermercado Semanal', 'Compras da semana no Extra', 450.00, '2023-10-01', true, NOW(), NOW()),
('Uber Trabalho', 'Corrida para o escritório', 24.90, '2023-10-02', true, NOW(), NOW()),
('Netflix', 'Mensalidade Streaming', 55.90, '2023-10-05', false, NOW(), NOW()),
('Conta de Luz', 'Cemig Referência Outubro', 180.50, '2023-10-10', true, NOW(), NOW()),
('Farmácia', 'Remédios para gripe', 85.00, '2023-10-12', true, NOW(), NOW()),
('Cinema', 'Ingresso + Pipoca', 120.00, '2023-10-15', false, NOW(), NOW()),
('Gasolina', 'Tanque cheio', 280.00, '2023-10-18', true, NOW(), NOW()),
('Curso Java', 'Mensalidade Udemy/Alura', 79.90, '2023-10-20', false, NOW(), NOW()),
('Jantar Fora', 'Restaurante Japonês', 250.00, '2023-10-25', false, NOW(), NOW()),
('Tênis Novo', 'Nike Air Force', 600.00, '2023-10-28', false, NOW(), NOW());

INSERT INTO tb_category_expense (category_id, expense_id) VALUES
((SELECT category_id FROM tb_category WHERE category_name = 'Alimentação'), (SELECT expense_id FROM tb_expense WHERE name = 'Supermercado Semanal')),
((SELECT category_id FROM tb_category WHERE category_name = 'Transporte'), (SELECT expense_id FROM tb_expense WHERE name = 'Uber Trabalho')),
((SELECT category_id FROM tb_category WHERE category_name = 'Lazer'), (SELECT expense_id FROM tb_expense WHERE name = 'Netflix')),
((SELECT category_id FROM tb_category WHERE category_name = 'Moradia'), (SELECT expense_id FROM tb_expense WHERE name = 'Conta de Luz')),
((SELECT category_id FROM tb_category WHERE category_name = 'Saúde'), (SELECT expense_id FROM tb_expense WHERE name = 'Farmácia')),
((SELECT category_id FROM tb_category WHERE category_name = 'Lazer'), (SELECT expense_id FROM tb_expense WHERE name = 'Cinema')),
((SELECT category_id FROM tb_category WHERE category_name = 'Transporte'), (SELECT expense_id FROM tb_expense WHERE name = 'Gasolina')),
((SELECT category_id FROM tb_category WHERE category_name = 'Educação'), (SELECT expense_id FROM tb_expense WHERE name = 'Curso Java')),
((SELECT category_id FROM tb_category WHERE category_name = 'Alimentação'), (SELECT expense_id FROM tb_expense WHERE name = 'Jantar Fora')),
((SELECT category_id FROM tb_category WHERE category_name = 'Vestuário'), (SELECT expense_id FROM tb_expense WHERE name = 'Tênis Novo'));