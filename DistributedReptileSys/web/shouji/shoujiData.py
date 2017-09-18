#!/usr/bin/env python
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import pymysql
from time import ctime
import time

# url = 'http://sjz.58.com/zufang/30215318181571x.shtml'
url = 'http://zhuanzhuan.58.com/detail/753003941798297604z.shtml'

host = '127.0.0.1'
username = 'root'
password = 'sql'
database = '58project'


# 创建表
def create_table(table_name):
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        # 判断表是否存在
        create_table_sql = "create table if not exists " + table_name + " ( id int auto_increment primary key comment '手机主键自增'," \
                                                                        "name varchar(100) comment '手机售卖名称'," \
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


def get_page_data(id, name, url):
    table_name = 'shoujiData'
    create_table(table_name)
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    LLCS = soup.select('.look_time')[0].text
    if LLCS == '次浏览':
        print('空链接' + url)
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        cursor.execute("update shouji set state = '空链接' where id = %s" % str(id))
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
    insert_sql = "insert into " + table_name + " (name,url,LLCS,XMRS,XJ,QY,LXR,BBMS,BBZP) value('" + name + "','" + url + "','" + LLCS + "','" + XMRS + "','" + XJ + "','" + QY + "','" + LXR + "','" + BBMS + "','" + BBZP + "')"
    insert_sql2 = "insert into " + table_name + " (name,url,LLCS,XMRS,XJ,QY,LXR,BBMS,BBZP) value('" + name + "','" + url + "','" + LLCS + "','" + XMRS + "','" + XJ + "','" + QY + "',' ','" + BBMS + "','" + BBZP + "')"
    # print(insert_sql)
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        cursor.execute("update shouji set state = '已使用' where id=%s" % str(id))
        try:
            cursor.execute(insert_sql)
        except:
            cursor.execute(insert_sql2)
        conn.commit()
    except Exception as e:
        print('手机插入失败：' + str(e))
        conn.rollback()
        exit(1)
    finally:
        cursor.close()
        conn.close()


# get_page_data(1, '1', url)


def get_shouji_data():
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        while (True):
            getsql = "select * from shouji where state = '未使用'  limit 1"
            cursor.execute(getsql)
            conn.commit()
            link = cursor.fetchall()
            # print(link)
            if len(link) != 0:
                # time.sleep(1)
                for cc in link:
                    # if cc[0] % 50 == 0:
                    #     print('第' + str(cc[0]) + '个：' + cc[2] + "时间%s" % ctime())
                    # time.sleep(5)
                    # print('第'+str(cc[0])+'个：'+cc[2] +"时间%s" %ctime())
                    # exit(1)
                    # print('id：' + str(cc[0]))
                    # print('name：' + cc[1])
                    print('id：' + str(cc[0]) + 'url：' + cc[2] + "时间%s" % ctime())
                    # print('state：' + cc[3])
                    get_page_data(cc[0], cc[1], cc[2])
                    # if(lenMax < get_page_data(cc[0],cc[1],cc[2])):
                    #     lenMax = get_page_data(cc[0],cc[1],cc[2])
                    #     print(lenMax,cc[2],cc[0])
            # print(link)
            else:
                print('手机表已全部使用')
                exit(1)
    except Exception as e:
        print('手机查询链接失败' + str(e))
        exit(1)
    finally:
        cursor.close()
        conn.close()


get_shouji_data()
