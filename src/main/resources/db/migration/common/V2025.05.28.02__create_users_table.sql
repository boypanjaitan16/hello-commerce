CREATE TABLE tbl_users (
  id UUID PRIMARY KEY,
  email VARCHAR(100) NOT NULL UNIQUE,
  password TEXT NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  address VARCHAR(200),
  city VARCHAR(100),
  state VARCHAR(100),
  zip_code VARCHAR(20),
  country VARCHAR(100),
  email_verified BOOLEAN NOT NULL DEFAULT FALSE,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  role_id UUID,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

ALTER TABLE tbl_users ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES tbl_roles(id);