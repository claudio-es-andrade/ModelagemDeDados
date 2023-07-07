use MeuEsquemaEcommerce;

select * from cliente;
INSERT INTO cliente(Nome, Endereco, IdentificacaoCPF, IdentificacaoCNPJ)
	VALUES('João', 'Rua 15 CEP23456789', '12345678911', 'Não Existe');
       --   ('Maria Joana', 'Rua 17 CEP23456788', '32145678912', 'Não Existe');
   INSERT INTO cliente(Nome, Endereco, IdentificacaoCPF, identificacaoCNPJ, idCliente )
	VALUES ROW('Paulo Eletro', 'Rua 16 CEP23456788', '12345678914', '32145678123421', 4);       
--  Get Cliente

DELIMITER $$

CREATE PROCEDURE getCliente()
BEGIN
		SELECT
			Nome, 
            Endereco, 
            IdentificacaoCPF, 
            IdentificacaoCNPJ
		FROM
			cliente
		ORDER BY Nome;
END$$
DELIMITER ;

CALL getCliente();



-- Set Cliente
DROP PROCEDURE setCliente;
DELIMITER //

CREATE PROCEDURE setCliente(IN Nome varchar(255), IN Endereco varchar(45), IN IdentificacaoCPF varchar(11), IN IdentificacaoCNPJ varchar(14) )
BEGIN
		INSERT INTO cliente(Nome, Endereco, IdentificacaoCPF, IdentificacaoCNPJ) 
        VALUES (Nome, Endereco, IdentificacaoCPF, IdentificacaoCNPJ);
			
END//
DELIMITER ;

CALL setCliente('Joao Alves', 'Rua 15 CEP23456558', '13245678914', '31225678123421');

-- Delete Cliente

DROP PROCEDURE deleteCliente;
DELIMITER //

CREATE PROCEDURE deleteCliente(IN Nome varchar(255) )
BEGIN
		DELETE FROM cliente c
        WHERE c.Nome = Nome;			
END//
DELIMITER ;

CALL deleteCliente('João');
-- Get Nota

DROP PROCEDURE getNota;

DELIMITER \\

CREATE PROCEDURE getNota()
BEGIN
		SELECT
			Nome,
            IdentificacaoCPF as CPF, 
            IdentificacaoCNPJ as CNPJ,
            Categoria,
            situacao as ESTADO,
            Valor,
            frete as Valor_do_Frete
		FROM
			cliente c, pedido p, produto
		WHERE c.idCliente = p.idPedido
		ORDER BY Nome;
END\\
DELIMITER ;

CALL getNota();

/* select Nome, descricao,  situacao , frete from cliente c, pedido p
where c.idCliente = p.idPedido;
UPDATE employee
SET    Salary  =
CASE   WHEN   Dno = 5 THEN Salary + 2000
	   WHEN   Dno = 4 THEN Salary + 1500
       WHEN   Dno = 1 THEN Salary + 3000
       ELSE   Salary + 0
END  */

-- Procedure para selecionar, criar ou deletar clientes
DROP PROCEDURE getSetDelCliente;
DELIMITER //

CREATE PROCEDURE getSetDelCliente(IN escolha INT)
BEGIN
		case escolha 
			when 1 then  CALL getCliente(); 
            when 2 then  CALL setCliente('Joao Roberto', 'Rua 12 CEP23546558', '13246578914', '31225687123421');
            when 3 then  CALL deleteCliente('Joao Roberto');
		end case;
END//
DELIMITER ;

CALL getSetDelCliente(3);

