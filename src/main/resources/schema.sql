CREATE TABLE `USER` (
    user_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    user_nick_name VARCHAR(255),
    password VARCHAR(255),
    user_phone VARCHAR(255),
    user_email VARCHAR(255),
    use_yn CHAR(1),
    role VARCHAR(50),
    insert_user VARCHAR(255),
    insert_date DATETIME,
    update_user VARCHAR(255),
    update_date DATETIME
);

CREATE TABLE BOARD (
    board_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    community_id INT,
    user_id VARCHAR(255),
    title VARCHAR(255),
    writer VARCHAR(255),
    detail TEXT,
    views INT,
    use_yn CHAR(1),
    insert_user VARCHAR(255),
    insert_date DATETIME,
    update_user VARCHAR(255),
    update_date DATETIME
);
