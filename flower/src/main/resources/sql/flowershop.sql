CREATE DATABASE flowershop;
USE flowershop;

-- Create the users table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50) NULL,
    last_name VARCHAR(50) NULL,
    address TEXT NULL,
    phone_number VARCHAR(20) NULL,
    role ENUM('admin', 'user') DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the orders table
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    total_price DECIMAL(10,2) NOT NULL,
    order_status ENUM('pending', 'shipped', 'delivered', 'cancelled') NOT NULL,
    shipping_address TEXT NOT NULL,
    payment_method VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create the products table
CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50),
    image_url VARCHAR(255),
    stock INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the order_items table
CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- change order-status to String for simple handle!!!!!
ALTER TABLE Orders MODIFY order_status VARCHAR(20);

-- Create the wishlist table
CREATE TABLE wishlist (
    wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    product_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Create the cart table
CREATE TABLE cart (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Insert data into users table
INSERT INTO users (username, password_hash, email, first_name, last_name, address, phone_number, role) VALUES
('john_doe', 'hashed_password1', 'john@example.com', 'John', 'Doe', '123 Flower St, Blossom City', '1234567890', 'user'),
('jane_smith', 'hashed_password2', 'jane@example.com', 'Jane', 'Smith', '456 Petal Rd, Floral Town', '0987654321', 'admin');

-- Insert data into products table
INSERT INTO products (name, description, price, category, image_url, stock) VALUES
('Rose Bouquet', 'A beautiful bouquet of red roses.', 29.95, 'Bouquet', 'https://m.media-amazon.com/images/I/71dEnbFkofL._AC_SL1500_.jpg', 50),
('Tulip Arrangement', 'A lovely arrangement of tulips.', 24.99, 'Arrangements', 'https://m.media-amazon.com/images/I/61Pop209G8L._AC_SL1200_.jpg', 30),
('Lily Basket', 'A charming basket of lilies.', 69.99, 'Baskets', 'https://bloomex.ca/components/com_virtuemart/shop_image/product/Peaceful-White-Lilies-LF100-72.png', 20);

-- Insert data into orders table
INSERT INTO orders (user_id, total_price, order_status, shipping_address, payment_method) VALUES
(1, 54.98, 'pending', '123 Flower St, Blossom City', 'Credit Card'),
(2, 19.99, 'shipped', '456 Petal Rd, Floral Town', 'PayPal');

-- Insert data into order_items table
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 29.99),
(1, 2, 1, 24.99),
(2, 3, 1, 19.99);

-- Insert data into wishlist table
INSERT INTO wishlist (user_id, product_id) VALUES
(1, 3),
(2, 1);

-- Insert data into cart table
INSERT INTO cart (user_id, product_id, quantity) VALUES
(1, 1, 2),
(2, 3, 1);
