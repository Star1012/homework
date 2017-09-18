#!/usr/bin/env python
# -*- coding: utf-8 -*-


from bs4 import BeautifulSoup
import requests
import pymysql
import time
from time import ctime

# url = 'http://sjz.58.com/shangpu/30099747281601x.shtml'
url = 'http://sjz.58.com/shangpu/29903812607667x.shtml'

host = '127.0.0.1'
username = 'root'
password = 'sql'
database = '58project'
url_host = 'http://sjz.58.com'


# 创建表
def create_table(table_name):
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        # 判断表是否存在
        create_table_sql = "create table if not exists " + table_name + " ( id int auto_increment primary key comment '手机主键自增'," \
                                                                        "type varchar(50) comment '什么类型的二手物品'," \
                                                                        "name varchar(100) comment '售卖名称'," \
                                                                        "url varchar(100) comment '网页地址'," \
                                                                        "LLCS varchar(50) comment '浏览次数'," \
                                                                        "XMRS varchar(50) comment '想买人数'," \
                                                                        "XJ   varchar(50) comment '现价'," \
                                                                        "QY   varchar(50) comment '区域'," \
                                                                        "LXR  varchar(50) comment '联系人'," \
                                                                        "BBMS varchar(2000) comment '宝贝描述'," \
                                                                        "BBZP varchar(2000) comment '宝贝照片')"
        # print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except Exception as e:
        print('创建表 失败了' + table_name + " " + str(e))
        exit(1)


def get_page_data(id, name, url, type):
    table_name = 'saleDetailData'
    create_table(table_name)
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    LLCS = soup.select('.look_time')[0].text
    if LLCS == '次浏览':
        print(str(id) + '空链接 ' + url)
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        cursor.execute("update salesec set state = '空链接' where id = %s" % str(id))
        conn.commit()
        return
        # exit(1)
    # print(LLCS)

    XMRS = soup.select('.want_person')[0].text
    # print(XMRS)
    XJ = soup.select('.price_now > i')[0].text
    # print(XJ)
    QY = soup.select('.palce_li > span > i')[0].text
    # print(QY)
    LXR = soup.select('.personal_name')[0].text
    # print(repr(LXR))
    BBMS = soup.select('.baby_kuang > p')[0].text
    # print(BBMS)
    BBZPs = soup.select('#img_smalls > li > img')
    BBZP = ''
    if BBZPs != []:
        for ZP in BBZPs:
            BBZP += (ZP.get('rel') + '*')
        BBZP = BBZP[:-1]
    # print(BBZP)
    insert_sql = "insert into " + table_name + " (type,name,url,LLCS,XMRS,XJ,QY,LXR,BBMS,BBZP) value('" + type + " ','" + name + "','" + url + "','" + LLCS + "','" + XMRS + "','" + XJ + "','" + QY + "','" + LXR + "','" + BBMS + "','" + BBZP + "')"
    insert_sql2 = "insert into " + table_name + " (type,name,url,LLCS,XMRS,XJ,QY,LXR,BBMS,BBZP) value('" + type + "','" + name + "','" + url + "','" + LLCS + "','" + XMRS + "','" + XJ + "','" + QY + "',' ','" + BBMS + "','" + BBZP + "')"
    # print(insert_sql)
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        cursor.execute("update salesec set state = '已使用' where id=%s" % str(id))
        try:
            cursor.execute(insert_sql)
        except:
            cursor.execute(insert_sql2)
        conn.commit()
    except Exception as e:
        print('sale 二级插入失败，自动舍弃：' + str(e) + url + insert_sql)
        conn.rollback()
        cursor.execute("update salesec set state = '舍弃' where id=%s" % str(id))
        conn.commit()
        # exit(1)
    finally:
        cursor.close()
        conn.close()


def get_sale_data():
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        while (True):
            getsql = "select * from salesec where state = '未使用'  limit 1"
            cursor.execute(getsql)
            conn.commit()
            link = cursor.fetchall()
            # print(link)
            if len(link) != 0:
                # time.sleep(1)
                for cc in link:
                    if cc[0] % 50 == 0:
                        print('第' + str(cc[0]) + '个：' + cc[2] + "时间%s" % ctime())
                        time.sleep(5)
                    # print('第'+str(cc[0])+'个：'+cc[2] +"时间%s" %ctime())
                    # exit(1)
                    # print('id：' + str(cc[0]))
                    # print('name：' + cc[1])
                    # print('id：' + str(cc[0]) + 'url：' + cc[2] + "时间%s" % ctime())
                    # print('state：' + cc[3])
                    get_page_data(cc[0], cc[1], cc[2], cc[3])
                    # if(lenMax < get_page_data(cc[0],cc[1],cc[2])):
                    #     lenMax = get_page_data(cc[0],cc[1],cc[2])
                    #     print(lenMax,cc[2],cc[0])
            # print(link)
            else:
                print('sale二级表已全部使用')
                exit(1)
    except Exception as e:
        print('sale二级查询链接失败' + str(e))
        exit(1)
    finally:
        cursor.close()
        conn.close()


# get_shouji_data()
