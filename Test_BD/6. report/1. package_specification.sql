CREATE OR REPLACE PACKAGE pkg_reports IS
    TYPE t_merchant_cursor IS REF CURSOR;

    FUNCTION fn_merchant_report RETURN t_merchant_cursor;
END pkg_reports;