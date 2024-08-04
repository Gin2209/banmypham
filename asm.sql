create database Java6_ASM
use Java6_ASM

-- Tạo bảng account
CREATE TABLE account (
    userID varchar(10) PRIMARY KEY,
    LastName NVARCHAR(50),
    FirstName NVARCHAR(50),
    Email NVARCHAR(50),
    Password NVARCHAR(30),
    PhoneNumber NVARCHAR(12),
    Avatar NVARCHAR(10)
);

-- Tạo bảng role
CREATE TABLE role (
    roleID INT identity(1,1) PRIMARY KEY,
    RoleName NVARCHAR(50),
    Description NVARCHAR(100)
);
create table collection(
	IDCollection INT IDENTITY (1,1) PRIMARY KEY,
	NameCollection nvarchar(50)
)
-- Tạo bảng product

-- Tạo bảng size
CREATE TABLE size (
    sizeID INT identity(1,1) PRIMARY KEY,
    size int,
	unit NVARCHAR(10)
);
-- Tạo bảng address
CREATE TABLE address (
    address nvarchar(255) PRIMARY KEY,
    userID varchar(10),
    constraint ad_ac FOREIGN KEY (userID) REFERENCES account(userID)
);
-- Tạo bảng authorities
CREATE TABLE authorities (
	ID int identity(1,1) primary key,
    userID varchar(10) ,
    roleID INT ,
    constraint au_ac FOREIGN KEY (userID) REFERENCES account(userID),
    constraint au_r FOREIGN KEY (roleID) REFERENCES role(roleID)
);
CREATE TABLE product (
    productID INT identity(1,1) PRIMARY KEY,
    Name NVARCHAR(100),
    image NVARCHAR(10),
    description NVARCHAR(max),
	IDCollection INT,
	constraint co_p FOREIGN KEY (IDCollection) REFERENCES collection(IDCollection)
);
-- Tạo bảng product_size
CREATE TABLE product_size (
	PS_ID INT identity(1,1) PRIMARY KEY,
	productID int,
    sizeID INT,
    price DECIMAL(10, 2),
    quantity INT,
    constraint ps_p FOREIGN KEY (productID) REFERENCES product(productID),
    constraint ps_s FOREIGN KEY (sizeID) REFERENCES size(sizeID)
);

-- Tạo bảng product_type
CREATE TABLE product_type (
    productTypeID varchar(10) PRIMARY KEY,
    name NVARCHAR(50)
);

create table product_productType(
	IDType int identity(1,1)  primary key,
	productTypeID varchar(10),
	productID int,
	constraint p_pt FOREIGN KEY (productTypeID) REFERENCES product_type(productTypeID),
	constraint pt_ FOREIGN KEY (productID) REFERENCES product(productID)
)

-- Tạo bảng skin_type
CREATE TABLE skin_concern (
    skinConcernID varchar(10) PRIMARY KEY,
    name NVARCHAR(50)
);

create table product_skinConcern(
	IDSkin int identity(1,1) primary key,
	skinConcernID varchar(10),
	productID int,
	constraint p_sc FOREIGN KEY (skinConcernID) REFERENCES skin_concern(skinConcernID),
	constraint sc_p FOREIGN KEY (productID) REFERENCES product(productID)
)

-- Tạo bảng order
CREATE TABLE orders (
    orderID varchar(20) PRIMARY KEY,
    userID varchar(10),
    orderDate DateTime,
    expectedDeliveryDate DateTime,
	status NVARCHAR(50),
    constraint o_u FOREIGN KEY (userID) REFERENCES account(userID)
);

-- Tạo bảng order_detail
CREATE TABLE order_detail (
    ID INT identity(1,1) PRIMARY KEY,
    orderID varchar(20),
	PS_ID INT,
    quantity INT,
    total DECIMAL(10, 2),
    constraint od_o FOREIGN KEY (orderID) REFERENCES orders(orderID),
    constraint od_ps FOREIGN KEY (PS_ID ) REFERENCES product_size(PS_ID )
);

-- Tạo bảng transaction
CREATE TABLE transactions (
    ID INT identity(1,1) PRIMARY KEY,
    orderID varchar(20),
    totalmoney DECIMAL(10, 2),
    transactionDate DATE,
    constraint o_t FOREIGN KEY (orderID) REFERENCES orders(orderID)
);

-- Tạo bảng review
CREATE TABLE review (
    reviewID INT identity(1,1) PRIMARY KEY,
    orderID varchar(20),
    Rating INT,
    comment NVARCHAR(200),
    reviewDate DATE,
    constraint r_o FOREIGN KEY (orderID) REFERENCES orders(orderID)
);

CREATE TABLE images (
    imgID INT identity(1,1) PRIMARY KEY,
    productID INT,
    imagePath varchar(30),
    constraint i_p FOREIGN KEY (productID) REFERENCES product(productID)
);



INSERT INTO account
VALUES 
('AD001', N'Phạm', N'Huyền', 'huyenpttps35245@fpt.edu.vn', 'Huyenhuyen28@', '0984786748','avt-3d.jpg'),
('NV002', N'Lê', 'Anh', 'anhlhps22222@fpt.edu.vn', 'Anhanh01@', '0856749769','avt-3d.jpg'),
('AD003', N'Nguyễn', N'Ngân', 'nganntps35194@fpt.edu.vn', 'Nganngan12@', '0346753850','avt-3d.jpg'),
('NV004', N'Nguyễn', 'Linh', 'linhnnps33333@fpt.edu.vn', 'Linhlinh02@', '0857846796','avt-3d.jpg'),
('AD005', N'Nguyễn', 'Sang', 'sangntps44444@fpt.edu.vn', 'Sangsang03@', '0955748724','avt-3d.jpg'),
('AC006', N'Nguyễn ',N'Hồng Ngọc', 'nguyenhongngoc022@gmail.com', 'ngocnguyen123',  '0346763850','avt-3d.jpg'),
('AC007', N'Trần',N'Minh', 'tranthanhminh113@gmail.com', 'minhtran123', '0746763850','avt-3d.jpg'),
('AC008', N'Phạm Thị',N' Thúy Hạnh', 'phamthithuyhanh114@gmail.com', 'hanhpham123',  '0346763862','avt-3d.jpg'),
('AC009', N'Lê Hồng ',N'Ánh', 'lehonganh115@gmail.com', 'anhle123', '0363636590','avt-3d.jpg'),
('AC010', N'Cù Thanh' ,N'Thảo', 'cuthanhthao119@gmail.com', 'thanhthao123', '0394175076','avt-3d.jpg'),
('AC011', N'Phạm Thị',N'Huyền', 'huyenpt34@gmail.com', 'huyenpham123',   '0394172850','avt-3d.jpg'),
('AC012', N'Trần Ngọc ',N'Lý', 'tranngocly089@gmail.com', 'lytran123',   '0395865076','avt-3d.jpg');

-- Thêm dữ liệu vào bảng security
INSERT INTO role
VALUES 
    (N'Admin', N'Quản trị hệ thống'),
    (N'Staff', N'Nhân Viên'),
    (N'User', N'Người dùng'),
    (N'Guest', N'Khách');

	insert into collection values 
	('Centella'),
	('Hyalu Cica'),
	('Tone Brightening'),
	('Poremizing'),
	('Tea Trica'),
	('Probio Cica')

INSERT INTO product VALUES
( N'Hyalu-Cica Water-Fit Sun Serum SPF50+ PA++++', 'sp1.webp', 'What It Is: All rounder ampoule with great qualities for redness and irritation relief, including high quality Madagascan Centella Asiatica extract
Product Benefits: Calming, Hydrating, Acne Care Skin Type: Sensitive, Acne, Normal Key Ingredients: Centella Asiatica Extract', 2),
( N'Centella Ampoule', 'sp2.webp' , 'What It Is: All rounder ampoule with great qualities for redness and irritation relief, including high quality Madagascan Centella Asiatica extract
Product Benefits: Calming, Hydrating, Acne Care Skin Type: Sensitive, Acne, Normal Key Ingredients: Centella Asiatica Extract', 1),
( N'Tea-Trica Spot Cover Patch','sp3.webp', 'What It Is: All rounder ampoule with great qualities for redness and irritation relief, including high quality Madagascan Centella Asiatica extract
Product Benefits: Calming, Hydrating, Acne Care Skin Type: Sensitive, Acne, Normal Key Ingredients: Centella Asiatica Extract', 5),
( N'Centella Watergel Sheet Ampoule Mask', 'sp4.webp' , 'What It Is: A lightweight gel sleeping mask with 5 Hyaluronic acids and Melatonin calms and moisturizes the skin overnight.
Product Benefits: Moisturizing, Calming, Improves Skin Tone Skin Type: Sensitive, Normal, Dry Key Ingredients: Centella Asiatica Leaf Water, Hyaluronic Acid, Melatonin, Ceramide NP', 1),
( N'Centella Light Cleansing Oil', 'sp5.webp', 'What It Is: A rich, creamy cleanser with Mineral salts, Kaolin and Papain acts like a magnet to absorb impurities and excess sebum. 
Product Benefits: Calming, Pore Tightening, Mild exfoliating Skin Type: Oily, Normal, Combination Key Ingredients: Centella Asiatica Extract, Mineral Salts, Kaolin (CI 77004), Papain, Pyrus Malus (Apple) Fruit Extract' ,1),
( N'Centella Ampoule Foam', 'sp6.webp', 'What It Is: A deep cleansing foam with BHA and Tea tree unclogs pores and exfoliates dead skin cells, leaving a refreshed finish.
Product Benefits: Calming, Acne, Sensitive, Mild exfoliating Skin Type: Acne, Oily, Sensitive Key Ingredients: Centella Asiatica Extract, Melaleuca Alternifolia (Tea Tree) Leaf Water, Salicylic Acid, Pinus Palustris Leaf Extract, Chamaecyparis Obtusa Water', 1),
( N'Centella Soothing Cream', 'sp7.webp' , 'What It Is: A full Centella skincare routine set with 5 mini-sized products for travel or trial of the Centella line.
Product Benefits: Calming, Hydrating, Mild exfoliating, Traveling Size Skin Type: Normal, Sensitive, Combination, Dry Key Ingredients: Centella Asiatica Extract, Gluconolactone, Betaine, Citric Acid, Sodium Hyaluronate, Citrus Aurantium Bergamia (Bergamot) Fruit Oil, Helianthus Annuus (Sunflower) Seed Oil, Olea Europaea (Olive) Fruit Oil, Simmondsia Chinensis (Jojoba) Seed Oil, Trehalose, Ceramide NP, Cholesterol', 1),
( N'Poremizing Fresh Ampoule' ,'sp8.webp', 'What It Is: A full Centella skincare routine set with 5 mini-sized products for travel or trial of the Centella line.
Product Benefits: Calming, Hydrating, Mild exfoliating, Traveling Size Skin Type:Normal, Sensitive, Combination, Dry Key Ingredients:Centella Asiatica Extract, Gluconolactone, Betaine, Citric Acid, Sodium Hyaluronate, Citrus Aurantium Bergamia (Bergamot) Fruit Oil, Helianthus Annuus (Sunflower) Seed Oil, Olea Europaea (Olive) Fruit Oil, Simmondsia Chinensis (Jojoba) Seed Oil, Trehalose, Ceramide NP, Cholesterol', 4),
( N'Hyalu-Cica Silky-Fit Sun Stick','sp9.webp', 'What It Is: A smooth stick mask with 4 kinds of clays (18% Kaolin) and fine Red bean powder to soak up excess sebum and tightens enlarged pores.
Product Benefits: Calming, Pore Tightening, Mild exfoliating Skin Type: Oily, Normal, CombinationKey Ingredients: Centella Asiatica Extract, Kaolin, Bentonite, Illite, Montmorillonite, Aloe Barbadensis Leaf Extract', 2),
( N'Centella Toning Toner', 'sp10.webp', 'What It Is: A gentle PHA exfoliating toner that can be used daily to thoroughly improve skin texture.
Product Benefits: Calming, Soothing, Mild exfoliating Skin Type: Normal, Sensitive Key Ingredients: Centella Asiatica Extract, Gluconolactone, Betaine', 1),
( N'Tone Brightening Capsule Ampoule', 'sp11.webp', 'What It Is: An ultra-fine mist spray with Green tea and 5 Hyaluronic acid gives instant dewy, glowing skin with a cooling effect
Product Benefits: Moisturizing, Calming Skin Type: Sensitive, Normal, Dry Key Ingredients: Centella Asiatica Extract, Hyaluronic Acid, Camellia Sinensis Leaf Water, Xylitol, Ceramide NP', 3),
( N'Skin Relief Duo', 'sp12.webp', 'What It Is: A highly hydrating toner with AHA, LHA gently exfoliates while soothing and brightening the skin.
Product Benefits: Moisturizing, Calming Skin Type: Sensitive, Normal, Dry Key Ingredients: Centella Asiatica Leaf Water, Hyaluronic Acid, Ceramide NP, Citric Acid, Capryloyl Salicylic Acid', 1),
( N'PROBIO-CICA BAKUCHIOL EYE CREAM', 'sp13.webp', 'What It Is: A mild exfoliating toner with fruit extracts, Madewhite™ and Niacinamide prepare the skin for next skincare step.
Product Benefits: Calming, Spot Correcting, Dullness Skin Type: Normal, Sensitive, Combination Key Ingredients: Centella Asiatica Extract, Niacinamide, 3-O-Ethyl Ascorbic Acid, Madecassoside', 6),
( N'PROBIO-CICA INTENSIVE AMPOULE', 'sp14.webp', 'What It Is: A Tea tree water toner with Cypress balances oil & sebum, and removes dead skin cells.
Product Benefits: Calming, Acne, Sensitive Skin Type: Acne, Oily, Sensitive Key Ingredients: Centella Asiatica Extract, Melaleuca Alternifolia (Tea Tree) Leaf Water, Pinus Palustris Leaf Extract, Chamaecyparis Obtusa Water', 6),
( N'Centella Air-Fit Suncream Plus SPF50+ PA++++', 'sp15.webp', 'What It Is: An exfoliating toner with mineal salts and 4-HAs (AHA, BHA, PHA, LHA) gently cleanses pores and removes dead skin cells.
Product Benefits: Calming, Pore Tightening Skin Type: Oily, Combination, Sensitive Key Ingredients: Centella Asiatica Extract, Mineral Salts, Salix Alba (Willow) Bark Extract, Citric Acid, Capryloyl Salicylic Acid, Gluconolactone, Salicylic Acid, Citric Acid', 1),
( N'PROBIO-CICA ENRICH CREAM', 'sp16.webp', 'What It Is: A rich essence toner with fermented Centella, 5 Hyaluronic acids and Ceramide NP soothes and moisturizes the skin.
Product Benefits: Calming, Anti-Aging, Hydrating Skin Type: Dry, Normal, Combination Key Ingredients: Lactobacillus/Centella Asiatica Extract Ferment Filtrate, Centella Asiatica Extract, Madecassic Acid, Asiaticoside, Asiatic Acid, Hyaluronic Acid', 6),
( N'Hyalu-Cica Blue Serum', 'sp17.webp', 'What It Is: An all-in-one lightweight serum with Centella, 5 Hyaluronic acids, Ceramide NP, Niacinamide, and Adenosine to hydrate and soothe the skin.
Product Benefits: Moisturizing, Calming Skin Type: Sensitive, Normal, Dry Key Ingredients: Centella Asiatica Leaf Water, Hyaluronic Acid, Ceramide NP, Hedera Helix (Ivy) Leaf/Stem Extract', 2),
( N'Centella Cream', 'sp18.webp', 'What It Is: A light cream with Panthenol and TECA soothes, moisturizes, and nourishes the skin.
Product Benefits: Calming, Hydrating Skin Type: Normal, Sensitive Key Ingredients: Centella Asiatica Extract, Panthenol, TECA (Madecassoside, Asiaticoside, Madecassic Acid, Asiatic Acid)', 1),
( N'Hyalu-Cica Sleeping Pack', 'sp19.webp', 'What It Is: A lightweight gel sleeping mask with 5 Hyaluronic acids and Melatonin calms and moisturizes the skin overnight.
Product Benefits: Moisturizing, Calming, Improves Skin Tone Skin Type: Sensitive, Normal, DryKey Ingredients: Centella Asiatica Leaf Water, Hyaluronic Acid, Melatonin, Ceramide NP', 2),
( N'Hyalu-Cica Brightening Toner', 'sp20.webp', 'What It Is: A highly hydrating toner with AHA, LHA gently exfoliates while soothing and brightening the skin.
Product Benefits: Moisturizing, Calming Skin Type: Sensitive, Normal, Dry Key Ingredients: Centella Asiatica Leaf Water, Hyaluronic Acid, Ceramide NP, Citric Acid, Capryloyl Salicylic Acid', 2),
( N'Hyalu-Cica Moisture Cream', 'sp21.webp', 'What It Is: A lightweight cream with 5 Hyaluronic acids and Hydrolyzed Collagen provides deep hydration that lasts up to 100 hours.
Product Benefits: Moisturizing, Calming Skin Type: Sensitive, Normal, Dry Key Ingredients: Centella Asiatica Extract, Hyaluronic Acid, Hydrolyzed Collagen.', 2);

INSERT INTO size VALUES
    (15, 'ml'),
	(20, 'ml'),
    (25, 'ml'),
    (30, 'ml'),
    (50, 'ml'),
    (55, 'ml'),
    (70, 'ml'),
	(75, 'ml'),
    (95, 'ml'),
    (100, 'ml'),
    (110, 'ml'),
	(125, 'ml'),
	(130, 'ml'),
    (200, 'ml'),
    (210, 'ml'),
    (400, 'ml'),
    (1, N'gói'),
    (7, 'g'),
    (20, 'g');

INSERT INTO address  VALUES 
(N'C11 Tân Chánh Hiệp Quận 12 TP Hồ Chí Minh', 'AC006'),
(N'753 Nguyễn Ảnh Thủ Phường Hiệp Thành Quận 12 TP Hồ Chí Minh', 'AC007'),
(N'Xóm 5 thôn 2 Bình Nghi Tây Sơn Bình Định', 'AC008'),
(N'20A Nguyễn Văn Lượng Gò Vấp TP Hồ Chí Minh', 'AC006'),
(N'456 Xưởng băng keo Hiệp Phước Hóc Môn TP Hồ Chí Minh', 'AC008'),
(N'123 Lê Lợi, Quận 1, TP Hồ Chí Minh', 'AC006'),
(N'456 Nguyễn Huệ, Quận 1, TP Hồ Chí Minh', 'AC007'),
(N'202 Phan Xích Long, Quận Phú Nhuận, TP Hồ Chí Minh', 'AC008'),
(N'303 Phạm Văn Đồng, Quận Thủ Đức, TP Hồ Chí Minh', 'AC009'),
(N'12 Trần Phú, Quận Hải Châu, TP Đà Nẵng', 'AC010'),
(N'34 Nguyễn Trãi, TP Vũng Tàu, Bà Rịa - Vũng Tàu', 'AC011'),
(N'23 Lê Hồng Phong, TP Hải Phòng', 'AC012'),
(N'45 Đinh Tiên Hoàng, TP Nha Trang, ACánh Hòa', 'AC011'),
(N'67 Bạch Đằng, TP Đà Lạt, Lâm Đồng', 'AC010'),
(N'12 Lý Thường Kiệt, TP Hà Nội', 'AC012');

insert into authorities(RoleID,UserID)
values		
(1, 'AD001'),
(2, 'NV002'),
(2, 'AD003'),
(2, 'NV004'),
(1, 'AD005'),
(3 ,'AC006'),
(3, 'AC007'),
(3, 'AC008'),
(3, 'AC009'),
(3, 'AC010'),
(3, 'AC011'),
(3, 'AC012');

INSERT INTO product_size 
	VALUES
	(1,1, 190000, 30),
	(1,5, 475000, 50),
	(1,10, 605000, 50),
	(2,4, 225500, 50),
	(2,6, 300000, 50),
	(2,11,  440000, 50),
	(3,17, 160000, 50),
	(4, 3, 71500, 50),
	(5, 4 , 130000, 50),
	(5, 14, 420000, 50),
	(6, 2, 95000, 50),
	(6, 12, 280000, 50),
	(7, 4 , 178500, 50),
	(7, 7, 320000, 50),
	(8,4, 225750, 50),
	(8, 5, 340000, 50),
	(8, 10, 460000, 50),
	(9, 19, 380000, 50),
	(9, 18,  213750, 50),
	(10, 4,  119000, 50),
	(10, 15, 360000, 50),
	(10, 16, 520000, 50),
	(11, 4,  213750, 50),
	(11, 10, 480000, 50),
	(12, null,  656000, 50),
	(13, 2,  497000, 50),
	(14, 5,  440000, 50),
	(14, 9,  600000, 50),
	(15, 5,  360000, 50),
	(16, 1, 237500, 50),
	(16, 5, 480000, 50),
	(17, 4, 332500, 50),
	(17, 5, 440000, 50),
	(18, 4,  213750, 50),
	(18, 8, 400000, 50),
	(19, 4, 140000, 50),
	(19, 10, 380000, 50),
	(20, 4, 142500, 50),
	(20, 15, 400000, 50),
	(21, null, 420000, 50)
	
	INSERT INTO orders (orderID, userID, orderDate, expectedDeliveryDate, status)
VALUES
('DH001', 'AC006', '2024-05-27 10:00:00', '2024-05-30 10:00:00', N'Đã hoàn thành'),
('DH002', 'AC007', '2024-05-28 11:30:00', '2024-06-01 11:30:00', N'Đã hoàn thành'),
('DH003', 'AC008', '2024-05-28 14:26:00', '2024-06-03 14:26:00', N'Đã hoàn thành'),
('DH004', 'AC006', '2024-05-26 16:09:00', '2024-06-02 16:09:00', N'Đã hoàn thành'),
('DH005', 'AC006', '2024-05-29 18:30:00', '2024-06-04 18:30:00', N'Đã hoàn thành'),
('DH006', 'AC006', '2024-06-01 22:30:00', '2024-06-08 12:30:00', N'Đã hoàn thành'),
('DH007', 'AC007', '2024-06-01 11:15:00', '2024-06-08 11:30:00', N'Đã hoàn thành'),
('DH008', 'AC008', '2024-06-01 08:30:00', '2024-06-07 08:30:00', N'Đã hoàn thành'),
('DH009', 'AC009', '2024-06-02 10:30:00', '2024-06-10 10:30:00', N'Đã hoàn thành'),
('DH010', 'AC010', '2024-06-09 07:30:00', '2024-06-12 07:30:00', N'Đã hoàn thành'),
('DH011', 'AC011', '2024-06-12 11:30:00', '2024-06-15 11:30:00', N'Đã hoàn thành'),
('DH012', 'AC012', '2024-06-17 14:22:00', '2024-06-18 14:22:00', N'Đã hoàn thành'),
('DH013', 'AC010', '2024-06-18 17:30:00', '2024-06-22 17:30:00', N'Đã hoàn thành'),
('DH014', 'AC011', '2024-07-02 11:50:00', '2024-07-07 11:50:00', N'Đã hoàn thành'),
('DH015', 'AC012', '2024-07-04 09:45:00', '2024-07-10 09:45:00', N'Đã hoàn thành');

	INSERT INTO order_detail VALUES
	('DH001', 1, 2, 150000.00),
	('DH002', 2, 1, 200000.00),
	('DH003', 3, 4, 250000.00),
	('DH004', 4, 3, 155000.00),
	('DH005', 5, 1, 223000.00),
	('DH006', 6, 1, 450000.00),
	('DH007', 7, 2, 82000.00),
	('DH008', 8, 1, 64000.00),
	('DH009', 9, 3, 379000.00),
	('DH010', 10, 7, 1240000.00),
	('DH011', 11, 1, 264000.00),
	('DH012', 12, 2, 99000.00),
	('DH013', 13, 1, 187000.00),
	('DH014', 14, 5, 654000.00),
	('DH015', 15, 3, 450000.00);

	INSERT INTO review VALUES
    ('DH001', 5, N'Sản phẩm tuyệt vời, rất hài lòng!', '2024-03-01'),
    ('DH002', 4, N'Chất lượng tốt, nhưng giao hàng chậm.', '2024-03-26'),
    ('DH003', 3, N'Sản phẩm trung bình, không có gì đặc biệt.', '2024-04-03'),
    ('DH004', 2, N'Không hài lòng lắm với chất lượng sản phẩm.', '2024-05-04'),
    ('DH005', 1, N'Sản phẩm bị hỏng khi đến nơi.', '2024-05-25'),
    ('DH006', 5, N'Tuyệt vời! Sẽ mua lại lần nữa.', '2024-05-30'),
    ('DH007', 4, N'Khá tốt, nhưng giá có thể rẻ hơn.', '2024-06-07'),
    ('DH008', 3, N'Tạm ổn với mức giá này.', '2024-06-12'),
    ('DH009', 2, N'Sản phẩm không giống như mô tả.', '2024-06-28'),
    ('DH010', 1, N'Rất thất vọng, không đề xuất.', '2024-07-01');

	INSERT INTO images  VALUES
	(1, 'sp1.webp'), (1, 'sp1.1.webp'), (1, 'sp1.2.webp'), (1, 'sp1.3.webp'), (1, 'sp1.4.webp'),
	(2, 'sp2.webp'), (2, 'sp2.1.webp'), (2, 'sp2.2.webp'), (2, 'sp2.3.webp'),
	(3, 'sp3.webp'), (3, 'sp3.1.webp'), (3, 'sp3.2.webp'), (3, 'sp3.3.webp'), (3, 'sp3.4.webp'),
	(4, 'sp4.webp'), (4, 'sp4.1.webp'), (4, 'sp4.2.webp'), (4, 'sp4.3.webp'), (4, 'sp4.4.webp'),
	(5, 'sp5.webp'), (5, 'sp5.1.webp'), (5, 'sp5.2.webp'), (5, 'sp5.3.webp'), (5, 'sp5.4.webp'),
	(6, 'sp6.webp'), (6, 'sp6.1.webp'), (6, 'sp6.2.webp'), (6, 'sp6.3.webp'),
	(7, 'sp7.webp'), (7, 'sp7.1.webp'), (7, 'sp7.2.webp'), (7, 'sp7.3.webp'), (7, 'sp7.4.webp'),
	(8, 'sp8.webp'), (8, 'sp8.1.webp'), (8, 'sp8.2.webp'), (8, 'sp8.3.webp'),
	(9, 'sp9.webp'), (9, 'sp9.1.webp'), (9, 'sp9.2.webp'), (9, 'sp9.3.webp'), (9, 'sp9.4.webp'),
	(10, 'sp10.webp'),(10, 'sp10.1.webp'), (10, 'sp10.2.webp'), (10, 'sp10.3.webp'), (10, 'sp10.4.webp'),
	(11, 'sp11.webp'), (11, 'sp11.1.webp'), (11, 'sp11.2.webp'), (11, 'sp11.3.webp'), (11, 'sp11.4.webp'),
	(12, 'sp12.webp'), (12, 'sp12.1.webp'), (12, 'sp12.2.webp'), (12, 'sp12.3.webp'), (12, 'sp12.4.webp'),
	(13, 'sp13.webp'), (13, 'sp13.1.webp'), (13, 'sp13.2.webp'), (13, 'sp13.3.webp'), (13, 'sp13.4.webp'),
	(14, 'sp14.webp'), (14, 'sp14.1.webp'), (14, 'sp14.2.webp'), (14, 'sp14.3.webp'), (14, 'sp14.4.webp'),
	(15, 'sp15.webp'), (15, 'sp15.1.webp'), (15, 'sp15.2.webp'), (15, 'sp15.3.webp'),
	(16, 'sp16.webp'), (16, 'sp16.1.webp'), (16, 'sp16.2.webp'), (16, 'sp16.3.webp'), (16, 'sp16.4.webp'),
	(17, 'sp17.webp'), (17, 'sp17.1.webp'), (17, 'sp17.2.webp'), (17, 'sp17.3.webp'), (17, 'sp17.4.webp'),
	(18, 'sp18.webp'), (18, 'sp18.1.webp'), (18, 'sp18.2.webp'), (18, 'sp18.3.webp'), (18, 'sp18.4.webp'),
	(19, 'sp19.webp'), (19, 'sp19.1.webp'), (19, 'sp19.2.webp'), (19, 'sp19.3.webp'), (19, 'sp19.4.webp'),
	(20, 'sp20.webp'), (20, 'sp20.1.webp'), (20, 'sp20.2.webp'), (20, 'sp20.3.webp'), (20, 'sp20.4.webp'),
	(21, 'sp21.webp'), (21, 'sp21.1.webp'), (21, 'sp21.2.webp'), (21, 'sp21.3.webp')

INSERT INTO transactions 
VALUES ('DH006', 45000.00, '2024-06-01'),
('DH012', 45000.00, '2024-06-17'),
('DH009', 45000.00, '2024-06-02'),
('DH001', 45000.00, '2024-05-27'),
('DH015', 45000.00, '2024-07-04')

insert into skin_concern values 
('LD001',N'Tổ hợp'),
('LD002',N'Khô'),
('LD003',N'Da dầu'),
('LD004',N'Nhạy cảm'),
('LD005', N'Bình thường'),
('LD006',N'Mụn'),
('LD007',N'Cấp ẩm'),
('LD008',N'Calming'),
('LD009',N'Se khít lỗ chân lông')

INSERT INTO product_skinConcern (skinConcernID, productID)
VALUES
('LD004', 1), ('LD005', 1), ('LD002', 1),
('LD004', 2), ('LD006', 2), ('LD005', 2),
('LD006', 3), ('LD005', 3), ('LD004', 3),
('LD001', 4), ('LD005', 4), ('LD004', 4),
('LD005', 5), ('LD002', 5), ('LD004', 5),
('LD005', 6), ('LD004', 6),
('LD005', 7), ('LD004', 7),
('LD003', 8), ('LD001', 8), ('LD004', 8),
('LD004', 9), ('LD005', 9), ('LD002', 9),
('LD004', 10), ('LD005', 10),
('LD001', 11), ('LD004', 11), ('LD005', 11),
('LD005', 12), ('LD004', 12), ('LD002', 12),
('LD002', 13), ('LD004', 13), ('LD005', 13),
('LD001', 14), ('LD002', 14), ('LD005', 14),
('LD001', 15), ('LD003', 15),
('LD001', 16), ('LD002', 16), ('LD005', 16),
('LD002', 17), ('LD004', 17), ('LD005', 17),
('LD004', 18), ('LD005', 18),
('LD004', 19), ('LD005', 19), ('LD002', 19),
('LD002', 20), ('LD004', 20), ('LD005', 20),
('LD002', 21), ('LD004', 21), ('LD005', 21);

insert into product_type values 
('LSP001',N'Serum/ Ampoule'),
('LSP002',N'Kem chống nắng'),
('LSP003',N'Sữa rửa mặt'),
('LSP004',N'Toner'),
('LSP005',N'Kem dưỡng ẩm, làm dịu'),
('LSP006', N'Mặt nạ'),
('LSP007', N'Combo'),
('LSP008', N'Tẩy trang')


INSERT INTO product_productType (productTypeID, productID)
VALUES 
('LSP002', 1),
('LSP001', 2),
('LSP006', 3),
('LSP006', 4),
('LSP008', 5),
('LSP003', 6),
('LSP005', 7),
('LSP004', 8),
('LSP002', 9),
('LSP004', 10),
('LSP001', 11),
('LSP007', 12),
('LSP005', 13),
('LSP001', 14),
('LSP002', 15),
('LSP005', 16),
('LSP001', 17),
('LSP005', 18),
('LSP005', 19),
('LSP004', 20),
('LSP001', 21);
