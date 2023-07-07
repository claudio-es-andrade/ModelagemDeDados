create database MeuEsquemaEcommerce;
use MeuEsquemaEcommerce;

create table pessoa(
	idPessoa int auto_increment primary key,
    tFisica boolean,
    tJuridica boolean
);

create table pagamento(
	idPagamento int auto_increment primary key,
    identificacao enum('Boleto', 'Cartão de Crédito', 'Cartão de Débito', 'Cheque', 'Pix', 'Conta Corrente')
    -- constraint fk_pagamento_cliente foreign key (idPagamento) references cliente(idCliente)
);

create table cliente(
	idCliente int auto_increment primary key,
    Nome varchar(255),
    Endereco varchar(45),
    IdentificacaoCPF varchar(11) not null unique,
    IdentificacaoCNPJ varchar(14)
    -- constraint fk_pessoa_cliente foreign key (idCliente) references pessoa(idPessoa)
);

create table pedido(
	idPedido int auto_increment primary key,
    situacao varchar(45),
    descricao varchar(45),
    frete float
    -- constraint fk_pedido_cliente foreign key (idPedido) references cliente(idCliente)
);

create table produto(
	idProduto int auto_increment primary key,
    Categoria varchar(20),
    Descricao varchar(255),
    Valor float
);

create table produtoPedido(
	idProdutoPedido_produto int,
    idProdutoPedido_pedido int,
	Quantidade int
    -- constraint fk_ProdutoPedido_Produto foreign key (idProdutoPedido_produto) references produto(idProduto),
    -- constraint fk_ProdutoPedido_Pedido foreign key (idProdutoPedido_pedido) references pedido(idPedido)
);

create table fornecedor(
	idFornecedor int auto_increment primary key,
	RazaoSocial varchar(100),
    IdentificacaoCNPJ varchar(14),
    Endereco varchar(45)
);

create table fornecedorProduto(
	idFornecedorProduto_fornecedor int,
    idFornecedorProduto_produto int,
	Quantidade int
 -- constraint fk_FornecedorProduto_Fornecedor foreign key (idFornecedorProduto_fornecedor) references fornecedor(idFornecedor),
 -- constraint fk_PornecedorProduto_Produto foreign key (idFornecedorProduto_produto) references produto(idProduto)
);

create table entrega(
	idEntrega int auto_increment primary key,
    codigoRastreio varchar(45),
    situacao varchar(45)
);

create table fornecedorEntrega(
	idFornecedorEntrega_entrega int,
    idFornecedorEntrega_fornecedor int,
	Descricao varchar(100)
    -- constraint fk_FornecedorEntrega_Entrega foreign key (idFornecedorEntrega_entrega) references entrega(idEntrega),
    -- constraint fk_PornecedorEntrega_Fornecedor foreign key (idFornecedorEntrega_fornecedor) references fornecedor(idFornecedor)
);

create table vendedorTerceirizado(
	idVendedorTerceirizado int auto_increment primary key,
	RazaoSocial varchar(100),
    IdentificacaoCNPJ varchar(14),
	Endereco varchar(45)
);

create table vendedorTerceirizadoProduto(
	idVendedorTerceirizadoProduto_vendedorTerceirizado int,
    idVendedorTerceirizadoProduto_produto int,
	Quantidade int
   -- constraint fk_VendedorTerceirizadoProduto_vendedorTerceirizado foreign key (idVendedorTerceirizadoProduto_vendedorTerceirizado) references vendedorTerceirizado(idVendedorTerceirizado),
   -- constraint fk_PendedorTerceirizadoProduto_Produto foreign key (idVendedorTerceirizadoProduto_produto) references produto(idProduto)
);

create table estoque(
	idEstoque int auto_increment primary key,
    Endereco varchar(45),
    Descricao varchar(100),
    Quantidade int
);

create table estoqueProduto(
	idEstoqueProduto_estoque int,
    idEstoqueProduto_produto int,
	Quantidade int
    -- constraint fk_EstoqueProduto_Estoque foreign key (idEstoqueProduto_estoque) references estoque(idEstoque),
    -- constraint fk_EstoqueProduto_Produto foreign key (idEstoqueProduto_produto) references produto(idProduto)

);
-- Atualizando...
create database MeuEsquemaEcommerce;
use MeuEsquemaEcommerce;
-- Persistência de dados
SELECT * FROM pessoa;
UPDATE pessoa
	SET tFisica=true
    WHERE idPessoa=4;
SELECT * FROM pessoa;
    
	
INSERT INTO pessoa(tFisica , tJuridica)
	VALUES(true, false),
	      (true, false),
          (true, true),
          (true, true);

INSERT INTO cliente(Nome, Endereco, IdentificacaoCPF, IdentificacaoCNPJ)
	VALUES('João', 'Rua 15 CEP23456789', '12345678911', 'Não Existe'),
          ('Maria Joana', 'Rua 17 CEP23456788', '32145678912', 'Não Existe');


select * from cliente;
drop table cliente;
UPDATE cliente
	SET IdentificacaoCNPJ='Não Existe'
    WHERE idCliente=4;

Update pedido
     Set frete = 90.0
     where idPedido = 1;

INSERT INTO cliente(Nome, Endereco, IdentificacaoCPF, identificacaoCNPJ, idCliente )
	VALUES ROW('João', 'Rua 15 CEP23456789', '12345678911', 'NÃO EXISTE', 1),
          ROW('Maria Joana', 'Rua 17 CEP23456788', '32145678912', 'NÃO EXISTE', 2),
          ROW('Alberto Som', 'Rua 14 CEP23456789', '12345678913', '12345678123412', 3),
		  ROW('Paulo Eletro', 'Rua 16 CEP23456788', '12345678914', '32145678123421', 4);

INSERT INTO cliente(Nome, Endereco, IdentificacaoCPF, identificacaoCNPJ, idCliente )
	VALUES ROW('Paulo Eletro', 'Rua 16 CEP23456788', '12345678914', '32145678123421', 4);


delete from cliente 
	where idCliente =4;

select * from pessoa;
select * from cliente;

describe produto;

INSERT INTO pagamento(idPagamento , identificacao )
	VALUES ROW(1, 'Boleto'),
		   ROW(2, 'Cartão de Crédito'),
           ROW(3, 'Conta Corrente'),
           ROW(4, 'Pix');
		
INSERT INTO produto(Categoria, Descricao, Valor)
	VALUES ROW('Eletrônicos', 'Micro-Ondas Pequeno', 675.50 ),
           ROW('Eletrônicos', 'Celular Android', 1500.50),
           ROW('Utensílios do Lar', 'Jogo de Panelas', 500.50),
		   ROW('Alimentos', 'Arroz Integral 5Kg', 25.70);

INSERT INTO produto(Categoria, Descricao, Valor)
	VALUES ROW('Utensílios do Lar', 'Jogo de Talheres', 45.50 );

INSERT INTO pedido(idPedido , situacao , descricao , frete)
	VALUES ROW(1, 'FECHADO', '3 Jogos de Panelas', 40.00),
           ROW(2, 'ABERTO', '2 Celulares Android', 20.00),
           ROW(3, 'FECHADO', 'Micro-Ondas Pequeno', 35.00),
           ROW(4, 'FECHADO', 'Jogo de Talheres', 15.00);

INSERT INTO estoque( Endereco , Descricao , Quantidade )
	VALUES ROW('Rua das Couves CEP12345-678', 'Micro-Ondas', 50),
		   ROW('Rua das Couves CEP12345-678', 'Celular Android', 100),
           ROW('Rua das Couves CEP12345-678', 'TV Led', 500),
           ROW('Rua do Agrião CEP12354-678', 'Jogo de Panelas', 80),
           ROW('Rua do Melão CEP45612-678', 'Arroz Integral 5Kg', 50);

INSERT INTO fornecedor(RazaoSocial, IdentificacaoCNPJ, Endereco)
	VALUES ROW('Xing Ling S.A.'   , '12345678123412', 'Rua dos Imigrantes 400'       ),
           ROW('Cobras S.A.'      , '45612345432131', 'Avenida Dr. Cesar Lattes 500' ),
           ROW('João Eletros S.A.', '32145678123412', 'Praça dos Pacificadores 678'  );

INSERT INTO vendedorTerceirizado(RazaoSocial , IdentificacaoCNPJ , Endereco )
	VALUES ROW('Jonas Alimentos S.A.'      , '12345678321413', 'Rua dos Tapajós 400'          ),
           ROW('Leve Calçados S.A.'        , '34589012456789', 'Avenida Dra. Marie Curie 900' ),
           ROW('Santos Alimentos S.A.'     , '98765432133412', 'Praça dos Juízes 978'         ),
           ROW('Neves Panelas S.A.'        , '12225432123412', 'Rua dos Expatriados 789'      ),
           ROW('Pioneira Eletrônica S.A.'  , '56781234234121', 'Praça dos Voluntários 10'     ),
           ROW('Maria Roupas S.A.'         , '11224567843211', 'Rua José da Silva 208'        );

INSERT INTO vendedorTerceirizadoProduto( idVendedorTerceirizadoProduto_vendedorTerceirizado , idVendedorTerceirizadoProduto_produto , Quantidade )
	VALUES ROW(1 , 1, 500 ),
           ROW(2 , 1, 600 ),
           ROW(3 , 2, 150 ),
           ROW(4 , 3, 890 ),
           ROW(5 , 4, 789 );

INSERT INTO entrega( codigoRastreio , situacao  )
		VALUES ROW('XYZ-12345', 'Em Trânsito'),
               ROW('ZZZ-45122', 'Em Trânsito'),
               ROW('XYZ-12345', 'Retornando para o Estoque'),
               ROW('XXZ-34566', 'Entregue');

INSERT INTO fornecedorEntrega( idFornecedorEntrega_entrega  , idFornecedorEntrega_fornecedor ,  Descricao )
		VALUES ROW(1 ,1 , '1 - Micro-Ondas'),
			   ROW(2 ,2 , '2 - Celulares Android'),
               ROW(3 ,3 , '1 - Jogo de Panelas');

INSERT INTO produtoPedido( idProdutoPedido_produto , idProdutoPedido_pedido , Quantidade )
	VALUES(1, 1, 3),
          (2, 2, 1),
          (3, 3, 1),
          (4, 4, 1);

INSERT INTO estoqueProduto( idEstoqueProduto_estoque ,  idEstoqueProduto_produto ,  Quantidade )
	VALUES(1, 1, 300),
          (2, 2, 100),
          (3, 3, 50),
          (4, 4, 140);

select * from pedido;
select * from produto;

select p.Categoria, p.Descricao , p.Valor
	from produto p
    inner join produtoPedido
		on idProdutoPedido_produto= p.idProduto
	inner join pedido
		on idPedido = idProdutoPedido_pedido;

select c.idCliente, c.Nome, c.identificacaoCPF, c.identificacaoCNPJ, ped.situacao, ped.descricao, ped.frete
	from cliente c , pedido ped
    where idPedido= c.idCliente;

select c.idCliente, c.Nome, c.identificacaoCPF, c.identificacaoCNPJ, ped.situacao, ped.descricao, ped.frete
	from cliente c , pedido ped
    where ped.situacao= 'FECHADO' ;

select * from cliente c;
select count(c.idCliente) as 'Total de Clientes'
	from cliente c;

select sum(ped.frete) as 'Gastos com Frete'
	from pedido ped;

select avg(ped.frete) as 'Média Geral do Frete'
	from pedido ped;

select c.idCliente, c.Nome, c.identificacaoCPF, c.identificacaoCNPJ, ped.situacao, ped.descricao, ped.frete
	from cliente c , pedido ped
    where c.idCliente=4;

select c.idCliente, c.Nome, c.identificacaoCPF, c.identificacaoCNPJ, ped.situacao, ped.descricao, ped.frete, pag.idPagamento, pag.identificacao
	from cliente c , pedido ped, pagamento pag
    where ped.idPedido = c.idCliente and pag.identificacao = 'Boleto';

select c.idCliente, c.Nome, c.identificacaoCPF, c.identificacaoCNPJ, ped.situacao, ped.descricao, ped.frete
	from cliente c , pedido ped
    where ped.situacao='FECHADO';
    
select c.idCliente, c.Nome, c.identificacaoCPF, c.identificacaoCNPJ, ped.situacao, ped.descricao, ped.frete
	from cliente c , pedido ped
    where ped.situacao='FECHADO' and ped.descricao='Jogo de Talheres';

select p.Categoria, p.Descricao , p.Valor
	from produto p
    inner join produtoPedido
		on idProdutoPedido_produto= p.idProduto
	where p.Categoria='Eletrônicos';

select p.Categoria, p.Descricao , p.Valor
	from produto p
    inner join produtoPedido
		on idProdutoPedido_produto= p.idProduto
	where p.Categoria='Eletrônicos' and p.Descricao='Micro-Ondas Pequeno';

select * from fornecedorEntrega;
select * from fornecedor;
select * from entrega;
select * from entrega
	where situacao='Entregue';

select * from fornecedor 
    inner join fornecedorEntrega
		on idfornecedorEntrega_fornecedor= idFornecedor;

select * from fornecedor;
select * from fornecedor
    group by idFornecedor;

select * from entrega;
select * from entrega
    group by idEntrega
    having codigoRastreio <> 'XYZ-12345';

select * from entrega
    where codigoRastreio = 'XYZ-12345';

select  f.RazaoSocial , f.identificacaoCNPJ from fornecedor f 
    inner join fornecedorEntrega
		on idfornecedorEntrega_fornecedor= idFornecedor;

select * from cliente
	inner join pessoa
		on idPessoa = idCliente
	having tJuridica <> 1;

select * from cliente
	inner join pessoa
		on idPessoa = idCliente
    order by Nome;
    
select * from cliente
	inner join pessoa
		on idPessoa = idCliente
    order by identificacaoCNPJ;

select * from cliente
	inner join pessoa
		on idPessoa = idCliente
    order by identificacaoCNPJ and Nome;
