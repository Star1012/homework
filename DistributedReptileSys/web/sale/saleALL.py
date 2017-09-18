#!/usr/bin/env python
# -*- coding: utf-8 -*-

__mtime__ = '2017/6/16'

from sale.GetFirSaleUrl import get_one_url
from sale.GetSecSaleUrl import get_all_url
from sale.get_sale_data import get_sale_data
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
        cursor.execute("drop table sale")
        cursor.execute("drop table salesec ")
        cursor.execute("drop table saleDetailData")
        conn.commit()
    except Exception as e:
        print("初始化商铺表失败"+str(e))
    finally:
        cursor.close()
        conn.close()

# print('初始化中...')
# init()
# print('开始爬取二手顶层链接')
# get_one_url('http://sjz.58.com/sale.shtml')
# print('开始爬取二手二层链接')
# get_all_url()
# print('开始爬取二手详情页')
get_sale_data()
