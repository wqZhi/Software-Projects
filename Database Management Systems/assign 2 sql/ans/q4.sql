SELECT COUNT(DISTINCT employee_id) 
FROM employees JOIN departments ON employees.department_id = departments.department_id
WHERE department_name = "Shipping";