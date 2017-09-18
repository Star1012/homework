#!/usr/bin/env python
# -*- coding: utf-8 -*-

__mtime__ = '2017/6/16'

from house.shangpu import auto_changePage
from house.shangpuData import get_shangpu_url
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
        cursor.execute("drop table shangpu ")
        cursor.execute("drop table shangpuData")
        conn.commit()
    except Exception as e:
        print("初始化商铺表失败"+str(e))
    finally:
        cursor.close()
        conn.close()

print('初始化...')
init()
print('开始爬取商铺表链接')
auto_changePage('http://sjz.58.com/shangpu/')
print('爬取商铺表详情')
get_shangpu_url()
