-- Seed categories
INSERT INTO categories (id, name, description)
VALUES ('20000000-0000-0000-0000-000000000001', 'Audio', 'Headphones, speakers and earbuds')
ON CONFLICT (id) DO NOTHING;

INSERT INTO categories (id, name, description)
VALUES ('20000000-0000-0000-0000-000000000002', 'Smartphones & Wearables', 'Smartphones, VR sets and personal wearables')
ON CONFLICT (id) DO NOTHING;

INSERT INTO categories (id, name, description)
VALUES ('20000000-0000-0000-0000-000000000003', 'Computers', 'Computers and peripherals')
ON CONFLICT (id) DO NOTHING;

INSERT INTO categories (id, name, description)
VALUES ('20000000-0000-0000-0000-000000000004', 'Cameras & Displays', 'Monitors, webcams and video devices')
ON CONFLICT (id) DO NOTHING;

INSERT INTO categories (id, name, description)
VALUES ('20000000-0000-0000-0000-000000000005', 'Smart Home & Gadgets', 'Smart home devices and electronic gadgets')
ON CONFLICT (id) DO NOTHING;


-- Seed products (20)

-- AUDIO (4)
INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000001', 'Wireless Headphones', NULL, 129.99, 12, 'sku-4f7c92b1', 4.5, 124, 'https://www.leafstudios.in/cdn/shop/files/1_a43c5e0b-3a47-497d-acec-b4764259b10e_900x.png?v=1750486829', '20000000-0000-0000-0000-000000000001')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000006', 'Wireless Earbuds', NULL, 59.99, 11, 'sku-6b9281fa', 4.1, 98, 'https://picsum.photos/seed/earbuds/300/200', '20000000-0000-0000-0000-000000000001')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000003', 'Bluetooth Speaker', NULL, 79.99, 15, 'sku-a23fbc10', 4.2, 87, 'https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSCpFZ81bu7CzGHzb2DodW97Qab8AxdfXfvjD7zka9O9XJNNGzVguwXbGqjwFBnv0_jcIOERaz7Q_eihmdZ9V6bJ8z5XvugHoY1jnE4NI5L6OZExG5yH8d3hwBmxCkT&usqp=CAc', '20000000-0000-0000-0000-000000000001')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-00000000000e', 'Smart Home Speaker', NULL, 129.99, 12, 'sku-85cd771a', 4.4, 91, 'https://picsum.photos/seed/home_speaker/300/200', '20000000-0000-0000-0000-000000000001')
ON CONFLICT (id) DO NOTHING;


-- SMARTPHONES & WEARABLES (4)
INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000002', 'Smartphone Pro X', NULL, 999.99, 9, 'sku-91bc772a', 4.8, 320, 'https://cdn.vexio.ro/images/products/img_202305191339/2241901/full/smartphone-generic-samsung-galaxy-x-cover-6-pro-g736-128gb-dual-sim-enterprise-edition-black-de-4108228.png', '20000000-0000-0000-0000-000000000002')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000005', 'Smartwatch Series 6', NULL, 249.99, 8, 'sku-ff19bb02', 4.3, 156, 'https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQat_ZgFH0KGQ9ZLJ9R-O8YyYxyCVpHYG0V1UF-P41WgzW-D5g8NstgwpH9A-12HesNFJFNZEoWO74i-naiJbCDRuJpCLliOBXO9kxTRqG7jMucAzWLGl_kHg&usqp=CAc', '20000000-0000-0000-0000-000000000002')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000014', 'Fitness Tracker', NULL, 89.99, 11, 'sku-8af2c01f', 4.0, 120, 'https://picsum.photos/seed/fitband/300/200', '20000000-0000-0000-0000-000000000002')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000011', 'VR Headset', NULL, 399.99, 7, 'sku-45bc109e', 4.2, 142, 'https://picsum.photos/seed/vr/300/200', '20000000-0000-0000-0000-000000000002')
ON CONFLICT (id) DO NOTHING;


-- COMPUTERS (4)
INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000004', 'Gaming Laptop', NULL, 1499.99, 6, 'sku-77af10c3', 4.7, 210, 'https://dellstatic.luroconnect.com/media/catalog/product/cache/74ae05ef3745aec30d7f5a287debd7f5/g/n/gn55308yc9c001orb1_1_1.jpg', '20000000-0000-0000-0000-000000000003')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000007', 'Mechanical Keyboard', NULL, 89.99, 10, 'sku-aa7620e1', 4.6, 177, 'https://picsum.photos/seed/keyboard/300/200', '20000000-0000-0000-0000-000000000003')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000008', 'Gaming Mouse', NULL, 49.99, 14, 'sku-10fd7739', 4.4, 145, 'https://picsum.photos/seed/mouse/300/200', '20000000-0000-0000-0000-000000000003')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-00000000000b', 'USB-C Hub', NULL, 39.99, 13, 'sku-7ad023b9', 4.2, 84, 'https://picsum.photos/seed/hub/300/200', '20000000-0000-0000-0000-000000000003')
ON CONFLICT (id) DO NOTHING;


-- CAMERAS & DISPLAYS (4)
INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000009', '4K Monitor', NULL, 349.99, 7, 'sku-9c21eb77', 4.5, 203, 'https://picsum.photos/seed/monitor/300/200', '20000000-0000-0000-0000-000000000004')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-00000000000c', 'Full HD Webcam', NULL, 69.99, 16, 'sku-4ba118d5', 4.0, 62, 'https://picsum.photos/seed/webcam/300/200', '20000000-0000-0000-0000-000000000004')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-00000000000f', 'Drone Mini 4K', NULL, 599.99, 6, 'sku-55be901d', 4.5, 74, 'https://picsum.photos/seed/drone/300/200', '20000000-0000-0000-0000-000000000004')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000010', 'Action Camera', NULL, 199.99, 10, 'sku-0ae91cd3', 4.3, 115, 'https://picsum.photos/seed/camera/300/200', '20000000-0000-0000-0000-000000000004')
ON CONFLICT (id) DO NOTHING;


-- SMART HOME & GADGETS (4)
INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000012', 'Smart Thermostat', NULL, 149.99, 8, 'sku-19f209cd', 4.4, 65, 'https://picsum.photos/seed/thermostat/300/200', '20000000-0000-0000-0000-000000000005')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-000000000013', 'Robot Vacuum', NULL, 299.99, 10, 'sku-fd5512b8', 4.1, 102, 'https://picsum.photos/seed/vacuum/300/200', '20000000-0000-0000-0000-000000000005')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-00000000000a', 'External SSD 1TB', NULL, 119.99, 9, 'sku-2281ff01', 4.7, 412, 'https://picsum.photos/seed/ssd/300/200', '20000000-0000-0000-0000-000000000005')
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, quantity, sku, ratings, reviews, image, category_id)
VALUES ('00000000-0000-0000-0000-00000000000d', 'Portable Charger 20,000mAh', NULL, 45.99, 19, 'sku-f0b772e1', 4.6, 233, 'https://picsum.photos/seed/powerbank/300/200', '20000000-0000-0000-0000-000000000005')
ON CONFLICT (id) DO NOTHING;

