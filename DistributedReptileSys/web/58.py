#!/usr/bin/env python
# -*- coding: utf-8 -*-

__mtime__ = '2017/6/7'

from bs4 import BeautifulSoup
import requests
import pymysql

url = 'http://sjz.58.com/'
url_host = 'http://sjz.58.com'

host = '127.0.0.1'
username = 'root'
password = '153532'
database = '58project'
url_host = 'http://sjz.58.com'


# 建立 58 同城主界面链接的数据库（最大的链接）
def create_58main_table(tableName):
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        create_table_sql = 'create table if not exists ' + tableName + '(id int auto_increment primary key,' \
                                                                       'name varchar(100),' \
                                                                       'url varchar(100),' \
                                                                       'state varchar(50))'
        print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except:
        print('建立 ' + tableName + '表出错了')
        exit(1)


def get_58_main(url):
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    ass = soup.select('body > div > div > div > div > div > div > h2 > a')
    # tableName = soup.select('head > title').__getitem__(0).get_text()
    tableName = '58mainUrl'
    create_58main_table(tableName)
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
    except:
        print('连接数据' + tableName + '失败')
        exit(1)
    for a in ass:
        if str(a.get('href'))[0:5] != 'http:':
            link = url_host + a.get('href').split('?')[0]
            name = a.get_text()
            insert_sql = "insert into " + tableName + "(name,url,state) values ('" + name + "','" + link + "','未查询')"
            print('insert_sql:' + insert_sql)
            try:
                cursor.execute(insert_sql)
                conn.commit()
            except:
                print('数据表' + tableName + '插入数据失败')
                exit(1)
            print(link)
            print(name)
    try:
        cursor.close()
        conn.close()
    except:
        print('数据表' + tableName + '关闭失败')
        exit(1)

# if __name__ is '__main__':
get_58_main(url)
print('ss')
