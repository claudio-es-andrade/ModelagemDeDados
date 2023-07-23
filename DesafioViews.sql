use company;
describe employee;

-- Número de empregados por Departamento e Local
create or replace view employee_department_location_view as 
		select count(Dno) as TOTAL , d.Dname as DEPARTMENT , Dlocation as LOCATION
		from employee e
		inner join department d on e.Dno=d.Dnumber
        inner join dept_location l on d.Dnumber=l.Dlnumber
        group by d.Dname, l.Dlocation;

select * from employee;
select * from department;

select * from employee_department_location_view;


-- Lista de Departamentos e seus Gerentes
create or replace view list_department_managers_view as 
		select concat(Fname,' ', Minit,' ', Lname) as Employee, d.Dname as DEPARTMENT 
		from employee e
		inner join department d on e.Ssn = d.Mgr_ssn
        group by d.Dname;

select * from list_department_managers_view;

-- Projetos com maior número de empregados
create or replace view projectWithMostEmployees_view as 
		select count(Fname) as Employees, Pname as Project
		from employee e
		inner join works_on w on e.Ssn = w.Essn
        inner join project p on w.Pno = p.Pnumber
        group by Pname
        having count(Fname) > 2;

select * from projectWithMostEmployees_view;

-- Lista de projetos, departamentos e seus gerentes

create or replace view projectDepartmentManagers_view as 
		select concat(Fname,' ', Minit,' ', Lname) as MANAGER, Dname as DEPARTMENT, Pname as PROJECT 
		from employee e 
        inner join department d on e.Ssn = d.Mgr_ssn
        inner join project p on  p.Dnum = d.Dnumber
        group by Pname;

select * from projectDepartmentManagers_view;

-- Quais empregados possuem dependentes e se são gerentes

create or replace view projectManagersDependents_view as 
		select Fname as MANAGER, Dependent_name as DEPENDENTS 
		from employee e 
        inner join dependent s on  e.Ssn = s.Essn
        inner join department d on e.Ssn = d.Mgr_ssn  -- sem esta clausula, há mais funcionários, os que não são Gerentes
        group by MANAGER, DEPENDENTS ;

select * from projectManagersDependents_view;

CREATE USER 'manager_unic'@'localhost' IDENTIFIED BY 'SabnmsrhjKvsloPY!34#mgr_unic';
GRANT CREATE, SELECT ON *. * TO 'manager_unic'@'localhost';

use MeuEsquemaEcommerce;

CREATE OR REPLACE VIEW notaFiscalDasTabelas_view AS
		SELECT
			Nome,
            c.IdentificacaoCPF as CPF, 
            c.IdentificacaoCNPJ as CNPJ,
            t.Categoria,
            p.situacao as ESTADO,
            t.Valor,
            p.frete as Valor_do_Frete
		FROM
			cliente c, pedido p, produto t
		WHERE c.idCliente = p.idPedido and p.idPedido = t.idProduto
		ORDER BY Nome;
        
DROP VIEW notaFiscalDasTabelas_view;

SELECT * FROM notaFiscalDasTabelas_view;

SELECT Nome,Categorua,situacao,Valor,frete FROM notaFiscalDasTabelas_view;