CREATE TABLE restaurant (
    id VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(15) NOT NULL UNIQUE,
    image_url VARCHAR(255) NOT NULL UNIQUE,
    user_id VARCHAR(50) NOT NULL,

    CONSTRAINT pk_restaurant_id PRIMARY KEY (id),
    CONSTRAINT fk_restaurant_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);