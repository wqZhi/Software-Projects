SELECT COUNT(DISTINCT employee_id) 
FROM employees JOIN (
    departments JOIN (
        locations JOIN (
            countries JOIN regions ON countries.region_id = regions.region_id
        ) ON locations.country_id = countries.country_id
    ) ON departments.location_id = locations.location_id
) ON employees.department_id = departments.department_id
WHERE regions.region_name = "Europe";