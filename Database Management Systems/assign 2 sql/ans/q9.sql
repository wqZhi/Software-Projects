SELECT department_name
FROM employees JOIN departments ON employees.department_id = departments.department_id 
WHERE employees.salary = (SELECT MAX(salary) FROM employees);