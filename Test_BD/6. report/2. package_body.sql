CREATE OR REPLACE PACKAGE BODY pkg_reports IS

    FUNCTION fn_merchant_report RETURN t_merchant_cursor IS
        v_cursor t_merchant_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT
                c.business_name,
                c.municipality,
                c.phone,
                c.email,
                c.registration_date,
                c.status,
                COUNT(e.establishment_id) AS establishment_count,
                NVL(SUM(e.revenue), 0) AS total_revenue,
                NVL(SUM(e.employee_count), 0) AS employee_count
            FROM merchant c
            LEFT JOIN establishment e ON c.merchant_id = e.merchant_id
            WHERE c.status = 'ACTIVE'
            GROUP BY
                c.business_name,
                c.municipality,
                c.phone,
                c.email,
                c.registration_date,
                c.status
            ORDER BY COUNT(e.establishment_id) DESC;

        RETURN v_cursor;
    END fn_merchant_report;

END pkg_reports;
/