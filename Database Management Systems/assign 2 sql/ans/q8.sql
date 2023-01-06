SELECT employees.manager_id, employees.salary
FROM employees
WHERE employees.salary = (SELECT MIN(salary) FROM employees);