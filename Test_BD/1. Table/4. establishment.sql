CREATE TABLE establishment (
    establishment_id NUMBER PRIMARY KEY,
    name VARCHAR2(150) NOT NULL,
    revenue NUMBER(15,2) NOT NULL,
    employee_count NUMBER(5) NOT NULL,
    merchant_id NUMBER NOT NULL,
    last_updated DATE,
    updated_by_user NUMBER,
    CONSTRAINT fk_establishment_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id),
    CONSTRAINT fk_establishment_auth FOREIGN KEY (updated_by_user) REFERENCES auth(user_id)
);