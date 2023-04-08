CREATE TABLE verification_token (
    token_id INT NOT NULL auto_increment PRIMARY KEY ,
    user_id INT ,
    token VARCHAR(255) ,
    expiry_date TIMESTAMP ,
    FOREIGN  KEY (user_id) REFERENCES user(id)
);