CREATE TABLE consumer (
    id VARCHAR(50) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    image_url VARCHAR(255) NOT NULL UNIQUE,
    user_id VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL,

    CONSTRAINT pk_consumer_id PRIMARY KEY (id),
    CONSTRAINT fk_consumer_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);