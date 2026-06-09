***INSERTS***

-- =========================
-- USUÁRIOS
-- =========================

INSERT INTO usuario_entity
(nome, email, senha, cargo, data_cadastro)
VALUES
('Maria Silva', 'maria@email.com', '123456', 'USUARIO', NOW()),
('João Santos', 'joao@email.com', '123456', 'USUARIO', NOW()),
('Ana Costa', 'ana@email.com', '123456', 'USUARIO', NOW()),
('Pedro Lima', 'pedro@email.com', '123456', 'USUARIO', NOW()),
('Carlos Souza', 'carlos@email.com', '123456', 'USUARIO', NOW()),
('Bibliotecária Julia', 'julia@bookme.com', '123456', 'BIBLIOTECARIO', NOW()),
('Administrador Sistema', 'admin@bookme.com', '123456', 'ADMINISTRADOR', NOW());

-- =========================
-- LIVROS
-- =========================

INSERT INTO livro_entity
(titulo, autor, isbn, quant_total, quant_disponivel)
VALUES
('Dom Casmurro', 'Machado de Assis', 9788503012300, 5, 3),
('Memórias Póstumas', 'Machado de Assis', 9788503012301, 4, 2),
('O Cortiço', 'Aluísio Azevedo', 9788503012302, 3, 3),
('Capitães da Areia', 'Jorge Amado', 9788503012303, 6, 4),
('Vidas Secas', 'Graciliano Ramos', 9788503012304, 4, 1),
('1984', 'George Orwell', 9788503012305, 8, 5),
('A Revolução dos Bichos', 'George Orwell', 9788503012306, 5, 2),
('O Hobbit', 'J.R.R. Tolkien', 9788503012307, 10, 8),
('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 9788503012308, 7, 4),
('Clean Code', 'Robert Martin', 9788503012309, 4, 3);

-- =========================
-- EMPRÉSTIMOS
-- =========================

INSERT INTO emprestimo_entity
(id_usuario, id_livro, data_emprestimo, data_devol_prevista, data_devol_real)
VALUES
(1, 1, NOW() - INTERVAL '5 days', CURRENT_DATE + 10, NULL),
(2, 2, NOW() - INTERVAL '8 days', CURRENT_DATE + 7, NULL),
(3, 5, NOW() - INTERVAL '20 days', CURRENT_DATE - 5, NULL),
(4, 6, NOW() - INTERVAL '15 days', CURRENT_DATE - 2, NOW() - INTERVAL '1 day');

-- =========================
-- RESERVAS
-- =========================

INSERT INTO reserva_entity
(id_usuario, id_livro, data_reserva, ordem_fila, status)
VALUES
(5, 5, NOW(), 1, 'ATIVA'),
(2, 5, NOW(), 2, 'ATIVA'),
(1, 6, NOW(), 1, 'ATIVA');

-- =========================
-- MULTAS
-- =========================

INSERT INTO multa_entity
(id_emprestimo, valor, tipo, pago)
VALUES
(3, 15.00, 'ATRASO', FALSE);



***ALTERAÇÕES***

ALTER TABLE usuario_entity ADD COLUMN telefone VARCHAR(15);

ALTER TABLE usuario_entity 
ALTER COLUMN nome VARCHAR(200);


***DELEÇÃO***

  
ALTER TABLE usuario_entity
DROP COLUMN telefone;
