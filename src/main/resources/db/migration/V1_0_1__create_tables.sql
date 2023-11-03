--CREATE SCHEMA IF NOT EXISTS provorterr;
CREATE TABLE IF NOT EXISTS province (id SERIAL PRIMARY KEY, province_code VARCHAR(3), province_name VARCHAR(50));
CREATE TABLE IF NOT EXISTS member (id SERIAL PRIMARY KEY, member_first_name VARCHAR(30), member_last_name VARCHAR(30), member_email VARCHAR(50), member_phone_number VARCHAR(20), member_username VARCHAR(15), member_password VARCHAR(150), member_role VARCHAR(15), member_status VARCHAR(15));
