SELECT * FROM(
    SELECT departments.department_name, AVG(jobs.max_salary) as avg
    FROM departments JOIN (
        employees JOIN jobs ON employees.job_id = jobs.job_id
        ) ON departments.department_id = employees.department_id
    GROUP BY departments.department_id
) 
WHERE avg > 8000;