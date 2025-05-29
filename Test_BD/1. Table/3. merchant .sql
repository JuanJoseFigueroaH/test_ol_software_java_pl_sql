CREATE TABLE merchant (
    merchant_id NUMBER PRIMARY KEY,
    business_name VARCHAR2(150) NOT NULL,
    municipality_id NUMBER NOT NULL,
    phone VARCHAR2(20),
    email VARCHAR2(100),
    registration_date DATE DEFAULT SYSDATE NOT NULL,
    status VARCHAR2(10) CHECK (status IN ('ACTIVE', 'INACTIVE')) NOT NULL,
    last_updated DATE,
    updated_by_user NUMBER,
    CONSTRAINT fk_merchant_municipality FOREIGN KEY (municipality_id) REFERENCES municipality(id),
    CONSTRAINT fk_merchant_auth FOREIGN KEY (updated_by_user) REFERENCES auth(user_id)
);