CREATE OR REPLACE TRIGGER trg_merchant_bi
BEFORE INSERT OR UPDATE ON merchant
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        :NEW.merchant_id := seq_merchant.NEXTVAL;
    END IF;

    -- Auditing
    :NEW.last_updated := SYSDATE;
END;
/
