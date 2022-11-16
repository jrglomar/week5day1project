INSERT INTO USERS(id, username, password, role) VALUES(1, 'admin', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'ADMIN');
INSERT INTO USERS (id,username, password,role) VALUES (2,'user', '$2a$04$PCIX2hYrve38M7eOcqAbCO9UqjYg7gfFNpKsinAxh99nms9e.8HwK','USER');

INSERT INTO voucher(id, number, amount, description, type) VALUES (1,123, 24, 'This is voucher 1', 'CASH');
INSERT INTO voucher(id, number, amount, description, type) VALUES (2, 456, 44, 'This is voucher 2', 'CASH');
INSERT INTO voucher(id, number, amount, description, type) VALUES (3, 789, 64, 'This is voucher 3', 'CHECK');

