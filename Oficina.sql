create database SchemaOficina;
use SchemaOficina;

create table veiculo(
	idVeiculo int auto_increment primary key,
    Itens varchar(255),
    TipoVeiculo enum('Utilitários','Pickup','Carro de Passeio'),
    TipoMotor enum('Diesel', 'Gasolina','Flex', 'Gás', 'Híbrido', 'Elétrico'),
    constraint fk_veiculo_cliente foreign key (idVeiculo) references cliente(idCliente)
);

create table envioServico(
	idEnvioServico_Cliente int auto_increment primary key,
    idEnvioServico_Oficina int ,
    Quantidade int,
    constraint fk_envioServico_cliente foreign key (idEnvioServico_Cliente) references cliente(idCliente),
    constraint fk_envioServico_oficina foreign key (idEnvioServico_Oficina) references oficina(idOficina)
);

create table cliente(
	idCliente int auto_increment primary key,
    Nome varchar(255),
    Telefone varchar(11),
    Quantidade int
);

create table ordemServico(
	idOrdemServico int auto_increment primary key,
    DataEmissao datetime,
    Itens varchar(255),
    MaoDeObra float,
    TipoServico varchar(45),
    Autorizacao boolean,
    EstatusServico enum('Inicio','Parado','Fase de Término','Esperando Peça','Outros'),
    ValorTotal float,
    DataPrevistaConclusao date,
    constraint fk_ordemServico_cliente foreign key (idOrdemServico) references cliente(idCliente)
);

create table oficina(
	idOficina int auto_increment primary key,
    Funcionarios int
);

create table mecanico(
	idMecanico int auto_increment primary key,
    Nome varchar(255),
    Endereco varchar(255),
    Especializacao varchar(255),
    constraint fk_mecanico_oficina foreign key (idMecanico) references oficina(idOficina)
);

create table analiseConserto(
	idAnaliseConserto_idMecanico int auto_increment,
    idAnaliseConserto_idOficina int ,
    idAnaliseConserto_idConserto int ,
    DescricaoServico varchar(255),
    constraint fk_analiseConserto_mecanico foreign key (idAnaliseConserto_idMecanico) references mecanico(idMecanico),
    constraint fk_analiseConserto_oficina foreign key (idAnaliseConserto_idOficina) references oficina(idOficina),
    constraint fk_analiseConserto_conserto foreign key (idAnaliseConserto_idConserto) references conserto(idConserto)
);

create table conserto(
	idConserto int auto_increment primary key,
    DescricaoServico varchar(255)
);

create table revisao(
	idRevisao int auto_increment primary key,
    DescricaoServico varchar(255)
);

create table analiseRevisao(
	idAnaliseRevisao_idMecanico int ,
    idAnaliseRevisao_idOficina int ,
    idAnaliseRevisao_idRevisao int auto_increment primary key,
    DescricaoServico varchar(255),
    constraint fk_analiseRevisao_mecanico foreign key (idAnaliseRevisao_idMecanico) references mecanico(idMecanico),
    constraint fk_analiseRevisao_oficina foreign key (idAnaliseRevisao_idOficina) references oficina(idOficina),
    constraint fk_analiseRevisao_revisao foreign key (idAnaliseRevisao_idRevisao) references revisao(idRevisao)
);

create table autorizacaoConserto(
	idAutorizacaoConserto_idOrdemServico int auto_increment primary key,
	idAutorizacaoConserto_idOficina int ,
    idAutorizacaoConserto_idConserto int ,
    constraint fk_AutorizacaoConserto_ordemServico foreign key (idAutorizacaoConserto_idOrdemServico) references ordemServico(idOrdemServico),
    constraint fk_AutorizacaoConserto_oficina foreign key (idAutorizacaoConserto_idOficina) references oficina(idOficina),
    constraint fk_AutorizacaoConserto_conserto foreign key (idAutorizacaoConserto_idConserto) references conserto(idConserto)
);

create table autorizacaoRevisao(
	idAutorizacaoRevisao_idOrdemServico int auto_increment primary key,
	idAutorizacaoRevisao_idOficina int ,
    idAutorizacaoRevisao_idRevisao int ,
    constraint fk_AutorizacaoRevisao_ordemServico foreign key (idAutorizacaoRevisao_idOrdemServico) references ordemServico(idOrdemServico),
    constraint fk_AutorizacaoRevisao_oficina foreign key (idAutorizacaoRevisao_idOficina) references oficina(idOficina),
    constraint fk_AutorizacaoRevisao_Revisaoconserto foreign key (idAutorizacaoRevisao_idConserto) references revisao(idConserto)
);

INSERT INTO cliente( idCliente , Nome , Telefone , Quantidade)
	VALUES (1, 'João das Couves','21980000001', 2),
           (2, 'Rodolfo da Silva','21980000002', 3),
           (3, 'Carlos Junior','21980000003', 1),
           (4, 'Fabio Souza','21980000004', 4);

INSERT INTO veiculo(idVeiculo, Itens, TipoVeiculo, TipoMotor)
	VALUES ROW(1, 'Motor parte Elétrica','Carro de Passeio','Flex'),
           ROW(2, 'Caixa de Marcha','Utilitários','Diesel'),
           ROW(3, 'Cabo de Vela','Pickup','Elétrico'),
		   ROW(4, 'Amortecedores','Carro de Passeio','Gasolina');

INSERT INTO oficina( idOficina , Funcionarios)
	VALUES (1,3),
		   (2,6),
           (3,4),
           (4,2),
           (5,2);

INSERT INTO envioServico( idEnvioServico_Cliente , idEnvioServico_Oficina , Quantidade )
	VALUES (1,1,1),
		   (2,3,6),
           (3,4,1),
           (4,5,1);

INSERT INTO  mecanico( idMecanico , Nome  , Endereco , Especializacao )
	VALUES (1, 'João Silva', 'Rua 5...', 'Eletricista'),
           (2, 'Jonas Couto', 'Rua do Sal...', 'Eletricista'),
           (3, 'João Jorge', 'Avenida 5 de...', 'Mecânico'),
           (4, 'Jorge Silva', 'Alameda...', 'Mecânico');
           -- (5, 'João Alves', 'Alameda Vieira ...', 'Mecânico'),
           -- (6, 'Jean Gomes', 'Avenida ...', 'Mecânico'),
           -- (7, 'Jonatas Souto', 'Rua Alegre...', 'Mecânico'),
           -- (8, 'John Winter', 'Magic Street ...', 'Instructor');

INSERT INTO ordemServico( idOrdemServico , DataEmissao , Itens , MaoDeObra  , TipoServico , Autorizacao , EstatusServico , ValorTotal , DataPrevistaConclusao )
	VALUES (1, '2022-05-01', '1- Filtro de Ar condicionado...', 2500.00, 'Troca de ...', true, 'Início', 5000.00, '2022-06-01'),
		   (2, '2022-06-03', '1- Lanterna Esquerda Dianteira...', 5000.00, 'Inspeção ...', true, 'Esperando Peça', 10000.00, '2022-07-03'),
           (3, '2022-06-04', '1- Amortecedor Dianteiro de Esquerda...', 5000.00, 'Retirada ...', true, 'Fase de Término', 9000.00, '2022-06-06'),
           (4, '2022-06-05', '1- Troca do Radiador ...', 7000.00, 'Limpeza ...', false, 'Parado', 25000.00, '2022-06-06');
        -- (5, '2022-06-06', '1- Reparo no Chicote Elétrico do Amplificador ...', 10000.00, 'Limpeza ...', true, 'Outros', 5000.00, '2022-06-07');
	
INSERT INTO conserto( idConserto , DescricaoServico)
	VALUES (1, 'Troca de Escapamento'),
	       (2, 'Balanceamento & Alinhamento'),
           (3, 'Troca da Transmissão'),
           (4, 'Troca de pneus + Balanceamento & Alinhamento'),
           (5, 'Troca dos Vidros'),
           (6, 'Troca de Pastilhas');    
    
INSERT INTO analiseConserto( idAnaliseConserto_idMecanico , idAnaliseConserto_idOficina , idAnaliseConserto_idConserto , DescricaoServico)
	VALUES (1,1,1,'Troca de Escapamento'),
           (2,1,2,'Balanceamento'),
           (3,1,3,'Troca de Transmissão'),
           (4,1,4,'Alinhamento');
           -- (5,1,5,'Troca dos Vidros'),
           -- (6,4,6,'Troca das Lanternas');

INSERT INTO revisao( idRevisao  , DescricaoServico)
	VALUES (1, 'Análise das Pastilhas dos Freios'),
           (2, 'Condições dos pneus e amortecedores'),
           (3, 'Troca de filtro de Óleo e óleo'),
           (4, 'Troca dos Pneus'),
           (5, 'Inspeção da Iluminação'),
           (6, 'Inspeção da Parte Elétrica');

INSERT INTO analiseRevisao( idAnaliseRevisao_idMecanico , idAnaliseRevisao_idOficina  , idAnaliseRevisao_idRevisao , DescricaoServico)
	VALUES (1,1,2,'Inspeção Escapamento'),
           (2,1,1,'Inspeção Eletrônica'),
           (3,1,3,'Sistema de Refrigeração'),
           (4,1,4,'Sistema de Ar-Condicionado');
           -- (5,1,5, 'Sistema de Frenagem'),
           -- (6,4,6,'Iluminação');

drop table autorizacaoConserto;
drop table autorizacaoRevisao;

create table autorizacaoConserto(
	idAutorizacaoConserto_idOrdemServico int auto_increment primary key,
	idAutorizacaoConserto_idOficina int ,
    idAutorizacaoConserto_idConserto int ,
    constraint fk_AutorizacaoConserto_ordemServico foreign key (idAutorizacaoConserto_idOrdemServico) references ordemServico(idOrdemServico),
    constraint fk_AutorizacaoConserto_oficina foreign key (idAutorizacaoConserto_idOficina) references oficina(idOficina),
    constraint fk_AutorizacaoConserto_conserto foreign key (idAutorizacaoConserto_idConserto) references conserto(idConserto)
);

create table autorizacaoRevisao(
	idAutorizacaoRevisao_idOrdemServico int auto_increment primary key,
	idAutorizacaoRevisao_idOficina int ,
    idAutorizacaoRevisao_idRevisao int ,
    constraint fk_AutorizacaoRevisao_ordemServico foreign key (idAutorizacaoRevisao_idOrdemServico) references ordemServico(idOrdemServico),
    constraint fk_AutorizacaoRevisao_oficina foreign key (idAutorizacaoRevisao_idOficina) references oficina(idOficina),
    constraint fk_AutorizacaoRevisao_revisao foreign key (idAutorizacaoRevisao_idRevisao) references revisao(idRevisao)
);

INSERT INTO autorizacaoRevisao(idAutorizacaoRevisao_idOficina , idAutorizacaoRevisao_idRevisao)
	VALUES (1,1),
           (1,2),
           (3,3),
           (2,3);

INSERT INTO autorizacaoConserto(idAutorizacaoConserto_idOficina, idAutorizacaoConserto_idConserto)
	VALUES (1,1),
           (1,2),
           (3,3),
           (2,3);

show schemas;
show tables;

select * from cliente c;
select count(c.idCliente) as 'Total de Clientes'
	from cliente c;

select c.idCliente, c.Nome, c.Telefone, c.Quantidade, v.TipoVeiculo
	from cliente c , veiculo v
    where idVeiculo= c.idCliente;

select c.idCliente, c.Nome, c.Telefone, c.Quantidade, v.TipoVeiculo
	from cliente c , veiculo v
    where TipoVeiculo = 'Carro de Passeio' AND c.Quantidade = 2;

select *
	from ordemServico os, cliente c;

select *
	from ordemServico os, cliente c
    inner join ordemServico
    on idOrdemServico = c.idCliente;

select *
	from ordemServico os, cliente c
    inner join ordemServico
    on idOrdemServico = c.idCliente
    where os.Autorizacao = true;

select *
	from ordemServico os, cliente c
    inner join ordemServico
    on idOrdemServico = c.idCliente
    where os.Autorizacao = true and os.DataPrevistaConclusao = '22-06-01';

select * 
	from cliente c
    left join ordemServico
    on idOrdemServico = c.idCliente;

select * 
	from cliente c
    left join ordemServico
    on idOrdemServico = c.idCliente
    inner join autorizacaoConserto
    on idAutorizacaoConserto_idOrdemServico = idOrdemServico;
    
 select * 
	from cliente c
    left join ordemServico
    on idOrdemServico = c.idCliente
    inner join autorizacaoConserto
    on idAutorizacaoConserto_idOrdemServico = idOrdemServico
    where Autorizacao = true;

select * 
	from cliente c
    left join ordemServico
    on idOrdemServico = c.idCliente
    inner join autorizacaoConserto
    on idAutorizacaoConserto_idOrdemServico = idOrdemServico
    where Autorizacao = true and EstatusServico = 'Fase de Término';

select * 
	from cliente c
    left join ordemServico
    on idOrdemServico = c.idCliente
    inner join autorizacaoConserto
    on idAutorizacaoConserto_idOrdemServico = idOrdemServico
    where Autorizacao = true and Itens = '1- Filtro de Ar condicionado...';

select * 
	from cliente c
    left join ordemServico
    on idOrdemServico = c.idCliente
    inner join autorizacaoConserto
    on idAutorizacaoConserto_idOrdemServico = idOrdemServico
    having MaoDeObra > 2000.00;
    
select * 
	from cliente c
    left join ordemServico
    on idOrdemServico = c.idCliente
    inner join autorizacaoConserto
    on idAutorizacaoConserto_idOrdemServico = idOrdemServico
    having MaoDeObra > 2000.00
    order by EstatusServico;

select * 
	from cliente c
    right join ordemServico
    on idOrdemServico = c.idCliente
    inner join autorizacaoConserto
    on idAutorizacaoConserto_idOrdemServico = idOrdemServico
    having MaoDeObra > 2000.00;

select * 
	from ordemServico os;

select * 
	from ordemServico os
    group by idOrdemServico;

select * 
	from cliente c
    inner join ordemServico
    on idOrdemServico = c.idCliente
    group by idOrdemServico;

select sum(os.MaoDeObra) as 'Total de Gastos com Mão de Obra'
	from ordemServico os;

select avg(os.MaoDeObra) as 'Média de Gastos com Mão de Obra'
	from ordemServico os;

select count(c.idCliente) as 'Total de Clientes'
	from cliente c;

select * from analiseConserto;

select * from analiseConserto
	inner join mecanico
    on idMecanico = idAnaliseConserto_idMecanico;

select * from analiseConserto
	inner join mecanico
    on idMecanico = idAnaliseConserto_idMecanico
    where Especializacao = 'Eletricista';

select * from analiseConserto
	inner join mecanico
    on idMecanico = idAnaliseConserto_idMecanico
    where Especializacao = 'Mecânico' and Nome = 'João Jorge';