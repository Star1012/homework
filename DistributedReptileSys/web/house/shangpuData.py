#!/usr/bin/env python
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import pymysql
import re
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
        create_table_sql = "create table IF NOT EXISTS " + table_name + "( id int auto_increment primary key comment '商铺主键自增'," \
                                                                        "name varchar(100) comment '商铺销售名称'," \
                                                                        "url varchar(100) comment '此条信息的地址'," \
                                                                        "LLCS varchar(50) comment'浏览次数'," \
                                                                        "QY varchar(50) comment '区域'," \
                                                                        "LJ varchar(50) comment '临近'," \
                                                                        "LX varchar(50) comment '类型'," \
                                                                        "HY varchar(50) comment '行业'," \
                                                                        "LSJY varchar(50) comment '历史经营'," \
                                                                        "MJ varchar(50) comment '面积'," \
                                                                        "ZRF varchar(50) comment '转让费'," \
                                                                        "ZJ varchar(50) comment '租金'," \
                                                                        "SJ varchar(50) comment '售价'," \
                                                                        "LXR varchar(50) comment '联系人'," \
                                                                        "XYDJ varchar(50) comment '信用等级'," \
                                                                        "RZZT varchar(50) comment '认证状态'," \
                                                                        "SSGS varchar(100) comment '所属公司'," \
                                                                        "LXDH varchar(50) comment '联系电话'," \
                                                                        "DPJS varchar(2000) comment '店铺的详细介绍'," \
                                                                        "DPZP varchar(2000) comment '店铺照片链接'" \
                                                                        ")"
        # print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except Exception as e:
        print('创建表 失败了' + table_name + " " + str(e))
        exit(1)


def get_items_name(name):
    if name == '区域':
        return 'QY'
    elif name == '临近':
        return 'LJ'
    elif name == '行业':
        return 'HY'
    elif name == '类型':
        return 'LX'
    elif name == '历史经营':
        return 'LSJY'
    elif name == '面积':
        return 'MJ'
    elif name == '转让费':
        return 'ZRF'
    elif name == '租金':
        return 'ZJ'
    else:
        return 'SJ'


def get_page_data(zhu, name, url):
    # 初始化部分
    tableName = url.split('/')[3] + 'Data'
    create_table(tableName)
    # QY = LJ = HY = LX = LSJY = MJ = ZRF = ZJ = SJ ='None'
    insert_sql = " insert into " + tableName + " (name,url,"
    insert_sql_value = " values ( '" + name + "','" + url + "',"
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    # 开始获取 商铺 页面数据
    items = soup.select('.info > li')
    # print(len(items))
    # 对于每一个 li 确定其是什么数据，（临近，行业。。）,根据其名称来给对应字段赋值
    for i in range(0, len(items)):
        insert_sql += (get_items_name(
            items[i].text.split('：')[0].split(':')[0].replace('\r\n', '').replace(' ', '').replace('\xa0', ' ')) + ",")
        # print(insert_sql)
        try:
            insert_sql_value += (
            "'" + items[i].text.split('：')[1].replace('\t', '').replace('\r\n', '').replace(' ', '').replace('\xa0',
                                                                                                             ' ') + "',")
        except:
            insert_sql_value += (
            "'" + items[i].text.split(':')[1].replace('\t', '').replace('\r\n', '').replace(' ', '').replace('\xa0',
                                                                                                             ' ') + "',")

            # print(insert_sql_value)

    # 下面两句是结束 insert 语句的编写，因为我们还有别的语句，所以注释掉，忘了
    # insert_sql += (get_items_name(items[-1].text.split('：')[0].replace('\r\n', '').replace(' ','').replace('\xa0',' ')) + ") ")
    # insert_sql_value += ("'"+items[-1].text.split('：')[1].replace('\t', '').replace('\r\n', '').replace(' ','').replace('\xa0',' ')+"')")
    # print(insert_sql+insert_sql_value)
    LLCS = soup.select('#totalcount')[0].text
    # print(LLCS)
    LXR = soup.select('a.tx')[1].text.replace('\t', '').replace('\r\n', '').replace(' ', '').replace('\xa0', ' ')
    # print(LXR)
    XYDJ = soup.select('ul > li > a > img')[0].get('title').replace('\t', '').replace('\r\n', '').replace(' ',
                                                                                                          '').replace(
        '\xa0', ' ')
    # print(XYDJ)
    try:
        RZZT = soup.select('.icon3')[0].get('title').replace('\t', '').replace('\r\n', '').replace(' ', '').replace(
            '\xa0', ' ')
    except:
        RZZT = '未认证'
    # print(RZZT)
    SSGS = soup.select('ul > li > label')[0].text if soup.find_all("label",
                                                                   attrs={"style": "color:#585858;"}) else 'None'
    # print(SSGS)
    LXDH = soup.select('#t_phone')[0].text.replace('\t', '').replace('\r\n', '').replace(' ', '').replace('\xa0', ' ')
    # print(LXDH)
    DPJS = soup.select('.maincon')[0].text.replace('\t', '').replace('\r\n', '').replace(' ', '').replace('\xa0', ' ')
    # print(DPJS)
    # DPZP = soup.select('.*?img_list.push("(.*?)").*?')
    # print(soup)
    DPZP = ''
    pattern = re.compile('.*?img_list.*?"(.*?)".*?', re.S)
    pics = re.findall(pattern, html.text)
    for pic in pics:
        DPZP = DPZP + pic + '*'
    # print(DPZP)
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        insert_sql_final = insert_sql + "LLCS,LXR,XYDJ,RZZT,SSGS,LXDH,DPJS,DPZP)" + insert_sql_value + "'" + LLCS + "'," + "'" + LXR + "','" + XYDJ + "','" + RZZT + "','" + SSGS + "','" + LXDH + "','" + DPJS + "','" + DPZP + "')"
        # print('insert_sql_final:' + insert_sql_final)
        update_sql = "update shangpu set state = '已使用' where id =" + str(zhu)
        # print(update_sql)
        cursor.execute(insert_sql_final)
        cursor.execute(update_sql)
        conn.commit()
    except Exception as e:
        print('id= ' + str(zhu) + '商铺数据插入失败,url:' + url)
        print('错误信息：' + str(e))
        conn.rollback()
        update_error_sql = "update shangpu set state = '使用失败' where id =" + str(zhu)
        cursor.execute(update_sql)
        conn.commit()
        # exit(1)
    finally:
        cursor.close()
        conn.close()


def get_shangpu_url():
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        num = 0
        lenMax = 0
        while (True):
            getsql = "select * from shangpu where state = '未使用'  limit 1"
            cursor.execute(getsql)
            conn.commit()
            link = cursor.fetchall()
            # print(link)
            if len(link) != 0:
                for cc in link:
                    if cc[0] % 50 == 0:
                        print('第' + str(cc[0]) + '个：' + cc[2] + "时间%s" % ctime())
                    # print('id：' + str(cc[0]))
                    # print('name：' + cc[1])
                    # print('url：' + cc[2])
                    # print('state：' + cc[3])
                    get_page_data(cc[0], cc[1], cc[2])
                    # if(lenMax < get_page_data(cc[0],cc[1],cc[2])):
                    #     lenMax = get_page_data(cc[0],cc[1],cc[2])
                    #     print(lenMax,cc[2],cc[0])
            # print(link)
            else:
                print('商铺表已全部使用')
                exit(1)
    except Exception as e:
        print('商铺查询链接失败' + str(e))
        exit(1)
    finally:
        cursor.close()
        conn.close()


# get_shangpu_url()
