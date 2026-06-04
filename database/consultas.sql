-- Livros disponíveis

SELECT *
FROM livro_entity
WHERE quant_disponivel > 0;

-- Livros emprestados

SELECT
    l.titulo,
    u.nome,
    e.data_emprestimo
FROM emprestimo_entity e
JOIN livro_entity l ON l.id = e.id_livro
JOIN usuario_entity u ON u.id = e.id_usuario
WHERE e.data_devol_real IS NULL;

-- Usuários com multas

SELECT
    u.nome,
    m.valor,
    m.tipo
FROM multa_entity m
JOIN emprestimo_entity e ON e.id = m.id_emprestimo
JOIN usuario_entity u ON u.id = e.id_usuario;

-- Histórico de empréstimos

SELECT
    u.nome,
    l.titulo,
    e.data_emprestimo,
    e.data_devol_prevista,
    e.data_devol_real
FROM emprestimo_entity e
JOIN usuario_entity u ON u.id = e.id_usuario
JOIN livro_entity l ON l.id = e.id_livro;
