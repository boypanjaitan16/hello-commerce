CREATE TABLE tbl_products (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  slug VARCHAR(255) NOT NULL,
  description TEXT,
  price NUMERIC(10, 2) NOT NULL,
  stock INTEGER NOT NULL,
  image_url VARCHAR(500),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);