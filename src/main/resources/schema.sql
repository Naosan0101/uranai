DROP TABLE IF EXISTS fortune;

CREATE TABLE fortune (
	id SERIAL PRIMARY KEY,
	month_result VARCHAR(255),
	day_result VARCHAR(255)
);