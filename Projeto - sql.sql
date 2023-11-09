use Projeto_EC6

CREATE TABLE Login (
    LoginID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Senha VARCHAR(100) NOT NULL
);

CREATE TABLE Categorias (
    CategoriaID INT AUTO_INCREMENT PRIMARY KEY,
    NomeCategoria VARCHAR(50) NOT NULL
);

CREATE TABLE Ingredientes (
    IngredienteID INT AUTO_INCREMENT PRIMARY KEY,
    NomeIngrediente VARCHAR(100) NOT NULL,
    PrecoUnitario DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Produtos (
    ProdutoID INT AUTO_INCREMENT PRIMARY KEY,
    NomeProduto VARCHAR(100) NOT NULL,
    CategoriaID INT,
    CustoIngredientes DECIMAL(10, 2) NOT NULL,
    MargemLucro DECIMAL(5, 2) NOT NULL,
    PrecoFinal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (CategoriaID) REFERENCES Categorias (CategoriaID)
);

CREATE TABLE Compras (
    CompraID INT AUTO_INCREMENT PRIMARY KEY,
    DataCompra DATETIME NOT NULL,
    ValorTotal DECIMAL(10, 2) NOT NULL
);

CREATE TABLE DetalhesCompra (
    DetalheCompraID INT AUTO_INCREMENT PRIMARY KEY,
    CompraID INT,
    ProdutoID INT,
    Quantidade INT NOT NULL,
    PrecoUnitario DECIMAL(10, 2) NOT NULL,
    ValorTotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (CompraID) REFERENCES Compras (CompraID),
    FOREIGN KEY (ProdutoID) REFERENCES Produtos (ProdutoID)
);

CREATE TABLE ProdutosIngredientes (
    ProdutoID INT,
    IngredienteID INT,
    Quantidade DECIMAL(10, 2),
    PRIMARY KEY (ProdutoID, IngredienteID),
    FOREIGN KEY (ProdutoID) REFERENCES Produtos(ProdutoID),
    FOREIGN KEY (IngredienteID) REFERENCES Ingredientes(IngredienteID)
);

--Apenas pra testar no front: INSERT INTO Login (Username, Senha) VALUES ('ADMIN', '1234');

CREATE PROCEDURE sp_ValidarSenha
    @Username VARCHAR(50),
    @Senha VARCHAR(100)
AS
BEGIN
    DECLARE @SenhaArmazenada VARCHAR(100);

    -- Obter a senha armazenada no banco de dados para o usuário fornecido
    SELECT @SenhaArmazenada = Senha
    FROM Login
    WHERE Username = @Username;

    -- Verificar se a senha fornecida coincide com a senha armazenada
    IF @SenhaArmazenada IS NOT NULL AND @SenhaArmazenada = @Senha
    BEGIN
        PRINT 'Senha válida'; -- Pode ser substituído por outro tratamento desejado
    END
    ELSE
    BEGIN
        PRINT 'Senha inválida'; -- Pode ser substituído por outro tratamento desejado
    END;
END;

CREATE PROCEDURE sp_CadastrarLoginESenha
    @Username VARCHAR(50),
    @SenhaTexto VARCHAR(100)
AS
BEGIN
    DECLARE @LoginID INT;

    -- Inserir o login na tabela Login
    INSERT INTO Login (Username)
    VALUES (@Username);

    SET @LoginID = SCOPE_IDENTITY(); -- Obtém o ID do login recém-inserido

    -- Inserir a senha na tabela Senha, relacionando-a com o LoginID
    INSERT INTO Senha (LoginID, SenhaTexto)
    VALUES (@LoginID, @SenhaTexto);
END;

-- Stored Procedure para alterar a senha de um login
CREATE PROCEDURE sp_AlterarSenha
    @LoginID INT,
    @NovaSenhaTexto VARCHAR(100)
AS
BEGIN
    -- Atualizar a senha na tabela Senha associada ao LoginID
    UPDATE Senha
    SET
        SenhaTexto = @NovaSenhaTexto
    WHERE LoginID = @LoginID;
END;

CREATE PROCEDURE sp_CadastrarCategoria
    @NomeCategoria VARCHAR(50)
AS
BEGIN
    INSERT INTO Categorias (NomeCategoria)
    VALUES (@NomeCategoria);
END;

CREATE PROCEDURE sp_CadastrarIngrediente
    @NomeIngrediente VARCHAR(100),
    @PrecoUnitario DECIMAL(10, 2)
AS
BEGIN
    INSERT INTO Ingredientes (NomeIngrediente, PrecoUnitario)
    VALUES (@NomeIngrediente, @PrecoUnitario);
END;

CREATE PROCEDURE sp_CadastrarProduto
    @NomeProduto VARCHAR(100),
    @Categoria VARCHAR(50),
    @Ingredientes VARCHAR(MAX),
    @MargemLucro DECIMAL(5, 2)
AS
BEGIN
    -- Obter o ID da categoria
    DECLARE @CategoriaID INT;
    SELECT @CategoriaID = CategoriaID FROM Categorias WHERE NomeCategoria = @Categoria;

    -- Calcular o custo dos ingredientes
    DECLARE @CustoIngredientes DECIMAL(10, 2);
    SELECT @CustoIngredientes = SUM(PrecoUnitario) 
    FROM Ingredientes 
    WHERE NomeIngrediente IN (SELECT value FROM STRING_SPLIT(@Ingredientes, ','));

    -- Calcular o preço final
    DECLARE @PrecoFinal DECIMAL(10, 2);
    SET @PrecoFinal = @CustoIngredientes + (@CustoIngredientes * @MargemLucro / 100.0);

    -- Inserir o produto na tabela Produtos
    INSERT INTO Produtos (NomeProduto, CategoriaID, CustoIngredientes, MargemLucro, PrecoFinal)
    VALUES (@NomeProduto, @CategoriaID, @CustoIngredientes, @MargemLucro, @PrecoFinal);

    -- Associar ingredientes ao produto
    DECLARE @ProdutoID INT;
    SET @ProdutoID = SCOPE_IDENTITY();

    INSERT INTO ProdutosIngredientes (ProdutoID, IngredienteID, Quantidade)
    SELECT @ProdutoID, IngredienteID, 1.0
    FROM Ingredientes
    WHERE NomeIngrediente IN (SELECT value FROM STRING_SPLIT(@Ingredientes, ','));
END;


CREATE PROCEDURE sp_AdicionarAoCarrinho
    @CompraID INT,
    @ProdutoID INT,
    @Quantidade INT,
    @PrecoUnitario DECIMAL(10, 2)
AS
BEGIN
    INSERT INTO DetalhesCompra (CompraID, ProdutoID, Quantidade, PrecoUnitario, ValorTotal)
    VALUES (@CompraID, @ProdutoID, @Quantidade, @PrecoUnitario, @Quantidade * @PrecoUnitario);
END;

CREATE PROCEDURE sp_RemoverDoCarrinho
    @DetalheCompraID INT
AS
BEGIN
    DELETE FROM DetalhesCompra WHERE DetalheCompraID = @DetalheCompraID;
END;

CREATE PROCEDURE sp_AlterarQuantidadeNoCarrinho
    @DetalheCompraID INT,
    @Quantidade INT
AS
BEGIN
    UPDATE DetalhesCompra
    SET Quantidade = @Quantidade,
        ValorTotal = @Quantidade * PrecoUnitario
    WHERE DetalheCompraID = @DetalheCompraID;
END;

CREATE PROCEDURE sp_CancelarCompra
    @CompraID INT
AS
BEGIN
    DELETE FROM DetalhesCompra WHERE CompraID = @CompraID;
    DELETE FROM Compras WHERE CompraID = @CompraID;
END;

CREATE PROCEDURE sp_PesquisarProdutosPorNome
    @NomeProduto VARCHAR(100)
AS
BEGIN
    SELECT P.NomeProduto, C.NomeCategoria, PI.Ingredientes, P.CustoIngredientes, P.MargemLucro, P.PrecoFinal
    FROM Produtos P
    JOIN Categorias C ON P.CategoriaID = C.CategoriaID
    LEFT JOIN (
        SELECT PI.ProdutoID, STRING_AGG(I.NomeIngrediente, ', ') AS Ingredientes
        FROM ProdutosIngredientes PI
        JOIN Ingredientes I ON PI.IngredienteID = I.IngredienteID
        GROUP BY PI.ProdutoID
    ) PI ON P.ProdutoID = PI.ProdutoID
    WHERE P.NomeProduto LIKE '%' + @NomeProduto + '%';
END;


CREATE PROCEDURE sp_PesquisarProdutosPorCategoria
    @NomeCategoria VARCHAR(50)
AS
BEGIN
    SELECT P.NomeProduto, C.NomeCategoria, PI.Ingredientes, P.CustoIngredientes, P.MargemLucro, P.PrecoFinal
    FROM Produtos P
    JOIN Categorias C ON P.CategoriaID = C.CategoriaID
    LEFT JOIN (
        SELECT PI.ProdutoID, STRING_AGG(I.NomeIngrediente, ', ') AS Ingredientes
        FROM ProdutosIngredientes PI
        JOIN Ingredientes I ON PI.IngredienteID = I.IngredienteID
        GROUP BY PI.ProdutoID
    ) PI ON P.ProdutoID = PI.ProdutoID
    WHERE C.NomeCategoria = @NomeCategoria;
END;


EXEC sp_CadastrarCategoria @NomeCategoria = 'Bolos Tradicionais';

EXEC sp_CadastrarIngrediente @NomeIngrediente = 'Farinha', @PrecoUnitario = 2.50;

EXEC sp_CadastrarProduto 
    @NomeProduto = 'Bolo de Chocolate',
    @Categoria = 'Bolos Tradicionais',
    @Ingredientes = 'Farinha,Açúcar,Chocolate,Ovos,Leite',
    @MargemLucro = 30.00;
	
EXEC sp_AdicionarAoCarrinho
    @CompraID = 1,
    @ProdutoID = 2,
    @Quantidade = 3,
    @PrecoUnitario = 15.00;
	

EXEC sp_ValidarSenha @Username = 'ADMIN', @Senha = '1234';

EXEC sp_RemoverDoCarrinho [DetalheCompraID];

EXEC sp_AlterarQuantidadeNoCarrinho [DetalheCompraID], [NovaQuantidade];

EXEC sp_CancelarCompra [CompraID];

EXEC sp_PesquisarProdutosPorNome @NomeProduto = '[NomeProduto]';

EXEC sp_PesquisarProdutosPorCategoria @NomeCategoria = '[NomeCategoria]';

EXEC sp_CadastrarLoginESenha @Username = 'NovoUsuario', @SenhaTexto = 'NovaSenha';

EXEC sp_AlterarSenha @LoginID = 1, @NovaSenhaTexto = 'NovaSenhaAtualizada';