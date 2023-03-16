drop DATABASE DoAn_SV
CREATE DATABASE DoAn_SV
GO
USE DoAn_SV
GO

CREATE TABLE MonHoc(
	MaMH NVARCHAR(30) PRIMARY KEY,
	TenMH NVARCHAR(30)
)

CREATE TABLE SinhVien(
	MaSV NVARCHAR(30) PRIMARY KEY,
	HoTen NVARCHAR(30),
	SDT NVARCHAR(30),
	GioiTinh BIT,
	Email NVARCHAR(30)
)

CREATE TABLE DiemThi(
	MaSV NVARCHAR(30),
	MaMH NVARCHAR(30),
	DiemGK FLOAT,
	DiemCK FLOAT,
	DiemTK FLOAT
)
ALTER TABLE DiemThi ADD CONSTRAINT FK_DIEMTHI_SINHVIEN FOREIGN KEY (MaSV)REFERENCES SinhVien(MaSV)
ALTER TABLE DiemThi ADD CONSTRAINT FK_DIEMTHI_MONHOC FOREIGN KEY (MaMH)REFERENCES MonHoc(MaMH)
 

CREATE TABLE Users(
	ID INT PRIMARY KEY,
	USERNAME NVARCHAR(30),
	PASSWORD NVARCHAR(30)
)
select *from SinhVien
insert into SinhVien(MaSV,HoTen,SDT,GioiTinh,Email)
values 
(19489821, 'Nguyen Thien Thu', '0385157703', 0, 'minhvy@gmail.com' ),
(19505811, 'Nguyen Minh Vy', '0385157704', 1, 'minhvy@gmail.com' );

insert into MonHoc([MaMH],[TenMH])
values (001, 'Lap Trinh Phan Tan Java'),
(002, 'Lap Trinh Huong Su Kien Java'),
(003, 'Lap Trinh Huong Doi Tuong Java');

select *from Users
select * from [dbo].[DiemThi]
delete from SinhVien where MaSV='19505811'