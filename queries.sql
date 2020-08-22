## Part 1: Test it with SQL
SELECT isc.TABLE_NAME, isc.COLUMN_NAME, isc.COLUMN_TYPE
FROM INFORMATION_SCHEMA.COLUMNS isc
WHERE TABLE_SCHEMA='techjobs'
AND TABLE_NAME IN ('job', 'skill', 'employer');
## Part 2: Test it with SQL
Select e.name from employer e where upper(e.location) LIKE '%ST. LOUIS%';
## Part 3: Test it with SQL

## Part 4: Test it with SQL