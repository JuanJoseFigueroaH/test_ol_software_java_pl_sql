CREATE TABLE auth (
    user_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    password VARCHAR2(200) NOT NULL,
    role VARCHAR2(20) CHECK (role IN ('ADMINISTRATOR', 'ASSISTANT')) NOT NULL
);