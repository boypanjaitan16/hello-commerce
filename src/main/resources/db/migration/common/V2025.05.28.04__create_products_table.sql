CREATE TABLE tbl_products (
  id UUID PRIMARY KEY,
  product_category_id UUID,
  name VARCHAR(255) NOT NULL,
  slug VARCHAR(255) NOT NULL,
  description TEXT,
  price NUMERIC(10, 2) NOT NULL,
  stock INTEGER NOT NULL,
  image_url VARCHAR(500),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

ALTER TABLE tbl_products ADD CONSTRAINT fk_product_category FOREIGN KEY (product_category_id) REFERENCES tbl_product_categories (id);