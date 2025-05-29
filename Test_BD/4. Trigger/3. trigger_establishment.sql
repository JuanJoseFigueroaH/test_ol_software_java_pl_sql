CREATE OR REPLACE TRIGGER trg_establishment_bi
BEFORE INSERT OR UPDATE ON establishment
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        :NEW.establishment_id := seq_establishment.NEXTVAL;
    END IF;

    -- Auditing
    :NEW.last_updated := SYSDATE;
END;
/