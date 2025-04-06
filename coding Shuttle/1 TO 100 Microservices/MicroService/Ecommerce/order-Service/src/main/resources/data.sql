INSERT into orders (total_price, order_status) VALUES
(100.50,'PENDING'),
(200.75,'CONFIRMED'),
(300.00,'CANCLED'),
(150.25,'PENDING'),
(120.00,'CANCLED'),
(210.50,'PENDING'),
(100.00,'CONFIRMED'),
(180.20,'PENDING'),
(250.40,'CANCLED');

INSERT INTO order_item (order_id, product_id, quantity)  values 
(1,101,2),
(1,102,1),
(2,102,1),
(2,103,3),
(3,105,1),
(3,106,2),
(4,107,5),
(5,108,3),
(6,109,2),
(7,110,1),
(8,111,2),
(9,112,3);
