use DATN_VooTreeVeeVuu;

insert into role values ('ADMIN'),('STAFF'),('PARTNER'),('CUSTOMER');



insert into accommodation_type values ('Hotel'),
                                      ('Motel'),
                                      ('Resort'),
                                      ('Homestay'),
                                      ('Apartment'),
                                      ('Villa');

insert into room_type values ('Standard Room'),
                             ('Superior Room'),
                             ('Deluxe Room'),
                             ('Suite'),
                             ('Family Room'),
                             ('Ocean View Room'),
                             ('Garden View Room'),
                             ('Room with Kitchen'),
                             ('Room with Hot Tub'),
                             ('Premium Room'),
                             ('Vip Room'),
                             ('Loft Room'),
                             ('Pool View Room'),
                             ('Mountain View Room'),
                             ('City View Room'),
                             ('Presidential Suite'),
                             ('Junior Suite'),
                             ('Duplex Room'),
                             ('Connecting Room'),
                             ('Penthouse Suite');

insert into facilities values ('hotel.png',N'Lễ tân 24h','HOTEL'),
                              ('air-conditioner.png',N'Máy lạnh','HOTEL'),
                              ('parking.png',N'Chỗ đậu xe','HOTEL'),
                              ('spoon-and-fork.png',N'Nhà hàng','HOTEL'),
                              ('swimming.png',N'Hồ bơi','HOTEL'),
                              ('building.png',N'Thang máy','HOTEL'),
                              ('wifi.png',N'Wifi','HOTEL'),
                              ('cocktail.png',N'Quán bar','HOTEL'),
                              ('massage.png',N'Massage','HOTEL'),
                              ('karaoke.png',N'Karaoke','HOTEL'),
                              ('cocktail.png',N'Mini bar','ROOM'),
                              ('safe.png',N'Két an toàn trong phòng','ROOM'),
                              ('hairdryer.png',N'Máy sấy tóc','ROOM'),
                              ('telephone.png',N'Điện thoại nội bộ','ROOM'),
                              ('work-table.png',N'Bàn làm việc','ROOM'),
                              ('kitchen.png',N'Nhà bếp','ROOM'),
                              ('beds (1).png',N'Dịch vụ phòng 24/7','ROOM'),
                              ('iron.png',N'Dịch vụ giặt là','ROOM'),
                              ('smoking.png',N'Phòng hút thuốc','ROOM'),
                              ('wheel.png',N'Phòng cho người khuyết tật','ROOM'),
                              ('swimming-pool.png',N'Bể bơi riêng','ROOM'),
                              ('microwave.png',N'Lò vi sóng và tủ lạnh','ROOM'),
                              ('air-conditioner.png',N'Máy lạnh','ROOM'),
                              ('bathtub.png',N'Nước nóng','ROOM'),
                              ('shower.png',N'Vòi tắm','ROOM'),
                              ('waiting-area.png',N'Khu vực chờ','ROOM');
------------------------
------------------------
-- Trước khi add từ giá trị này trở đi. phải chạy chương trình và tạo tài khoản
------------------------
------------------------

-- Insert data into users (this should be done before accounts to maintain the foreign key relationship)
INSERT INTO DATN_VooTreeVeeVuu.dbo.users (dob, first_name, gender, last_name) VALUES
                                                                                  ('1990-01-01', 'John', 'MALE', 'Doe'),
                                                                                  ('1985-05-05', 'Jane', 'FEMALE', 'Smith'),
                                                                                  ('1985-05-05', 'Janes', 'FEMALE', 'Smitsh');

-- Insert data into accounts (ensure the user_id references an existing user)
INSERT INTO DATN_VooTreeVeeVuu.dbo.accounts (status, user_id, email, password, phone_num, username) VALUES
                                                                                                        (1, 1, 'john.doe@example.com', 'password123', '1234567890', 'john_doe'),
                                                                                                        (1, 2, 'jane.smith@example.com', 'password123', '0987654321', 'jane_smith'),
                                                                                                        (1, 3, 'jane.smith@examples.com', 'password123', '098765321', 'janes_smith');

-- Insert data into account_roles
INSERT INTO DATN_VooTreeVeeVuu.dbo.account_roles (account_id, role_id) VALUES
                                                                           (1, 1),
                                                                           (2, 2),
                                                                           (3, 3);



INSERT INTO hotels(address, check_in_time, check_out_time, city, edit_status, description, hotel_name, hotel_phone_num, hotel_stars, status, type_id, user_id)
VALUES
    (N'12 Trần Phú, Phường 3, Thành phố Đà Lạt.','14:00:00','12:00:00',N'Đà Lạt','ACTIVE',N'Khách sạn 5 sao với kiến trúc cổ điển và dịch vụ cao cấp.',N'Dalat Palace Heritage Hotel','0263382544',5,'ACTIVE',1,2),
    (N'58 Đào Duy Từ, Phường 4, Thành phố Đà Lạt','14:00:00','12:00:00',N'Đà Lạt','ACTIVE',N'Motel với giá cả hợp lý, phòng sạch sẽ và dịch vụ thân thiện',N'Phuong Anh Motel','0263383227',2,'ACTIVE',2,2),
    (N'Lê Lai, Phường 5, Thành phố Đà Lạt.','14:00:00','12:00:00',N'Đà Lạt','ACTIVE',N'Resort nằm giữa rừng thông với các biệt thự phong cách Pháp.',N'Ana Mandara Villas Dalat Resort & Spa','0263355588',5,'ACTIVE',3,2),
    (N'47 Đặng Thái Thân, Phường 3, Thành phố Đà Lạt.','14:00:00','12:00:00',N'Đà Lạt','ACTIVE',N'Homestay với thiết kế độc đáo, không gian thoáng đãng và tầm nhìn đẹp.',N'The Kupid Hill Homestay','0905666777',3,'ACTIVE',4,2),
    (N'10 Trần Phú, Phường 3, Thành phố Đà Lạt.','14:00:00','12:00:00',N'Đà Lạt','CREATE',N'Căn hộ tiện nghi, đầy đủ nội thất, nằm gần trung tâm thành phố',N'Dalat Home Apartments','0263382512',4,'PENDING',5,2),
    (N'32-34 Trần Phú, Lộc Thọ, Nha Trang.','14:00:00','12:00:00',N'Nha Trang','CREATE',N'Khách sạn 5 sao với dịch vụ cao cấp và vị trí đẹp',N'InterContinental Nha Trang','0258388777',4,'PENDING',1,2),
    (N'98C1/4 Trần Phú, Lộc Thọ, Nha Trang','14:00:00','12:00:00',N'Nha Trang','CREATE',N'Motel giá rẻ, phòng sạch sẽ, gần biển.',N'Thien Thanh Motel','0258352678',1,'PENDING',2,2),
    (N'Đảo Hòn Tre, Nha Trang','14:00:00','12:00:00',N'Nha Trang','ACTIVE',N'Resort 5 sao với bãi biển riêng và nhiều tiện ích giải trí.',N'Vinpearl Resort Nha Trang','0258359859',5,'ACTIVE',3,2),
    (N'31 Trần Phú, Lộc Thọ, Nha Trang.','14:00:00','12:00:00',N'Nha Trang','ACTIVE',N'Homestay với không gian ấm cúng, gần gũi, vị trí thuận lợi.',N'Kokoro Home','0905888775',3,'ACTIVE',4,2),
    (N'4 Tôn Đản, Lộc Thọ, Nha Trang','14:00:00','12:00:00',N'Nha Trang','ACTIVE',N'Căn hộ tiện nghi, dịch vụ tốt, gần biển.',N'Maple Hotel & Apartment','0258352988',4,'ACTIVE',5,2),
    (N'Bãi Bắc, Sơn Trà, Đà Nẵng','14:00:00','12:00:00',N'Đà Nẵng','ACTIVE',N'Khách sạn 5 sao sang trọng với bãi biển riêng và nhiều tiện ích',N'InterContinental Danang Sun Peninsula','0236393888',5,'ACTIVE',1,2),
    (N'125 Nguyễn Văn Thoại, Sơn Trà, Đà Nẵng','14:00:00','12:00:00',N'Đà Nẵng','ACTIVE',N'Motel với giá cả hợp lý, gần biển, phòng sạch sẽ',N'Ruby Light','0237394266',2,'ACTIVE',2,2),
    (N'Võ Nguyên Giáp, Ngũ Hành Sơn, Đà Nẵng','14:00:00','12:00:00',N'Đà Nẵng','ACTIVE',N'Resort 5 sao với dịch vụ spa toàn diện và không gian yên tĩnh.',N'Fusion Maia Danang','0936396799',5,'ACTIVE',3,2),
    (N'03 Trần Quốc Toản, Hải Châu, Đà Nẵng.','14:00:00','12:00:00',N'Đà Nẵng','ACTIVE',N'Homestay với thiết kế độc đáo, không gian ấm cúng và thân thiện.',N'Memory Hostel','0905666777',3,'ACTIVE',4,2),
    (N'120 Võ Nguyên Giáp, Sơn Trà, Đà Nẵng.','14:00:00','12:00:00',N'Đà Nẵng','UPDATE',N'Căn hộ cao cấp, view biển, đầy đủ tiện nghi và dịch vụ',N'Altara Suites Da Nang','0925268788',4,'PENDING',5,2),
    (N'159 Thùy Vân, Thắng Tam, Vũng Tàu','14:00:00','12:00:00',N'Vũng Tàu','UPDATE',N'Khách sạn 5 sao với thiết kế cổ điển, bãi biển riêng và dịch vụ cao cấp',N'The Imperial Hotel Vung Tau','0925436287',5,'PENDING',1,2),
    (N'25 Lê Hồng Phong, Vũng Tàu.','14:00:00','12:00:00',N'Vũng Tàu','UPDATE',N'Motel giá rẻ, phòng sạch sẽ, gần trung tâm.',N'Ngọc Hân Motel','0254353456',2,'PENDING',2,2),
    (N'Bãi Biển Đất Dốc, Côn Đảo, Vũng Tàu.','14:00:00','12:00:00',N'Vũng Tàu','ACTIVE',N'Resort 5 sao với không gian sang trọng, dịch vụ cao cấp và bãi biển đẹp.',N'Six Senses Con Dao','0955438312',5,'ACTIVE',3,2),
    (N'98 Phan Chu Trinh, Vũng Tàu','14:00:00','12:00:00',N'Vũng Tàu','ACTIVE',N'Homestay với thiết kế độc đáo, không gian thoáng đãng và tiện nghi.',N'The Wind Boutique','0915511378',3,'ACTIVE',4,2),
    (N'15 Thi Sách, Vũng Tàu.','14:00:00','12:00:00',N'Vũng Tàu','ACTIVE',N'Căn hộ cao cấp, view đẹp, đầy đủ tiện nghi và dịch vụ.',N'Pullman Vung Tau','0925355177',4,'ACTIVE',5,2),
    (N'6 kiet 27 Nguyễn Sinh Cung, Thành phố Huế, Huế, Việt Nam','14:00:00','12:00:00',N'Huế','ACTIVE',N'Phòng rất đẹp',N'Shine Riverside Homestay','0925354567',2,'ACTIVE',1,2),
    (N'149 Hàn Mặc Tử, Thành phố Huế, Huế, Việt Nam','14:00:00','12:00:00',N'Huế','ACTIVE',N'dịch vụ rất tốt 5 sao',N'Orchid Riverside Villa','0925358543',3,'ACTIVE',2,2),
    (N'77a Hàn Mặc Tử, Thành phố Huế, Huế, Việt Nam','14:00:00','12:00:00',N'Huế','ACTIVE',N'Khách sạn rất đẹp sạch sẽ',N'Nam Phương Riverside Villa (Nam Phuong Riverside Villa)','0924568177',2,'ACTIVE',4,2),
    (N'38 Đường Ngô Quyền, Thành phố Huế, Huế, Việt Nam','14:00:00','12:00:00',N'Huế','ACTIVE',N'vew rất đẹp',N'Mini hotel Lạc Hồng (Mini hotel Lac Hong)','0924567772',1,'ACTIVE',5,2),
    (N'48/27 ngô quyền 4, Thành phố Huế, Huế, Việt Nam','14:00:00','12:00:00',N'Huế','CREATE',N'khách sạn đẹp phụ vụ tận tình',N'Hoa ban Homestay','0925456565',3,'PENDING',2,2),
    (N'018 Phạm Xuân Huân, Sapa, Lào Cai','14:00:00','12:00:00',N'Sapa','CREATE',N'Dịch vụ tuyệt vời, nhân viên thân thiện và phòng ốc sạch sẽ. Khách sạn nằm ngay trung tâm Sapa, thuận tiện cho việc di chuyển.',N'Sapa Horizon Hotel','0214387196',1,'PENDING',5,2),
    (N'3B Thác Bạc, Sapa, Lào Cai, Việt Nam','14:00:00','12:00:00',N'Sapa','CREATE',N'Khách sạn sang trọng và hiện đại, cung cấp dịch vụ spa đẳng cấp. Vị trí thuận tiện và nhân viên nhiệt tình.',N'Eden Boutique Hotel & Spa','0934677388',5,'PENDING',1,3),
    (N'19A Đường Đông Lợi, Sapa, Lào Cai, Việt Nam','14:00:00','12:00:00',N'Sapa','CREATE',N'Khách sạn đẹp, nhân viên thân thiện và vị trí thuận lợi. Phòng ốc rộng rãi và sạch sẽ.',N'Sapa Relax Hotel & Spa','0214387117',3,'PENDING',2,3),
    (N'32 Mường Hoa, Sapa, Lào Cai, Việt Nam','14:00:00','12:00:00',N'Sapa','ACTIVE',N'Khách sạn có view đẹp, nhân viên phục vụ nhiệt tình và chuyên nghiệp. Phòng ốc sạch sẽ và thoải mái.',N'Sapa Charm Hotel','0214377288',4,'ACTIVE',3,3),
    (N'Đồi Quan 6, tổ 10, Sapa, Lào Cai, Việt Nam','14:00:00','12:00:00',N'Sapa','ACTIVE',N'Resort đẳng cấp với dịch vụ chuyên nghiệp và view đẹp tuyệt vời. Không gian yên tĩnh và thư giãn',N'Silk Path Grand Sapa Resort & Spa','0214378855',5,'ACTIVE',4,3),
    (N'Group 6, Ban Quy Hamlet, Duong To, Phú Quốc Island 92509, Vietnam','14:00:00','12:00:00',N'Phú Quốc','ACTIVE',N'Phòng rộng rãi và nhân viên chu đáo',N'Pullman Phu Quoc Beach Resort','0297267999',2,'ACTIVE',5,3),
    (N'Khu vực Bãi Dài, Gành Dầu, Đảo Phú Quốc, Việt Nam','14:00:00','12:00:00',N'Phú Quốc','ACTIVE',N'Sạch sẽ, phòng thoải mái và nhân viên thân thiện',N'Radisson Blu Resort Phu Quoc','0297366000',5,'ACTIVE',1,3),
    (N'Cửa Cạn, Đảo Phú Quốc, Việt Nam','14:00:00','12:00:00',N'Phú Quốc','ACTIVE',N'Các tiện nghi được duy trì tốt và không gian yên bình, tạo nên một kỳ nghỉ thư giãn',N'Chez Carole Beach Resort Phu Quoc','0297381999',3,'ACTIVE',2,3),
    (N'Tran Hung Dao Street, Phu Quoc Island, Kien Giang, Vietnam','14:00:00','12:00:00',N'Phú Quốc','ACTIVE',N'Khách sạn có view đẹp, nhân viên phục vụ nhiệt tình và chuyên nghiệp. Phòng ốc sạch sẽ và thoải mái.',N'The Tahiti Beach Hotel Phu Quoc','0297384899',4,'ACTIVE',3,3),
    (N'Duong Bao Hamlet, Duong To, Phu Quoc Island 91000-9200, Vietnam','14:00:00','12:00:00',N'Phú Quốc','ACTIVE',N'Resort đẳng cấp với dịch vụ chuyên nghiệp và view đẹp tuyệt vời. Không gian yên tĩnh và thư giãn',N'Novotel Phu Quoc Resort','0297626099',5,'ACTIVE',4,3),
    (N'22-24 Phan Như Thạch, Phường 1, Đà Lạt','14:00:00','12:00:00',N'Đà Lạt','UPDATE',N'Khách sạn 4 sao với phong cách kiến trúc cổ điển, gần các điểm tham quan chính.',N'Saphir Dalat Hotel','0924568177',2,'PENDING',5,3),
    (N'42 Nguyễn Chí Thanh, Phường 1, Đà Lạt','14:00:00','12:00:00',N'Đà Lạt','UPDATE',N'Khách sạn 4 sao nằm ngay trung tâm thành phố, gần chợ Đà Lạt, tiện nghi hiện đại.',N'TTC Hotel Premium Ngọc Lan','0924567772',5,'PENDING',1,3),
    (N'26-28 Ba Tháng Hai, Phường 1, Đà Lạt','14:00:00','12:00:00',N'Đà Lạt','UPDATE',N'Khách sạn 3 sao với phòng ốc sạch sẽ, giá cả phải chăng, vị trí trung tâm.',N'Tulip Hotel','0925456565',2,'PENDING',2,3),
    (N'35 Hồ Tùng Mậu, Phường 3, Đà Lạt','14:00:00','12:00:00',N'Đà Lạt','ACTIVE',N'Khách sạn giá rẻ, tiện nghi cơ bản, dịch vụ thân thiện.',N'Thien An Hotel','0263383423',4,'ACTIVE',3,3),
    (N'50 Trần Phú, Nha Trang','14:00:00','12:00:00',N'Nha Trang','ACTIVE',N'Khách sạn 4 sao với phòng ốc hiện đại, view biển, dịch vụ tốt và vị trí thuận tiện.',N'Novotel Nha Trang','0258625699',5,'ACTIVE',4,3),
    (N'9 Biệt Thự, Nha Trang','14:00:00','12:00:00',N'Nha Trang','ACTIVE',N'Khách sạn 4 sao gần bãi biển, với hồ bơi, spa và nhà hàng',N'Liberty Central Nha Trang','0258352955',2,'ACTIVE',5,3),
    (N'22 Trần Quang Khải, Nha Trang','14:00:00','12:00:00',N'Nha Trang','ACTIVE',N'Khách sạn giá rẻ với phòng ốc sạch sẽ, tiện nghi cơ bản và dịch vụ thân thiện.',N'Bondi Hotel Nha Trang','0258352453',5,'ACTIVE',1,3),
    (N'22-36 Nguyễn Huệ & 57-69F Đồng Khởi, Quận 1, TP.HCM','14:00:00','12:00:00',N'Hồ Chí Minh','ACTIVE',N'Khách sạn 5 sao, thiết kế sang trọng, tọa lạc tại trung tâm thành phố, có view nhìn ra sông Sài Gòn.',N'The Reverie Saigon','0283826686',3,'ACTIVE',2,3),
    (N'2 Công Trường Lam Sơn, Quận 1, TP.HCM','14:00:00','12:00:00',N'Hồ Chí Minh','ACTIVE',N'Khách sạn 5 sao, nằm gần Nhà hát Lớn, thiết kế cổ điển và hiện đại, có hồ bơi ngoài trời và spa.',N'Park Hyatt Saigon','0288242390',4,'ACTIVE',3,3),
    (N'73-75 Thủ Khoa Huân, Quận 1, TP.HCM','14:00:00','12:00:00',N'Hồ Chí Minh','ACTIVE',N'Khách sạn 4 sao, phong cách boutique, gần chợ Bến Thành, có spa và nhà hàng.',N'Silverland Yen Hotel','0282727389',5,'ACTIVE',4,3),
    (N'59-61 Pasteur, Quận 1, TP.HCM','14:00:00','12:00:00',N'Hồ Chí Minh','CREATE',N'Khách sạn 4 sao, vị trí trung tâm, gần chợ Bến Thành, có hồ bơi trên tầng thượng và phòng gym.',N'Liberty Central Saigon Citypoint Hotel','0283822567',2,'PENDING',5,3),
    (N'10-12 Lê Lai, Quận 1, TP.HCM','14:00:00','12:00:00',N'Hồ Chí Minh','CREATE',N'Khách sạn 3 sao, vị trí thuận tiện, phòng ốc sạch sẽ, dịch vụ tốt.',N'Ava Saigon Hotel','0283920489',5,'PENDING',1,3),
    (N'48-50 Thủ Khoa Huân, Quận 1, TP.HCM','14:00:00','12:00:00',N'Hồ Chí Minh','CREATE',N'Khách sạn 3 sao, gần chợ Bến Thành, giá cả hợp lý, có nhà hàng và phòng tập thể dục.',N'Blue Diamond Hotel','0283823823',3,'PENDING',2,3);



insert into rooms(type_id,edit_status , price, quantity, room_size, serve_breakfast, status, hotel_id, capacity) values
                                                                                                                     (1,'ACTIVE',2000000,3,60,1,'ACTIVE',2,2),
                                                                                                                     (2,'ACTIVE',3000000,3,70,0,'ACTIVE',3,3),
                                                                                                                     (2,'ACTIVE',4000000,3,45,1,'ACTIVE',1,4),
                                                                                                                     (3,'ACTIVE',5000000,3,60,0,'ACTIVE',2,1),
                                                                                                                     (4,'ACTIVE',6000000,3,70,1,'ACTIVE',3,2),
                                                                                                                     (3,'ACTIVE',7000000,3,45,0,'ACTIVE',1,3),
                                                                                                                     (4,'ACTIVE',8000000,3,60,1,'ACTIVE',2,4),
                                                                                                                     (5,'ACTIVE',9000000,3,70,0,'ACTIVE',3,1),
                                                                                                                     (6,'ACTIVE',10000000,3,45,1,'ACTIVE',4,2),
                                                                                                                     (5,'ACTIVE',11000000,3,60,0,'ACTIVE',5,3),
                                                                                                                     (6,'ACTIVE',12000000,3,70,1,'ACTIVE',6,4),
                                                                                                                     (7,'ACTIVE',13000000,3,45,0,'ACTIVE',4,1),
                                                                                                                     (7,'ACTIVE',14000000,3,60,1,'ACTIVE',5,2),
                                                                                                                     (8,'ACTIVE',15000000,3,70,0,'ACTIVE',6,3),
                                                                                                                     (8,'ACTIVE',16000000,3,45,1,'ACTIVE',4,4),
                                                                                                                     (1,'CREATE',17000000,3,60,0,'PENDING',5,1),
                                                                                                                     (1,'CREATE',18000000,3,70,1,'PENDING',6,2),
                                                                                                                     (2,'CREATE',19000000,3,45,0,'PENDING',7,3),
                                                                                                                     (2,'ACTIVE',20000000,3,60,1,'ACTIVE',8,4),
                                                                                                                     (3,'UPDATE',21000000,3,70,0,'PENDING',9,1),
                                                                                                                     (3,'ACTIVE',22000000,3,45,1,'ACTIVE',7,2),
                                                                                                                     (4,'UPDATE',23000000,3,60,0,'PENDING',8,3),
                                                                                                                     (4,'ACTIVE',24000000,3,70,1,'ACTIVE',9,4),
                                                                                                                     (5,'CREATE',25000000,3,45,0,'PENDING',7,1),
                                                                                                                     (5,'ACTIVE',26000000,3,60,1,'ACTIVE',8,2),
                                                                                                                     (6,'ACTIVE',27000000,3,70,0,'ACTIVE',9,3),
                                                                                                                     (6,'UPDATE',28000000,3,45,1,'PENDING',10,4),
                                                                                                                     (7,'ACTIVE',29000000,3,60,0,'ACTIVE',10,1),
                                                                                                                     (7,'CREATE',30000000,3,70,1,'PENDING',10,2),
                                                                                                                     (8,'ACTIVE',500000,4,45,0,'ACTIVE',11,5),
                                                                                                                     (8,'ACTIVE',600000,5,60,1,'ACTIVE',11,6),
                                                                                                                     (8,'ACTIVE',700000,6,70,0,'ACTIVE',11,7),
                                                                                                                     (8,'ACTIVE',800000,4,45,1,'ACTIVE',12,8),
                                                                                                                     (8,'ACTIVE',900000,5,60,0,'ACTIVE',12,9),
                                                                                                                     (8,'ACTIVE',1000000,6,70,1,'ACTIVE',12,10),
                                                                                                                     (8,'ACTIVE',1100000,4,45,0,'ACTIVE',13,11),
                                                                                                                     (8,'ACTIVE',1200000,5,60,1,'ACTIVE',13,12),
                                                                                                                     (8,'ACTIVE',1300000,6,70,0,'ACTIVE',13,13),
                                                                                                                     (8,'ACTIVE',1400000,4,45,1,'ACTIVE',14,14),
                                                                                                                     (8,'ACTIVE',1500000,5,60,0,'ACTIVE',14,15),
                                                                                                                     (4,'CREATE',200000,6,70,1,'PENDING',14,16),
                                                                                                                     (4,'CREATE',400000,4,45,0,'PENDING',15,17),
                                                                                                                     (4,'CREATE',600000,5,60,1,'PENDING',15,18),
                                                                                                                     (4,'CREATE',800000,6,70,0,'PENDING',15,19),
                                                                                                                     (4,'CREATE',1000000,4,45,1,'PENDING',16,20),
                                                                                                                     (4,'CREATE',1200000,5,60,0,'PENDING',16,1),
                                                                                                                     (4,'CREATE',1400000,6,70,1,'PENDING',16,2),
                                                                                                                     (4,'CREATE',1600000,4,45,0,'PENDING',17,3),
                                                                                                                     (4,'CREATE',1800000,5,60,1,'PENDING',17,4),
                                                                                                                     (4,'CREATE',2000000,6,70,0,'PENDING',17,5),
                                                                                                                     (4,'CREATE',2200000,4,45,1,'PENDING',18,6),
                                                                                                                     (4,'UPDATE',2400000,5,60,0,'PENDING',18,7),
                                                                                                                     (4,'UPDATE',2600000,6,70,1,'PENDING',18,8),
                                                                                                                     (4,'UPDATE',2800000,4,45,0,'PENDING',19,9),
                                                                                                                     (2,'UPDATE',3000000,5,60,1,'PENDING',19,10),
                                                                                                                     (2,'UPDATE',3200000,6,70,0,'PENDING',19,11),
                                                                                                                     (2,'UPDATE',3400000,4,45,1,'PENDING',20,12),
                                                                                                                     (2,'UPDATE',3600000,5,60,0,'PENDING',20,13),
                                                                                                                     (2,'UPDATE',3800000,6,70,1,'PENDING',20,14);

insert into hotel_images(image_name,hotel_id) values ('H001_1.jpg',1),
                                                     ('H001_2.jpg',1),
                                                     ('H001_3.jpg',1),
                                                     ('H001_4.jpg',1);

insert into room_images(image_name,room_id) values ('R001.jpg',1),
                                                   ('R002.jpg',4),
                                                   ('R003.jpg',7);



insert into hotel_facilities(fac_id,hotel_id) values(1,1),
                                                    (2,1),
                                                    (3,1),
                                                    (4,1),
                                                    (5,1),
                                                    (6,1),
                                                    (7,1),
                                                    (8,1),
                                                    (1,2),
                                                    (2,2),
                                                    (3,2),
                                                    (4,2),
                                                    (5,2),
                                                    (6,3),
                                                    (7,3),
                                                    (8,3),
                                                    (1,3),
                                                    (2,3),
                                                    (3,4),
                                                    (4,4),
                                                    (5,4),
                                                    (6,4);

insert into room_facilities(fac_id,room_id) values (11,1),
                                                   (12,1),
                                                   (13,1),
                                                   (14,1),
                                                   (11,2),
                                                   (12,2),
                                                   (13,2),
                                                   (14,3),
                                                   (15,3),
                                                   (16,3),
                                                   (17,4),
                                                   (18,4),
                                                   (19,4),
                                                   (20,4),
                                                   (21,5),
                                                   (22,5),
                                                   (23,5),
                                                   (24,5),
                                                   (25,5),
                                                   (11,5),
                                                   (12,6),
                                                   (13,6),
                                                   (14,6),
                                                   (15,6),
                                                   (16,7),
                                                   (17,7),
                                                   (18,7),
                                                   (19,7),
                                                   (20,8),
                                                   (21,8),
                                                   (22,8),
                                                   (23,8),
                                                   (24,9),
                                                   (25,9),
                                                   (11,9),
                                                   (12,10),
                                                   (13,10),
                                                   (14,10),
                                                   (15,10);


insert into bookings(booking_date,check_in_date,check_out_date,num_of_guest,num_of_room,review_status,status,total_price,room_id,user_id) values
                                                                                                                                              ('2024/05/01','2024/06/08','2024/06/11',2,2,1,'PENDING',100000,1,3),
                                                                                                                                              ('2024/05/02','2024/06/09','2024/06/12',3,1,1,'PENDING',200000,2,3),
                                                                                                                                              ('2024/05/03','2024/06/10','2024/06/13',4,3,1,'PENDING',300000,3,2),
                                                                                                                                              ('2024/05/04','2024/06/11','2024/06/14',4,2,1,'PAID',400000,4,2),
                                                                                                                                              ('2024/05/05','2024/06/12','2024/06/15',2,1,1,'PAID',500000,5,3),
                                                                                                                                              ('2024/05/06','2024/06/13','2024/06/16',2,1,1,'PAID',600000,6,3),
                                                                                                                                              ('2024/05/07','2024/06/14','2024/06/17',5,2,1,'PAID',700000,7,2),
                                                                                                                                              ('2024/05/08','2024/06/15','2024/06/18',2,3,1,'PAID',800000,8,2),
                                                                                                                                              ('2024/05/09','2024/06/16','2024/06/19',2,1,1,'PAID',900000,9,3),
                                                                                                                                              ('2024/05/10','2024/06/17','2024/06/20',1,2,1,'PAID',1000000,10,3),
                                                                                                                                              ('2024/05/11','2024/06/18','2024/06/21',4,2,1,'PAID',1100000,11,2),
                                                                                                                                              ('2024/05/12','2024/06/19','2024/06/22',2,2,1,'PAID',1200000,12,2),
                                                                                                                                              ('2024/05/13','2024/06/20','2024/06/23',2,3,1,'PAID',1300000,13,3),
                                                                                                                                              ('2024/05/14','2024/06/21','2024/06/24',2,2,1,'PAID',1400000,14,3),
                                                                                                                                              ('2024/05/15','2024/06/22','2024/06/25',2,3,1,'PENDING',1500000,15,2),
                                                                                                                                              ('2024/05/16','2024/06/23','2024/06/26',3,2,1,'PENDING',1600000,16,2),
                                                                                                                                              ('2024/05/17','2024/06/24','2024/06/27',4,1,1,'PAID',1700000,17,3),
                                                                                                                                              ('2024/05/18','2024/06/25','2024/06/28',2,3,1,'PAID',1800000,18,3),
                                                                                                                                              ('2024/05/19','2024/06/26','2024/06/29',5,2,1,'PAID',1900000,19,2),
                                                                                                                                              ('2024/06/01','2024/06/08','2024/06/11',2,2,0,'PENDING',100000,1,3),
                                                                                                                                              ('2024/06/02','2024/06/09','2024/06/12',3,1,0,'PENDING',200000,2,3),
                                                                                                                                              ('2024/06/03','2024/06/10','2024/06/13',4,3,0,'PENDING',300000,3,2),
                                                                                                                                              ('2024/06/04','2024/06/11','2024/06/14',4,2,0,'PAID',400000,4,2),
                                                                                                                                              ('2024/06/05','2024/06/12','2024/06/15',2,1,0,'PAID',500000,5,3),
                                                                                                                                              ('2024/06/06','2024/06/13','2024/06/16',2,1,0,'PAID',600000,6,3),
                                                                                                                                              ('2024/06/07','2024/06/14','2024/06/17',5,2,0,'PAID',700000,7,2),
                                                                                                                                              ('2024/06/08','2024/06/15','2024/06/18',2,3,0,'PAID',800000,8,2),
                                                                                                                                              ('2024/06/09','2024/06/16','2024/06/19',2,1,0,'PAID',900000,9,3),
                                                                                                                                              ('2024/06/10','2024/06/17','2024/06/20',1,2,0,'PAID',1000000,10,3),
                                                                                                                                              ('2024/06/11','2024/06/18','2024/06/21',4,2,0,'PAID',1100000,11,2),
                                                                                                                                              ('2024/06/12','2024/06/19','2024/06/22',2,2,0,'PAID',1200000,12,2),
                                                                                                                                              ('2024/06/13','2024/06/20','2024/06/23',2,3,0,'PAID',1300000,13,3),
                                                                                                                                              ('2024/06/14','2024/06/21','2024/06/24',2,2,0,'PAID',1400000,14,3),
                                                                                                                                              ('2024/06/15','2024/06/22','2024/06/25',2,3,0,'PENDING',1500000,15,2),
                                                                                                                                              ('2024/06/16','2024/06/23','2024/06/26',3,2,0,'PENDING',1600000,16,2),
                                                                                                                                              ('2024/06/17','2024/06/24','2024/06/27',4,1,0,'PAID',1700000,17,3),
                                                                                                                                              ('2024/06/18','2024/06/25','2024/06/28',2,3,0,'PAID',1800000,18,3),
                                                                                                                                              ('2024/06/19','2024/06/26','2024/06/29',5,2,0,'PAID',1900000,19,2),
                                                                                                                                              ('2023/06/01','2023/06/08','2023/06/11',2,2,0,'PENDING',100000,1,3),
                                                                                                                                              ('2023/06/02','2023/06/09','2023/06/12',3,1,0,'PENDING',200000,2,3),
                                                                                                                                              ('2023/06/03','2023/06/10','2023/06/13',4,3,0,'PENDING',300000,3,2),
                                                                                                                                              ('2023/06/04','2023/06/11','2023/06/14',4,2,0,'PAID',400000,4,2),
                                                                                                                                              ('2023/06/05','2023/06/12','2023/06/15',2,1,0,'PAID',500000,5,3),
                                                                                                                                              ('2023/06/06','2023/06/13','2023/06/16',2,1,0,'PAID',600000,6,3),
                                                                                                                                              ('2023/06/07','2023/06/14','2023/06/17',5,2,0,'PAID',700000,7,2),
                                                                                                                                              ('2023/06/08','2023/06/15','2023/06/18',2,3,0,'PAID',800000,8,2),
                                                                                                                                              ('2023/06/09','2023/06/16','2023/06/19',2,1,0,'PAID',900000,9,3),
                                                                                                                                              ('2023/06/10','2023/06/17','2023/06/20',1,2,0,'PAID',1000000,10,3),
                                                                                                                                              ('2023/06/11','2023/06/18','2023/06/21',4,2,0,'PAID',1100000,11,2),
                                                                                                                                              ('2023/06/12','2023/06/19','2023/06/22',2,2,0,'PAID',1200000,12,2),
                                                                                                                                              ('2023/06/13','2023/06/20','2023/06/23',2,3,0,'PAID',1300000,13,3),
                                                                                                                                              ('2023/06/14','2023/06/21','2023/06/24',2,2,0,'PAID',1400000,14,3),
                                                                                                                                              ('2023/06/15','2023/06/22','2023/06/25',2,3,0,'PENDING',1500000,15,2),
                                                                                                                                              ('2023/06/16','2023/06/23','2023/06/26',3,2,0,'PENDING',1600000,16,2),
                                                                                                                                              ('2023/06/17','2023/06/24','2023/06/27',4,1,0,'PAID',1700000,17,3),
                                                                                                                                              ('2023/06/18','2023/06/25','2023/06/28',2,3,0,'PAID',1800000,18,3),
                                                                                                                                              ('2023/06/19','2023/06/26','2023/06/29',5,2,0,'PAID',1900000,19,2);


INSERT INTO rating(comment, rate_date, booking_id, hotel_id, user_id, rate) VALUES
                                                                                (N'Phòng gia đình rất rộng rãi và rất sạch sẽ. Cảm giác thật tuyệt vời. Thức ăn sáng khá ngon miệng, nêm nếm vừa ăn. Tiếp tân và bảo vệ rất chu đáo Chắc chắn sẽ còn tiếp tục quay lại', '2024/10/2', 1, 1, 1, 4),
                                                                                (N'Điều đầu tiên là các nhân viên rất thân thiện, support nhiệt tình cho khách. Mình ở đây nhiều lần đều cảm thấy rất hài lòng về phong cách phục vụ của các bạn.', '2024/10/3', 2, 2, 1, 4),
                                                                                (N'Good hotel nhưng một số vấn đề vẫn còn', '2024/10/4', 3, 3, 1, 4),
                                                                                (N'Cửa phòng lỗi, kêu suốt đêm. Ban đầu còn tưởng là bị tâm linh. Nói chung là dịch vụ thực sự rất tệ. Chưa bao giờ tưởng tượng đi nghỉ mà mất ngủ vì tiếng ồn của khóa tự động ở cửa.', '2024/10/5', 4, 4, 1, 4),
                                                                                (N'Về room facilities và chất lượng đồ ăn chị không có phàn nàn nào cả. Tuy nhiên, về service và staff skills bên em nên xem lại.', '2024/10/6', 5, 5, 1, 4),
                                                                                (N'Sảnh chờ check in quá nóng, mình đến sớm nên phải ngồi chờ nhận phòng, cái đó hợp lý.', '2024/10/7', 6, 6, 1, 4),
                                                                                (N'Dịch vụ xấu, khách sạn quên cung cấp sữa tắm và dụng cụ vệ sinh', '2024/10/8', 7, 7, 1, 4),
                                                                                (N'Nhân viên rất tử tế và sẵn sàng hỗ trợ. Khách sạn là tốt đẹp và sạch sẽ, ngoại trừ họ cần phải cung cấp kem dưỡng da sau khi tắm.', '2024/10/9', 8, 8, 1, 4),
                                                                                (N'Vệ sinh phòng chưa sạch, có mùi hôi khi mới vào Phòng deluxe plus không như hình', '2024/10/10', 9, 9, 1, 4),
                                                                                (N'Tuyệt vời khách sạn, tất cả các nhân viên rất đẹp và chuyên nghiệp. Chỉ cần 5/10 phút đi bộ đến bãi biển', '2024/10/11', 10, 10, 1, 4),
                                                                                (N'Khách sạn tuyệt vời, dịch vụ tốt', '2023/01/15', 11, 11, 2, 4),
                                                                                (N'Phòng sạch sẽ, nhưng ồn ào', '2023/02/20', 12, 12, 2, 4),
                                                                                (N'Nhân viên thân thiện và nhiệt tình', '2023/03/25', 13, 13, 2, 4),
                                                                                (N'Khách sạn khá xa trung tâm', '2023/04/10', 14, 14, 2, 4),
                                                                                (N'Giá cả hợp lý, nhưng phòng nhỏ', '2023/05/05', 15, 15, 2, 4),
                                                                                (N'Tuyệt vời, sẽ quay lại', '2023/06/12', 16, 16, 2, 4),
                                                                                (N'Phòng không sạch, dịch vụ kém', '2023/07/19', 17, 17, 2, 5),
                                                                                (N'Khách sạn tốt, nhưng wifi yếu', '2023/08/23', 18, 18, 2, 5),
                                                                                (N'Dịch vụ tuyệt vời, phòng đẹp', '2023/09/01', 19, 19, 2, 5);


select * from accounts
select * from account_roles
select * from role
select * from users
select * from accommodation_type
select * from facilities
select * from hotel_facilities
select * from room_facilities
select * from hotel_images
select * from hotels
select * from rooms
select * from logs
select  *from otp
select * from rating
select * from bookings
select * from room_facilities
select * from room_type
select * from room_images
