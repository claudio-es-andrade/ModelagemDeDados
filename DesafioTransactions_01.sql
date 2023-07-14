SELECT @@autocommit;
-- Desligar o autocommit=off, false or 0;
SET @@autocommit = 0;

use MeuEsquemaEcommerce;

-- Nota Fiscal como TRANSACTION
START TRANSACTION;
SELECT
			Nome,
            IdentificacaoCPF as CPF, 
            IdentificacaoCNPJ as CNPJ,
            Categoria,
            situacao as ESTADO,
            Valor,
            frete as Valor_do_Frete
		FROM
			cliente c 
            INNER JOIN pedido on idCliente = idPedido
		ORDER BY Nome;
COMMIT; 	

ROLLBACK;

 START TRANSACTION;
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
		WHERE Valor = Valor + (Valor * 0.10);
COMMIT; 	
--
START TRANSACTION;
	ROLLBACK;
	CALL crudNota(4);
COMMIT;
-- 