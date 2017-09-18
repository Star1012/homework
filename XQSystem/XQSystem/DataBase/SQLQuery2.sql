--delete from Demand_Collect where O_code='12345678-9';
--delete from Operating_Organization where O_Name='晨光生物科技集团股份有限公司';
select * from Operating_Organization t1 join Demand_Collect t2 on t1.O_Name=t2.O_Name where O_Name like '%邯郸市锐马面业有限公司%'
--insert into Demand_Collect values('1','12345678-9','废料回收合成','为了应对市政府提倡的企业产业链升级，节能减排，治理石家庄市空气环境并提高企业产业效能。现需要购进','1','2','3','4','5','6','7','8','9','10','11','12');
--insert into Demand_Collect values('2','12345678-9','自动化生产','为了提高产业效能，实现产业链的自动化，机械化。脱离手工作坊的低效生产，引进德国自动化机床','1','2','3','4','5','6','7','8','9','10','11','12');
