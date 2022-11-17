INSERT INTO voucher(number, amount, description, type) VALUES (123, 24, 'This is voucher 1', 'CASH');
INSERT INTO voucher(number, amount, description, type) VALUES (456, 44, 'This is voucher 2', 'CASH');
INSERT INTO voucher(number, amount, description, type) VALUES (789, 64, 'This is voucher 3', 'CHECK');

INSERT INTO USERS (id, user_name, password,role) VALUES (1, 'admin', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu','ADMIN');
INSERT INTO USERS (id, user_name, password,role) VALUES (2, 'user', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu','USER');