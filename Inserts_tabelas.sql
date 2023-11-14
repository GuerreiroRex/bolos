INSERT INTO Categorias (NomeCategoria) VALUES
('Bolo Simples'),
('Bolo Recheado'),
('Bolo Decorado');


INSERT INTO Ingredientes (NomeIngrediente, PrecoUnitario) VALUES
('Farinha de Trigo', 3.50),
('Açúcar', 2.00),
('Ovos', 0.50),
('Leite', 1.50),
('Fermento em Pó', 1.00),
('Manteiga', 4.00),
('Chocolate em Pó', 5.50),
('Essência de Baunilha', 2.50),
('Sal', 0.25);


INSERT INTO Produtos (NomeProduto, CategoriaID, CustoIngredientes, MargemLucro, PrecoFinal) VALUES
('Bolo de Chocolate Simples', 1, 5.50, 2.00, 7.50),
('Bolo de Chocolate Recheado', 2, 5.50, 3.00, 8.50),
('Bolo Decorado com Morangos', 3, 8.00, 4.00, 12.00),
('Bolo de Baunilha Simples', 1, 4.00, 2.00, 6.00),
('Bolo Red Velvet Recheado', 2, 7.00, 3.50, 10.50),
('Bolo de Casamento Decorado', 3, 15.00, 6.00, 21.00);


INSERT INTO Compras (DataCompra, ValorTotal) VALUES
('2023-11-12 10:00:00', 35.00),
('2023-11-13 15:30:00', 50.00);


INSERT INTO DetalhesCompra (CompraID, ProdutoID, Quantidade, PrecoUnitario, ValorTotal) VALUES
(1, 1, 2, 7.50, 15.00),
(1, 3, 1, 12.00, 12.00),
(2, 5, 3, 10.50, 31.50);


INSERT INTO ProdutosIngredientes (ProdutoID, IngredienteID, Quantidade) VALUES
(1, 1, 2),
(1, 2, 1),
(1, 3, 3);