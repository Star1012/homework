
--河北省重大技术需求征集系统单位机构注册用户相关业务建表语句
--Drop table Demand_Collect;
--Drop table Operating_Organization;


--组织机构注册用户表
Create table Operating_Organization(
    O_code      nvarchar(20) not null,    --机构用户名
    Pwd         nvarchar(16) not null,    --密码
    O_Name      nvarchar(50) not null,    --组织机构名称
    O_Address   nvarchar(50) not null,    --组织机构地址
    O_Area      nvarchar(30) not null,    --组织所在地域
    O_Url       nvarchar(70),             --组织机构网址
    O_Email     nvarchar(50),             --组织机构邮箱
    O_Lperson   nvarchar(10),             --组织机构法人代表
    O_Postcode  nvarchar(6),              --组织机构邮编
    O_Contacts  nvarchar(10),             --组织机构联系人
    O_PlPhone   nvarchar(12),             --组织机构固话
    O_MbPhone   nvarchar(11),             --组织机构手机
    O_Fax       nvarchar(12),             --组织机构传真
    O_Attribute nvarchar(10),             --机构属性
    O_Tmd       nvarchar(50),             --组织机构归口部门
    O_OverView  nvarchar(500),            --组织机构概述
    role		nvarchar(20) not null,    --用户角色
    Primary Key(O_Name)
)

--组织机构技术需求表
Create table Demand_Collect(
    Demand_ID        char(12)      not null,    --需求表单
    Form_idea        nvarchar(500),             --形式审核意见
    Depart_idea      nvarchar(500),             --部门审核意见
    Manage_Depart    nvarchar(20),              --管理处室
    D_Status         int,                       --审核状态
    Lock             int,                       --表单锁 0为未被锁定浏览 1为被锁定浏览
    O_Name           nvarchar(50) ,             --组织机构名称
    D_Name           nvarchar(50) ,             --技术需求名称
    StartTime		 date         ,	            --需求开始时间
    EndTIme			 date		  ,             --需求截止时间
    Theme			 nvarchar(500),             --主要问题
    Tech_Key		 nvarchar(500),             --技术关键
    expect_target    nvarchar(500),             --预期目标
    D_Key            nvarchar(50),              --技术需求关键字
    plan_Investment  nvarchar ,                  --拟投入资金总额
    D_solution       nvarchar(50),              --技术需求解决方式
    Coo_Intention    nvarchar(50),              --合作意向单位
    Tech_Type        nvarchar(10),              --科技活动类型
    Subjects         nvarchar(10),              --学科分类
    D_Servics        nvarchar(50),              --技术需求所属领域
    D_App_Industry   nvarchar(10),              --技术需求应用行业
    Primary Key(Demand_ID),
    Foreign Key(O_Name) References Operating_Organization(O_Name)
);

--角色权限表
drop table Permission;
Create table Permission(
	role         nvarchar(20)  not null,       --角色名
	manage       bit		   not null,       --管理员权限
	addDemand    bit           not null,       --需求填报
	demandManage bit           not null,       --需求管理
	PmManage     bit           not null,       --个人信息管理
	formManage   bit           not null,       --形式审核
	departManage bit           not null,       --部门审核
	SqMangae     bit           not null,       --统计查询       
	primary key(role)   
)

