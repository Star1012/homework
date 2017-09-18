#!/usr/bin/env python
# -*- coding: utf-8 -*-

__mtime__ = '2017/5/30'

# 获取子列表中的链接
from bs4 import BeautifulSoup
import requests
import pymysql
import sys

host = '127.0.0.1'
username = 'root'
password = '153532'
database = '58project'
url_host = 'http://sjz.58.com'


# 创建表
def create_table(table_name):
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        # 判断表是否存在
        table_live = ""
        create_table_sql = "create table IF NOT EXISTS " + table_name + "( id int auto_increment primary key," \
                                                                        "name varchar(100)," \
                                                                        "url varchar(100)    )"
        # print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except Exception:
        print('创建表失败了')
        sys.exit(1)
        print('运行到这里')


def get_two_url(url):
    table_name = url.split('/')[3]
    # print(table_name)
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    no_exist = soup.select('#infolist > div.pager > strong > span')
    if no_exist:  # 页面存在
        try:
            create_table(table_name)
        except:
            print('表创建失败')
            sys.exit(1)

        # items = soup.select('tbody > tr > td.t > a')
        try:
            items = soup.select('#infolist > div > table > tbody > tr > td.t > a')
            conn = pymysql.connect(host, username, password, database, charset='utf8')
            cursor = conn.cursor()
            for item in items:
                # print(item)
                name = item.get_text()
                url = item.get('href').split('?')[0]
                if url.split('.')[-1] == 'shtml':
                    # print('name: ' + name + '  url: ' + url)
                    # url = 'http://zhuanzhuan.58.com/detail/843074066835144713z.shtml'
                    select_url = "select name from " + table_name + " where url = '" + url + "'"
                    # print(select_url)
                    num = cursor.execute(select_url)
                    # print(num)
                    if num != 0:
                        continue
                    insert_sql = "insert into " + table_name + "(name,url) values ('" + name + "','" + url + "')"
                    cursor.execute(insert_sql)
                    conn.commit()
                    # print(insert_sql)
        except:
            print('二级 URL 保存失败' + name)
        finally:
            cursor.close()
            conn.close()

    else:
        print('空页')
        return True


def auto_change_page(url):
    pageNum = 1
    page_exist = 0
    while (page_exist < 2):
        two_url = url + 'pn' + str(pageNum)
        print('two_url:' + two_url)
        if get_two_url(two_url):
            page_exist += 1
        pageNum += 1


auto_change_page('http://sjz.58.com/shouji/')
