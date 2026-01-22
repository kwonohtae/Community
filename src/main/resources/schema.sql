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
    parent_id BIGINT DEFAULT NULL, -- 재귀 참조를 위한 부모 ID
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
    update_date DATETIME,
    FOREIGN KEY (parent_id) REFERENCES BOARD(board_id) ON DELETE CASCADE
);

CREATE TABLE NOTICE (
    notice_id BIGINT AUTO_INCREMENT PRIMARY KEY,
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

CREATE TABLE ATTACHMENTS (
    attachment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id BIGINT,
    notice_id BIGINT,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size BIGINT NOT NULL,
    insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES BOARD(board_id) ON DELETE CASCADE,
    FOREIGN KEY (notice_id) REFERENCES NOTICE(notice_id) ON DELETE CASCADE
);

CREATE TABLE COMMENTS (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id BIGINT,
    notice_id BIGINT,
    user_id VARCHAR(255),
    content TEXT NOT NULL,
    use_yn CHAR(1) DEFAULT 'Y',
    insert_user VARCHAR(255),
    insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_user VARCHAR(255),
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES BOARD(board_id) ON DELETE CASCADE,
    FOREIGN KEY (notice_id) REFERENCES NOTICE(notice_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE
);
