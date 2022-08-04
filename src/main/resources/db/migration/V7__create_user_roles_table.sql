CREATE TABLE user_roles (
    user_id VARCHAR(50) NOT NULL,
    roles_id VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, roles_id),

    CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_user_role_role_id FOREIGN KEY (roles_id) REFERENCES role (id)
);