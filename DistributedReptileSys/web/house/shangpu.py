#!/usr/bin/env python
# -*- coding: utf-8 -*-

__mtime__ = '2017/6/7'
from bs4 import BeautifulSoup
import requests
import pymysql

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
        create_table_sql = "create table IF NOT EXISTS " + table_name + "( id int auto_increment primary key," \
                                                                        "name varchar(100)," \
                                                                        "url varchar(100)," \
                                                                        "state varchar(20) comment '判断是否已使用')"
        # print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except Exception:
        print('创建表失败了')
        exit(1)
        print('运行到这里')


# 将获取的 url name 保存到数据库中
# def save_url_name(url,name):


# 获取 soup 中的链接和文字信息
def get_url_name(soup, table_name):
    # 每一种页面的爬取格式都不相同
    # print(soup)
    items = soup.select('tr > td.t > a')
    # 用别的css选择路径不行，why
    # print(items)
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        for item in items:
            if item.get('href') is not None:
                # print(item)
                url = item.get('href').split('?')[0]
                if url.split('.')[-1] == 'shtml':
                    name = str(item.get_text()).strip()
                    insert_sql = "insert into " + table_name + "(url,name,state) values ('" + url + "','" + name + "','未使用')"
                    cursor.execute(insert_sql)
                    conn.commit()
                    # print('url:'+url+'\tname:'+name)
                    # print(insert_sql)
    except:
        print('ershoufang 链接失败')
        exit(1)
    finally:
        cursor.close()
        conn.close()


def get_one_url(url):
    table_name = url.split('/')[3]
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    no_exist = soup.select('.pager > strong > span')
    if no_exist:  # 页面存在
        try:
            create_table(table_name)
        except:
            print('表创建失败')
            exit(1)
        get_url_name(soup, table_name)
        return False  # 非空
    else:
        return True  # 空页


def auto_changePage(url):
    pageNum = 1
    page_exist = 0
    while (page_exist < 2):
        list_url = url + 'pn' + str(pageNum)
        print('list_url:' + list_url)
        # 空页返回 True
        if get_one_url(list_url):
            page_exist += 1
        pageNum += 1


# auto_changePage('http://sjz.58.com/shangpu/')
