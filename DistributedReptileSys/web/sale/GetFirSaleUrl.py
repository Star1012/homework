#!/usr/bin/env python
# -*- coding: utf-8 -*-

__mtime__ = '2017/5/30'

from bs4 import BeautifulSoup
import requests
import pymysql
import sys

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
        table_live = ""
        create_table_sql = "create table IF NOT EXISTS " + table_name + "( id int auto_increment primary key comment '主键自增'," \
                                                                        "name varchar(100) comment '名称'," \
                                                                        "url varchar(100) comment '地址'," \
                                                                        "state varchar(50) comment '试用状态')"
        print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except Exception:
        print('创建表失败了')
        sys.exit(0)
        print('运行到这里')


def get_one_url(url):
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    items = soup.select('#ymenu-side > ul > li > span.dlb > a')
    # print(items)
    try:
        create_table('sale')
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        for item in items:
            # print(item.get('href'))
            if item.get('href') is None:
                continue
            try:
                name = item.get_text()
                url = url_host + item.get('href')
            except Exception as e:
                print('获取 url 出错'+ str(e))
                continue

            insert_sql = "insert into sale(name,url,state) values ('" + name + "','" + url + "','未使用')"
            cursor.execute(insert_sql)
            conn.commit()
            print('name: ' + name + '  url: ' + url)
    except Exception as e:
        print('主 url 插入数据库出错了')
        print('Error:' + str(e))
        conn.rollback()
    finally:
        cursor.close()
        conn.close()


# get_one_url('http://sjz.58.com/sale.shtml')
