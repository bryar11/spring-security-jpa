INSERT INTO clients (name, lastname, email, create_at) VALUES('John', 'Doe', 'john.doe@gmail.com', '2022-01-01');
INSERT INTO clients (name, lastname, email, create_at) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2022-01-02');
INSERT INTO clients (name, lastname, email, create_at) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2022-01-03');
INSERT INTO clients (name, lastname, email, create_at) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2022-01-04');
INSERT INTO clients (name, lastname, email, create_at) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2022-01-05');
INSERT INTO clients (name, lastname, email, create_at) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2022-01-06');
INSERT INTO clients (name, lastname, email, create_at) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2022-01-07');

INSERT INTO users (id, username, password, enabled) VALUES (1, 'barce', '$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 1);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'admin', '$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 1);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');