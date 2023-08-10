CREATE DATABASE movieCatalog;

use movieCatalog;
DROP TABLE movie;

CREATE TABLE movie(
	id       int primary key not null auto_increment,
    type     int not null,
    name     varchar(30) not null,
	total_ep  int,
    atual_ep  int,
    last_view date
);

insert into movie(id, type, name, total_ep, atual_ep, last_view)
					values(1, 0, 'Friends', 10, 1, '2023-05-25' ),
                          (2, 1, 'Avengers', 0 , 0, '2023-10-25' ),
                          (3, 0, 'House', 100, 1, '2023-02-26'),
                          (4, 0, '2 and a half man', 300, 2, '2023-06-25'  ),
                          (5, 1, 'Justice League', 0 , 0 , '2023-07-01' ),
                          (6, 1, 'Men in Black', 0, 0 , '2023-01-01' ),
                          (7, 1, 'Wonder Woman', 0, 0, '2023-02-01' ),
                          (8, 1, 'Spiderman', 0, 0, '2023-09-28' ),
                          (9, 1, 'Titanic', 0, 0, '2023-11-20' ),
                          (10, 1, 'King Kong', 0, 0, '2023-12-20' ),
                          (11, 1, 'Godzilla', 0, 0, '2023-04-25' ),
                          (12, 0, 'Ultraman', 45, 2, '2023-05-2');
                          

select * from movie;

ALTER TABLE movie
DROP column atual_ep,
DROP column total_ep,
DROP column last_view;
