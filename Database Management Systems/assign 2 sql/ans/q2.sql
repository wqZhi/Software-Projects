SELECT departments.department_name, COUNT(DISTINCT employees.employee_id) AS count 
FROM departments JOIN employees ON employees.department_id = departments.department_id
GROUP BY departments.department_id 
ORDER BY count DESC;