ALTER TABLE consumer
MODIFY user_id VARCHAR(50) NOT NULL UNIQUE;

ALTER TABLE restaurant
MODIFY user_id VARCHAR(50) NOT NULL UNIQUE;

ALTER TABLE delivery
MODIFY user_id VARCHAR(50) NOT NULL UNIQUE;