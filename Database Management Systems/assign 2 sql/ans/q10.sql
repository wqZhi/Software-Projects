SELECT employees.employee_id 
FROM employees
WHERE (
    SELECT COUNT(DISTINCT dependent_id)
    FROM dependents
    WHERE dependents.employee_id = employees.employee_id) = 0;