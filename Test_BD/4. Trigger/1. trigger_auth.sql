CREATE OR REPLACE TRIGGER trg_auth_bi
BEFORE INSERT ON auth
FOR EACH ROW
BEGIN
    :NEW.user_id := seq_auth.NEXTVAL;
END;