package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.Goods;
import Util.DBBean;
import Util.getStr;

import com.google.gson.Gson;

public class getGoods extends HttpServlet {
	// 处理 GET 方法请求的方法
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		String table = request.getParameter("table");
		PrintWriter out = response.getWriter();
		DBBean db = new DBBean();
		try {
			db.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//获取商品表所有类型
		List<String> typelist = new ArrayList<String>();
		String sqltype = "select distinct type from "+table;
		try {
			ResultSet rstype = db.select(sqltype);
			while(rstype.next())
			{
				typelist.add(rstype.getString(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取不同类型商品的总数
		Map<String,String> countMp = new HashMap();
		List<String> count = new ArrayList<String>();
		for(int i=0;i<typelist.size();i++)
		{
			String sqlcount = "select count(*) from "+table+" where type='"+typelist.get(i)+"'";
			try {
				ResultSet rscount = db.select(sqlcount);
				while(rscount.next())
				{
					countMp.put(typelist.get(i),rscount.getString(1));
					count.add(rscount.getString(1));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//定义商品列表
		List<Goods> goodlist = new ArrayList<Goods>();
		String sql = "select * from "+table;
		try {
			ResultSet rs = db.select(sql);
			while(rs.next())
			{
				Goods good = new Goods();
				getStr gs = new getStr();
				good.setUrl(rs.getString("url"));
				good.setName(rs.getString("name").replaceAll(" ",""));
				good.setLlcs(gs.getNumfromStr(rs.getString("llcs")));
				good.setLxr(rs.getString("lxr"));
				good.setQy(rs.getString("qy"));
				good.setType(rs.getString("type"));
				good.setXj(rs.getString("xj"));
				good.setXmrs(gs.getNumfromStr(rs.getString("xmrs")));
				good.setCount(countMp.get(rs.getString("type")));
				good.setPage("1");
				goodlist.add(good);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取所有区域分布
		List<String> arealist = new ArrayList<String>();
		List<String> xxarealist = new ArrayList<String>();
		String sqlarea = "SELECT DISTINCT qy from "+table+" where qy like '石家庄-%'";
		try {
			ResultSet rsarea = db.select(sqlarea);
			while(rsarea.next())
			{
				arealist.add(rsarea.getString(1));
				xxarealist.add(rsarea.getString(1).split("-")[1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取每种类型在各个区域分布的数量
		List<String> numlist = new ArrayList<String>();
		for(int i=0;i<typelist.size();i++)
		{
			String num ="";
			boolean isfirst = true;
			for(int j=0;j<arealist.size();j++)
			{
				String sqlnum = "select count(*) from "+table+" where type='"+typelist.get(i)+"' and qy='"+arealist.get(j)+"'";
				System.out.println(sqlnum);
				String n ="";
				try {
					ResultSet rs = db.select(sqlnum);
					
					while(rs.next())
					{
						n = rs.getString(1);
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isfirst)
				{
					num	+= n;
					isfirst = false;
				}
				else
				{
					num +=", "+n;
				}
			}
			
			System.out.println(num);
			numlist.add(num);
		}
			
				
		db.colseConn();
		Gson gson = new Gson(); 
		String jsonstr1 = gson.toJson(typelist);
		String jsonstr2 = gson.toJson(count);
		String jsonstr3 = gson.toJson(xxarealist);
		String jsonstr4 = gson.toJson(numlist);
		out.print("{\"type\":"+jsonstr1+",\"count\":"+jsonstr2+",\"area\":"+jsonstr3+",\"num\":"+jsonstr4+"}");
	}
	// 处理 POST 方法请求的方法
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
