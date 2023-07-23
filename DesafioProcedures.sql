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

-- CREATE TABLE nota(
	#	     Nome varchar(255),
    #        IdentificacaoCPF varchar(11) not null unique, 
    #        IdentificacaoCNPJ varchar(14),
    #        Categoria varchar(20),
    #        situacao varchar(45),
    #        Valor float,
    #        frete float 
# );
-- Get Nota

DROP PROCEDURE getNota;

-- DELIMITER \\

-- CREATE PROCEDURE getNota()
-- BEGIN
#	SELECT
#			Nome,
#            IdentificacaoCPF as CPF, 
#            IdentificacaoCNPJ as CNPJ,
#            Categoria,
#            situacao as ESTADO,
#            Valor,
#           frete as Valor_do_Frete
#		FROM
#			cliente c, pedido p, produto
#		WHWRE c.idCliente = p.idPedido
#		ORDER BY Nome;
# END\\
# DELIMITER ;
--
# CALL getNota();
-- 

-- PROCEDURE PARA CRIAR A NOTA FISCAL
DELIMITER //
CREATE PROCEDURE createNota()
   BEGIN
      create table nota
      (
			Nome varchar(255),
			IdentificacaoCPF varchar(11) not null unique, 
            IdentificacaoCNPJ varchar(14),
            Categoria varchar(20),
            situacao varchar(45),
            Valor float,
            frete float 
      );
   END//
   
DELIMITER ;
-- FIM

-- Chamando createNota();
CALL createNota();
-- Fim

-- Criando getNota()
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
			nota ;
END\\
DELIMITER ;

DROP PROCEDURE getNota;
CALL getNota();

-- Criando setNota();
-- Set Cliente
DROP PROCEDURE setNota;
DELIMITER //

CREATE PROCEDURE setNota(IN Nome varchar(255), 
								IN IdentificacaoCPF varchar(11), 
								IN IdentificacaoCNPJ varchar(14), 
								IN Categoria varchar(20),
								IN situacao varchar(45),
								IN Valor float,
								IN frete float  )
BEGIN
		INSERT INTO nota(Nome, IdentificacaoCPF, IdentificacaoCNPJ, Categoria, situaçao, Valor, frete) 
        VALUES (Nome, IdentificacaoCPF, IdentificacaoCNPJ, Categoria, situaçao, Valor, frete);
			
END//
DELIMITER ;

CALL setNota('Roberto Alves', '12245678914', '31325678123421', 'Eletrõnico', 'FECHADO', 495.68, 25.49);
-- Fim

-- Criando procedure deleteNota()
DROP PROCEDURE deleteNota;
DELIMITER //

CREATE PROCEDURE deleteNota(IN Nome varchar(255) )
BEGIN
		DELETE FROM nota n
        WHERE n.Nome = Nome;			
END//
DELIMITER ;

CALL deleteNota('Roberto Alves');
-- Fim

-- Criando procedure updateNota()
DROP PROCEDURE updateNota;
DELIMITER //

CREATE PROCEDURE updateNota(IN Valor float )
BEGIN
		UPDATE nota n
        SET n.Valor = 500.00;			
END//
DELIMITER ;

CALL updateNota(485.68);
-- Fim

-- Criando procedure dropNota()
DROP PROCEDURE dropNota;
DELIMITER //

CREATE PROCEDURE dropNota()
BEGIN
		DROP table nota;
END//
DELIMITER ;

CALL dropNota();
-- Fim

-- Criando uma procedure para criar, buscar, selecionar, atualizar e deletar da tabela nota()

-- Procedure para selecionar, criar ou deletar clientes
DROP PROCEDURE crudNota;
DELIMITER //

CREATE PROCEDURE crudNota(IN escolha INT)
BEGIN
		case escolha 
			when 1 then  CALL createNota();
            when 2 then  CALL getNota();
            when 3 then  CALL copiaParaNota();
            when 4 then  CALL setNota('Roberto Alves', '12245678914', '31325678123421', 'Eletrõnico', 'FECHADO', 495.68, 25.49);
            when 5 then  CALL updateNota(485.68);
            when 6 then  CALL deleteNota('Roberto Alves');
            when 7 then  CALL dropNota();
		end case;
END//
DELIMITER ;

CALL crudNota(3);

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

	SELECT
			Nome,
            IdentificacaoCPF as CPF, 
            IdentificacaoCNPJ as CNPJ,
            Categoria,
            situacao as ESTADO,
            Valor,
           frete as Valor_do_Frete
		FROM
			cliente c, pedido p, produto t
		WHERE c.idCliente = p.idPedido and p.idPedido = t.idProduto
		ORDER BY Nome;
        
-- Procedure copia para a tabela nota
DELIMITER //

CREATE PROCEDURE copiaParaNota()
BEGIN
    INSERT INTO nota (Nome, IdentificacaoCPF, IdentificacaoCNPJ, Categoria, situacao, Valor, frete)
    SELECT
        c.Nome,
        c.IdentificacaoCPF as CPF,
        c.IdentificacaoCNPJ as CNPJ,
        t.Categoria,
        p.situacao as ESTADO,
        t.Valor,
        p.frete as Valor_do_Frete
    FROM
        cliente c
    JOIN
        pedido p ON c.idCliente = p.idPedido
    JOIN
        produto t ON p.idPedido = t.idProduto
    ORDER BY
        Nome;
END //

DELIMITER ;


DROP PROCEDURE copiaParaNota;
CALL copiaParaNota();


SELECT * FROM nota;