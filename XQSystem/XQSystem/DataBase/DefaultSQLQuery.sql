--系统内部默认表项，只提供查询，非特殊原因不可修改
--drop table Subjects;
--drop table NationalEconomy;
--drop table AffiliationDepart;

--delete from Subjects;
--delete from NationalEconomy;
--delete from AffiliationDepart

--学科表
Create table Subjects(
    Code nvarchar(10),			--代码
    Name nvarchar(30),			--学科名称
    primary key(Code)
);

--国民经济分类表
Create table NationalEconomy(
    Code nvarchar(20),			--代码
    Name nvarchar(50),			--国民经济名称	
    primary key(Code)	
);

--归口单位表
Create table AffiliationDepart(
    Name nvarchar(30),			--名称
    primary key(Name)
)

