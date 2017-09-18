#!/usr/bin/env python
# -*- coding: utf-8 -*-
# 用于获取出租房信息

from bs4 import BeautifulSoup
import requests
import pymysql
from time import ctime
import time

# url = 'http://sjz.58.com/zufang/30215318181571x.shtml'
url = 'http://sjz.58.com/zufang/30301405914832x.shtml'

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
        create_table_sql = "create table if not exists " + table_name + "( id int auto_increment primary key comment '出租主键自增'," \
                                                                        "name varchar(100) comment '出租房出租名称'," \
                                                                        "url varchar(100) comment '网页地址'," \
                                                                        "FD varchar(50) comment '房东'," \
                                                                        "LXDH varchar(50) comment '联系电话'," \
                                                                        "FBSJ varchar(50) comment '发布时间'," \
                                                                        "LLCS varchar(50) comment '浏览次数'," \
                                                                        "ZLJG varchar(50) comment '租赁价格'," \
                                                                        "ZLYF varchar(50) comment '租赁押付'," \
                                                                        "ZLFS varchar(50) comment '租赁方式'," \
                                                                        "FWLX varchar(50) comment '房屋类型'," \
                                                                        "CXLC varchar(50) comment '朝向楼层'," \
                                                                        "SZXQ varchar(50) comment '所在小区'," \
                                                                        "QY varchar(50) comment '所属区域'," \
                                                                        "XXDZ varchar(200) comment '详细地址'," \
                                                                        "FYXQ varchar(200) comment '房源详情'," \
                                                                        "FYMS varchar(2000) comment '房源描述'," \
                                                                        "FYZP varchar(2000) comment '房源照片'," \
                                                                        "JZND varchar(50) comment '建筑年代'," \
                                                                        "JZLX varchar(50) comment '建筑类型'," \
                                                                        "WYGS varchar(50) comment '物业公司'," \
                                                                        "WYFY varchar(50) comment '物业费用'," \
                                                                        "SSSQ varchar(50) comment '所属商圈')"
        # print(create_table_sql)
        cursor.execute(create_table_sql)
        conn.commit()
        cursor.close()
        conn.close()
    except Exception as e:
        print('创建表 失败了' + table_name + " " + str(e))
        exit(1)


def get_items_name(name):
    if name == '租赁价格':
        return 'ZLJG'
    elif name == '租赁方式':
        return 'ZLFS'
    elif name == '房屋类型':
        return 'FWLX'
    elif name == '朝向楼层':
        return 'CXLC'
    elif name == '所在小区':
        return 'SZXQ'
    elif name == '所属区域':
        return 'QY'
    elif name == '详细地址':
        return 'XXDZ'
    elif name == '联系电话':
        return 'LXDH'
    elif name == '房源详情':
        return 'FYXQ'
    elif name == '房源描述':
        return 'FYMS'
    elif name == '房源照片':
        return 'FYZP'
    elif name == '建筑年代':
        return 'JZND'
    elif name == '建筑类型':
        return 'JZLX'
    elif name == '物业公司':
        return 'WYGS'
    elif name == '物业费用':
        return 'WYFY'
    elif name == '所属商圈':
        return 'SSSQ'
    else:
        print('没找到对应的属性，请确认是否出现错误：' + name)


def is_None_page(soup, url):
    no_longer_exist = '404' in soup.find_all('script', type="text/javascript")[0].get('src').split('/')
    print(no_longer_exist)
    if no_longer_exist:
        update_sql = "update chuzu set state = '空链接'"
        try:
            conn = pymysql.connect(host, username, password, database, charset='utf8')
            cursor = conn.cursor()
            cursor.execute(update_sql)
            conn.commit()
        except Exception as e:
            conn.rollback()
            print('出现空链接' + str(e))
            exit(1)
        finally:
            cursor.close()
            conn.close()
            print('ss')
            exit(1)


def get_page_data(id, name, url):
    # 初始化，组装 sql 语句,创建数据表
    table_name = 'chuzu' + 'Data'
    create_table(table_name)
    insert_head = "insert into " + table_name + " (name,url,"
    insert_value = " value('" + name + "','" + url + "',"
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'lxml')
    # 判断非空
    # is_None_page(soup,url)
    try:
        FBSJ = soup.select('.house-update-info')[0].text.split('更新')[0].replace('\r\n', '').replace(' ', '')
    except Exception as e:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        update_sql = "update chuzu set state = '空链接' where id = " + str(id)
        print(update_sql)
        cursor.execute(update_sql)
        conn.commit()
        print('出现空链接' + str(e) + url)
        cursor.close()
        conn.close()
        print('ss')
        return False
        # exit(1)
    insert_head += "FBSJ,"
    insert_value += "'" + FBSJ + "',"
    # print(repr(FBSJ))
    LLCS = soup.select('#totalcount')[0].text
    insert_head += "LLCS,"
    insert_value += "'" + LLCS + "',"
    # print(LLCS)
    ZLJG = soup.select('span.c_ff552e')[0].text
    insert_head += "ZLJG,"
    insert_value += "'" + ZLJG + "',"
    # print(ZLJG)
    ZLYF = soup.select('span.c_333')[0].text
    insert_head += 'ZLYF,'
    insert_value += "'" + ZLYF + "',"
    # print(ZLYF)
    items = soup.select('ul.f14 > li')
    for i in range(0, len(items)):
        try:
            insert_value += (
                "'" + items[i].text.split('：')[1].replace('\r\n', '').replace(' ', '').replace('\xa0', ' ').replace(
                    '\n',
                    '') + "',")
        except:
            # print(items[i])
            continue
        insert_head += (get_items_name(
            items[i].text.split('：')[0].replace('\r\n', '').replace(' ', '').replace('\xa0', ' ').replace('\n',
                                                                                                          '')) + ',')
    FD = soup.select('.c_000')[0].text
    insert_head += 'FD,'
    insert_value += "'" + FD + "',"
    # print(FD)
    LXDH = soup.select('.phone-num')[0].text if soup.find('.phone-num') else 'None'
    insert_head += 'LXDH,'
    insert_value += "'" + LXDH + "',"
    # print(LXDH)
    FYXQs = soup.select('.house-disposal > li')
    # print(FYXQs)
    FYXQ = ''
    if FYXQs != []:
        # print('空')
        for XQ in FYXQs:
            FYXQ += (XQ.text + '*')
        FYXQ = FYXQ[:-1]
    insert_head += 'FYXQ,'
    insert_value += "'" + FYXQ + "',"
    # print(FYXQ)
    FYMS = soup.select('.introduce-item')[0].text.replace('\r\n', '').replace(' ', '')
    insert_head += 'FYMS,'
    insert_value += "'" + FYMS + "',"
    # print(repr(FYMS))
    # exit(1)
    FYZPs = soup.select('#leftImg > li > img')
    # print(FYZPs)
    FYZP = ''
    for ZP in FYZPs:
        # print(ZP)
        FYZP += (ZP.get('src') + '*')
    FYZP = FYZP[:-1]
    insert_head += 'FYZP)'
    insert_value += "'" + FYZP + "')"
    # print(FYZP)
    insert_sql = insert_head + insert_value
    # print(insert_sql)
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        update_sql = "update chuzu set state = '已使用' where id =" + str(id)
        # print(update_sql)
        cursor.execute(insert_sql)
        cursor.execute(update_sql)
        conn.commit()
    except Exception as e:
        print('id= ' + str(id) + '商铺数据插入失败,url:' + url)
        print('错误信息：' + str(e))
        conn.rollback()
        update_error_sql = "update chuzu set state = '使用失败' where id =" + str(id)
        cursor.execute(update_sql)
        conn.commit()
        exit(1)
    finally:
        cursor.close()
        conn.close()


def get_chuzu_url():
    try:
        conn = pymysql.connect(host, username, password, database, charset='utf8')
        cursor = conn.cursor()
        num = 0
        lenMax = 0
        while (True):
            getsql = "select * from chuzu where state = '未使用'  limit 1"
            cursor.execute(getsql)
            conn.commit()
            link = cursor.fetchall()
            # print(link)
            if len(link) != 0:
                # time.sleep(1)
                for cc in link:
                    if cc[0] % 50 == 0:
                        print('第' + str(cc[0]) + '个：' + cc[2] + "时间%s" % ctime())
                        # time.sleep(5)
                        # print('第'+str(cc[0])+'个：'+cc[2] +"时间%s" %ctime())
                        # exit(1)
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
                print('出租表已全部使用')
                exit(1)
    except Exception as e:
        print('出租查询链接失败' + str(e))
        exit(1)
    finally:
        cursor.close()
        conn.close()

# get_chuzu_url()
# get_page_data(1,'1','http://sjz.58.com/hezu/30360800078646x.shtml')
