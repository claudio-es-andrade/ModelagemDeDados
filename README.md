# Modelagem & Criação de Banco de Dados
Repositório com o exemplos de prática de Modelagem de Dados utilizando o MySQL-Workbench.
	Modelagem de E-Commerce e Criação do seu SGBD;
	Modelagem Oficina.

Observação: 
    Na Entidade Pessoa os Atributos Física e Jurídica apesar de estarem definidos como TINYINT, são do tipo BOOLEAN, desta forma será possível definir quem vai atuar como cliente do E Commerce e;
    Na Entidade Pagamento o Atributo Identificação foi definido como VARCHAR devido não ser possível defini-lo como enum, considerando as limitações do sistema.

Na primeira parte do conteúdo do script em SQL, foram criadas as Tabelas para cada Entidade Relacionada na Modelagem do Sistema de E-Commerce.
Logo em seguida foram criadas as formas de persistências dos dados para finalizar na terceira parte com as demais relações e associações entre as mesmas.

