SELECT countries.country_name
FROM countries JOIN regions ON countries.region_id = regions.region_id
WHERE region_name = "Europe";
