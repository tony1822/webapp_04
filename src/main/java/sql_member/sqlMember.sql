DROP DATABASE IF EXISTS tia101_g2;
CREATE DATABASE  tia101_g2;
USE tia101_g2;

-- 會員區塊.............................................................
CREATE TABLE member (
    member_id int AUTO_INCREMENT PRIMARY KEY,  -- 會員編號
    member_account varchar(20), -- 會員帳號
    member_password varchar(20), -- 會員密碼
    member_name varchar(20), -- 會員姓名
    member_phone varchar(20), -- 會員電話
    member_email varchar(100) UNIQUE, -- 會員信箱
    member_register_datetime Datetime DEFAULT CURRENT_TIMESTAMP, -- 註冊時間
    member_img LONGBLOB, -- 大頭照id
    is_admin BOOLEAN DEFAULT false -- 是否是管理員
);

INSERT INTO member(member_account, member_password, member_name, member_phone, member_email,member_img)
VALUES 
('tony2892', 'tia222334',  '吳俊楷', '0926523123', 'bbac@yahoo.com.tw',null),
('david1233', 'gag770894', '陳委豪', '0928194854', 'def@yahoo.com.tw', null),
('bolo5454', 'na51492932', '謝明哲', '0992740184', 'ghi@gmail.com.tw', null),
('antya123', 'gua2718', '陳俞凱', '0918572095', 'gary@yahoo.com.tw',  null),
('wil9356', 'ac5059092',  '陳建隆', '0938291822', 'vvic@yahoo.com.tw', null),
('alex11892', 'bac250302', '陳以哲', '0909287430', 'logo@yahoo.com.tw',null);

SELECT * FROM member;