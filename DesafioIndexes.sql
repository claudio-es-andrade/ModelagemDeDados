use company;

show index from employee;
select * from employee;
-- Qual o departamento com maior número de pessoas? 
CREATE INDEX index_max_pob_dept on employee(Dno);
EXPLAIN SELECT 
	max(Dno)
FROM
	employee
INNER JOIN department ON Dno = Dnumber
ORDER BY Dname;

-- Quais são os departamentos por cidade?
CREATE INDEX index_dept_location on dept_location(Dlocation);
EXPLAIN SELECT Dname as DEPARTMENT, Dlocation as CITY FROM department d, dept_location l
WHERE l.DLnumber=d.Dnumber;


-- Relação de empregados por departamento 
CREATE INDEX index_number_employee_dept on employee(Dno);
EXPLAIN SELECT 
	count(Dno) as No_Employess, Dname as Department
FROM
	employee e , department d
WHERE e.Dno = d.Dnumber
GROUP BY Dname;

-- Maiores Salários por Departamento
CREATE INDEX index_max_sal_dept on employee(Salary);
EXPLAIN SELECT 
	max(Salary) as Salary, Dname as DEPARTMENT
FROM
	employee e  , department d
WHERE  e.Dno = d.Dnumber
GROUP BY Dname;

-- Empregados com o Menor número de dependentes
describe dependent;
select * from dependent;
CREATE INDEX index_min_no_dependent on employee(Fname);
EXPLAIN SELECT 
	Fname as "Employee Name" ,
    min(Relationship) as DEPENDENT
FROM
	employee e, dependent s
WHERE e.Ssn = s.Essn
GROUP BY Fname;

-- Projeto com maior número de horas
describe works_on;
select * from works_on;

CREATE INDEX index_max_no_hour on works_on(Hours);
EXPLAIN SELECT 
	max(Hours) as Max_Hours, Pname as Project
FROM
	works_on w
INNER JOIN employee e ON e.Ssn = w.Essn
INNER JOIN project p ON   w.Pno = p.Pnumber 
GROUP BY Pname;

-- Projeto com menor número de horas
describe works_on;
select * from works_on;

CREATE INDEX index_min_no_hour on works_on(Hours);
EXPLAIN SELECT 
	min(Hours) as Min_Hours, Pname as Project
FROM
	works_on w
INNER JOIN employee e ON e.Ssn = w.Essn
INNER JOIN project p ON   w.Pno = p.Pnumber 
GROUP BY Pname;

-- Empregados do Departamento de uma determinada cidade e seu gerente
CREATE INDEX index_dept_city on dept_location(Dlocation);
EXPLAIN SELECT 
	Fname, Dname AS DEPARTMENT, Mgr_ssn AS MANAGER
FROM department d, dept_location l, employee e
WHERE d.Dnumber=l.DLnumber AND Dlocation='Stanfford';

DROP INDEX index_max_pob_dept on employee;
DROP INDEX index_dept_location on dept_location;
DROP INDEX index_number_employee_dept on employee;