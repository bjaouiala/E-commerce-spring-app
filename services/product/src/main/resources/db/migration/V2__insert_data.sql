-- Insert data into the category table
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics devices and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Books of various genres and authors', 'Books'),
    (nextval('category_seq'), 'Clothing for men, women, and children', 'Clothing');

-- Insert data into the product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone with 6GB RAM and 128GB storage', 'Smartphone', 50, 299.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Laptop with 16GB RAM and 512GB SSD', 'Laptop', 30, 799.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Mystery novel by a famous author', 'Mystery Novel', 100, 15.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Non-fiction book on personal development', 'Non-fiction Book', 80, 12.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Men''s cotton t-shirt size L', 'Men''s T-shirt', 200, 9.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Women''s summer dress size M', 'Women''s Dress', 150, 19.99, (SELECT id FROM category WHERE name = 'Clothing'));
