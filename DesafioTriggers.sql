drop database MeuEsquemaEcommerce;
use MeuEsquemaEcommerce;

-- Criação de uma tabela Backup de clientes
create table clienteArquivo(
		idCliente int auto_increment primary key,
		Nome varchar(255),
		Endereco varchar(45),
		IdentificacaoCPF varchar(11) not null unique,
		IdentificacaoCNPJ varchar(14));
		-- constraint fk_pessoa_clienteArquive foreign key (idClienteA) references pessoa(idPessoa) );
-- cliente(Nome, Endereco, IdentificacaoCPF, identificacaoCNPJ, idCliente)
drop table clienteArquivo;
select * from clienteArquivo;
-- Triggers de remoção: before delete

drop TRIGGER delete_users_check ;

DELIMITER $$
CREATE  TRIGGER delete_users_check 
before delete  on cliente
FOR EACH ROW
BEGIN	
    INSERT INTO clienteArquivo(Nome, Endereco, IdentificacaoCPF, identificacaoCNPJ, idCliente)
    VALUES(OLD.Nome, OLD.Endereco, OLD.IdentificacaoCPF, OLD.IdentificacaoCNPJ, OLD.Idcliente);
END$$    

DELIMITER ;

INSERT INTO cliente(Nome, Endereco, IdentificacaoCPF, identificacaoCNPJ, idCliente )
	VALUES ROW('Paulo Eletro', 'Rua 16 CEP23456788', '12345678914', '32145678123421', 4);


delete from cliente 
	where idCliente =4;

-- Triggers de atualização: before update
-- Criação da Tabela pedidoArquivo para o salvamento antes do update

create table pedidoArquivo(
	idPedido int auto_increment primary key,
    situacao varchar(45),
    descricao varchar(45),
    frete float
);

select* from pedido;

drop trigger before_pedido_update;


DELIMITER $$

CREATE TRIGGER before_pedido_update
BEFORE UPDATE
ON pedido FOR EACH ROW
BEGIN
    DECLARE errorMessage VARCHAR(255);
    SET errorMessage = CONCAT('O novo valor do frete ',
                        NEW.frete,
                        ' não pode execeder 1.5 vezes o valor do valor antigo ',
                        OLD.frete);
                        
    IF new.frete > old.frete * 1.5 THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = errorMessage;
    END IF;
END $$

DELIMITER ;


Update pedido
     Set frete = 90.0
     where idPedido = 1; 

--  After update

DROP TABLE IF EXISTS produtoUpdate;
select * from produtoUpdate; 

CREATE TABLE produtoUpdate (
    idProduto INT AUTO_INCREMENT PRIMARY KEY,
    Categoria VARCHAR(20),
    beforeValor FLOAT,
    afterValor FLOAT,
    changedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

UPDATE produto
	SET valor = 1500.50
    WHERE idProduto=3;

select * from produto;


INSERT INTO produto(Categoria, Descricao, Valor)
	VALUES ROW('Eletrônicos', 'Micro-Ondas Pequeno', 675.50 ),
           ROW('Eletrônicos', 'Celular Android', 1500.50),
           ROW('Utensílios do Lar', 'Jogo de Panelas', 500.50),
		   ROW('Alimentos', 'Arroz Integral 5Kg', 25.70);
           
DELIMITER $$

CREATE TRIGGER afterProduto_update
AFTER UPDATE
ON produto FOR EACH ROW
BEGIN
    IF OLD.Valor <> new.Valor THEN
        INSERT INTO produtoUpdate(IdProduto ,beforeValor, afterValor)
        VALUES(old.idProduto, old.Valor, new.Valor);
    END IF;
END$$

DELIMITER ;