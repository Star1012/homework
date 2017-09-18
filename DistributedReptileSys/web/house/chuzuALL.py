#!/usr/bin/env python
# -*- coding: utf-8 -*-

from house.chuzu import auto_changePage
from house.chuzuData import get_chuzu_url
import pymysql

host = '127.0.0.1'
username = 'root'
password = 'sql'
database = '58project'
url_host = 'http://sjz.58.com'
def init():
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        cursor.execute("drop table chuzu ")
        cursor.execute("drop table chuzuDate")
        conn.commit()
    except Exception as e:
        print("初始化出租表失败"+str(e))
    finally:
        cursor.close()
        conn.close()
print('初始化...')
init()
print('开始爬取出租链接')
auto_changePage('http://sjz.58.com/chuzu/')
print('出租开始爬取详细信息')
get_chuzu_url()
