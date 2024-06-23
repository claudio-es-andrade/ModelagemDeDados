CREATE  database agenda;

create table agenda.contatos(
  id int not null auto_increment primary key,
  nome varchar(40),
  idade int,
  email varchar(40),
  dataCadastro date
);