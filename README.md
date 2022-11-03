# Modelagem & Criação de Banco de Dados
Repositório com o exemplos de prática de Modelagem de Dados utilizando o MySQL-Workbench.
	Modelagem e Criação do SGBD E-Commerce;
	Modelagem e Criação do SGBD Oficina.

Observação: 
    Na Entidade Pessoa os Atributos Física e Jurídica apesar de estarem definidos como TINYINT, são do tipo BOOLEAN, desta forma será possível definir quem vai atuar como cliente do E Commerce e;
    Na Entidade Pagamento o Atributo Identificação foi definido como VARCHAR devido não ser possível defini-lo como enum, considerando as limitações do sistema.
   
    Nas Entidades autorizacaoConserto e autorizacaoRevisao tanto da Modelagem como no SGBD as foreign keys do cliente, mecânico foram removidas.

Na primeira parte do conteúdo do script em SQL, foram criadas as Tabelas para cada Entidade Relacionada na Modelagem dos Sistemas .
Logo em seguida foram criadas as formas de persistências dos dados para finalizar na terceira parte com as demais relações e associações entre as mesmas.

