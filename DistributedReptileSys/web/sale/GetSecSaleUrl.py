#!/usr/bin/env python
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import pymysql
from time import ctime

host = '127.0.0.1'
username = 'root'
password = 'sql'
database = '58project'


# 创建表
def create_table(table_name):
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        create_table_sql = "create table IF NOT EXISTS " + table_name + "( id int auto_increment primary key comment '主键自增'," \
                                                                        "name varchar(100) comment '名称'," \
                                                                        "url varchar(100) comment '地址'," \
                                                                        "type varchar(50) comment '什么类型的二手物品'," \
                                                                        "state varchar(50) comment '使用状态')"
        # print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except Exception:
        print('创建表失败了')
        exit(1)
        print('运行到这里')


# 得到二级链接
def get_sec_url(url, type):
    table_name = 'saleSec'
    # print(table_name)
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    no_exist = soup.select('#infolist > div.pager > strong > span')
    if no_exist:  # 页面存在
        try:
            create_table(table_name)
        except:
            print('表创建失败')
            exit(1)
        try:
            items = soup.select('#infolist > div > table > tbody > tr > td.t > a')
            conn = pymysql.connect(host, username, password, database, charset='utf8')
            cursor = conn.cursor()
            for item in items:
                name = str(item.get_text()).strip()
                url = item.get('href').split('?')[0]
                if url.split('.')[-1] == 'shtml':
                    select_url = "select name from " + table_name + " where url = '" + url + "'"
                    num = cursor.execute(select_url)
                    if num != 0:
                        continue
                    insert_sql = "insert into " + table_name + "(name,url,type,state) values ('" + name + "','" + url + "','" + type + "','未使用')"
                    # print(insert_sql)
                    cursor.execute(insert_sql)
                    conn.commit()
        except Exception as e:
            print('二级 URL 保存失败' + name + url+str(e)+insert_sql)
        finally:
            cursor.close()
            conn.close()

    else:
        # print('空页')
        return True


def auto_change_page(id, name, url):
    pageNum = 1
    page_exist = 0
    while (page_exist < 2):
        two_url = url + 'pn' + str(pageNum)
        print('two_url:' + two_url)
        if get_sec_url(two_url, name):
            page_exist += 1
        pageNum += 1
    print(str(id) + name + url + '爬完')


def get_all_url():
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        while (True):
            getsql = "select * from sale where state = '未使用'  limit 1"
            cursor.execute(getsql)
            conn.commit()
            link = cursor.fetchall()
            if len(link) != 0:
                for cc in link:
                    print('id：' + str(cc[0]) + 'url：' + cc[2] + "时间%s" % ctime())
                    auto_change_page(cc[0], cc[1], cc[2])
                    cursor.execute("update sale set state = '已使用' where id = " + str(cc[0]))
                    conn.commit()
            else:
                print('二手二级链接表已全部使用')
                break
    except Exception as e:
        print('手机查询链接失败' + str(e))
        exit(1)
    finally:
        cursor.close()
        conn.close()


# get_all_url()
# get_sec_url('http://sjz.58.com/ershoujiaju/pn2')
