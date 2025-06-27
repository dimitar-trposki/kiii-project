CREATE MATERIALIZED VIEW books_per_author AS
SELECT a.id AS author_id, COUNT(b.id) AS book_count
FROM author a
         LEFT JOIN book b ON a.id = b.author_id
GROUP BY a.id;

CREATE MATERIALIZED VIEW authors_per_country AS
SELECT a.country_id AS country_id, c.name AS country_name, COUNT(*) AS author_count
FROM author a
         JOIN country c ON a.country_id = c.id
GROUP BY a.country_id, c.name;