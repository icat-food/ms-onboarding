-- Limpar todos os registros anteriormente inseridos
DELETE FROM consumer;
DELETE FROM delivery;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM user;
DELETE FROM role;

-- Criar usuários e associar com subtipo delivery
INSERT INTO user(id, email, password, created_at, updated_at)
VALUES('fgANh9Ffzqa3FOQzFUnNJeBujQThtpEBuc7fJFHNy9eQRMmUHQ', 'eilson.risca_faca@xuragou.com', '$2a$10$V40wcYeeWeXDki2XLU7OI.UbleRzf55sjilMoHwCnGaQtXwuEFORW', '2022-07-02T01:00:28.019846340', '2022-07-02T01:00:28.019846340');

INSERT INTO delivery (id, full_name, cpf, image_url, user_id, created_at, updated_at)
VALUES('SN3Ao3WL5leG8Z39s39i5T9RyPEpVE18Re13SyBbC4uMj24UkW', 'Van Der Ilson', '28736574197', 'path/van', 'fgANh9Ffzqa3FOQzFUnNJeBujQThtpEBuc7fJFHNy9eQRMmUHQ', '2022-07-02T01:00:28.019846340', '2022-07-02T01:00:28.019846340');


-- Criar usuários e associar com subtipo consumer
INSERT INTO user(id, email, password, created_at, updated_at)
VALUES('a8Wr1ltJgapvIMmHFh2UXqY9PxhLSXkTqYihcpBjZdIdYFdnib', 'kaikeira.store@xurastei.com', '$2a$10$V40wcYeeWeXDki2XLU7OI.UbleRzf55sjilMoHwCnGaQtXwuEFORW', '2022-06-01T10:25:41.019846340', '2022-06-01T10:25:41.019846340');

INSERT INTO consumer (id, full_name, cpf, image_url, user_id, created_at, updated_at)
VALUES('vxiNR3KMsTJzwXd9LSRA1Vj83CC1PF1VpwRuwkMB7vkf3nygCA', 'Kaike dos Sete Mares', '98745632102', 'path/kaike', 'a8Wr1ltJgapvIMmHFh2UXqY9PxhLSXkTqYihcpBjZdIdYFdnib', '2022-06-01T10:25:41.019846340', '2022-06-01T10:25:41.019846340');


-- Criar usuários e associar com subtipo restaurant
INSERT INTO user(id, email, password, created_at, updated_at)
VALUES('Ye42zbo4JFVs0PIUCYC63t3wmr4uEbE0hJBXsT1XBXor1ZwS9P', 'choraste_beicola@pasteis.com', '$2a$10$V40wcYeeWeXDki2XLU7OI.UbleRzf55sjilMoHwCnGaQtXwuEFORW', '2021-09-28T07:2:41.019846340', '2021-09-28T07:2:41.019846340');

INSERT INTO restaurant (id, name, cnpj, image_url, user_id, created_at, updated_at)
VALUES('ezo2BHLZgoIkqYhx9OTvpMHOUgSJGOW6gLvrUE8mUhnD9vVlzn', 'Pastelaria do Beiçola', '59713462580001', 'path/beicola', 'fgANh9Ffzqa3FOQzFUnNJeBujQThtpEBuc7fJFHNy9eQRMmUHQ', '2021-09-28T07:2:41.019846340', '2021-09-28T07:2:41.019846340');


-- Criar roles
INSERT INTO role (id, name) VALUES('8a8a8970812e4ec4863acdd5c4e5fa8a8970812e4ec48633e1', 'ROLE_RESTAURANT');
INSERT INTO role (id, name) VALUES('da5a4b9bdo518635d2ed0864d3e9404a4b9bdo518635d2ed08', 'ROLE_DELIVERY');
INSERT INTO role (id, name) VALUES('a1ffecdb95e5482a8954dff94f4b042cdb95e5482a8954dff3', 'ROLE_CONSUMER');

-- Associar users com roles
-- consumer kaikeira
INSERT INTO user_roles (user_id, roles_id) VALUES('a8Wr1ltJgapvIMmHFh2UXqY9PxhLSXkTqYihcpBjZdIdYFdnib', 'a1ffecdb95e5482a8954dff94f4b042cdb95e5482a8954dff3');

-- delivery van
INSERT INTO user_roles (user_id, roles_id) VALUES('fgANh9Ffzqa3FOQzFUnNJeBujQThtpEBuc7fJFHNy9eQRMmUHQ', 'da5a4b9bdo518635d2ed0864d3e9404a4b9bdo518635d2ed08');

 -- restaurante beiçola
INSERT INTO user_roles (user_id, roles_id) VALUES('Ye42zbo4JFVs0PIUCYC63t3wmr4uEbE0hJBXsT1XBXor1ZwS9P', '8a8a8970812e4ec4863acdd5c4e5fa8a8970812e4ec48633e1');
