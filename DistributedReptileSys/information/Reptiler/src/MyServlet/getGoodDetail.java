package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Entity.Goods;

import Util.DBBean;
import Util.getStr;

import com.google.gson.Gson;

public class getGoodDetail extends HttpServlet {
	// 处理 GET 方法请求的方法
	public void doGet(HttpServletRequest request, HttpServletResponse regoodonse) throws ServletException, IOException
	{
		// 设置响应内容类型
		regoodonse.setContentType("text/html;charset=UTF-8");
		String table = request.getParameter("table");
		String type= request.getParameter("type");
		type = java.net.URLDecoder.decode(type,"UTF-8");
		System.out.println(type);
		String p = request.getParameter("page");
		
		PrintWriter out = regoodonse.getWriter();
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
	
		
		//获取价格区域信息
		
		
		//获取商铺表信息
		List<Goods> goodlist = new ArrayList<Goods>();
		//分页查询
		//计算总页数
		String sql = "select count(*) from "+table+" where type="+type;
		System.out.println(sql);
		int count=1;
		try {
			ResultSet rs = db.select(sql);
			while(rs.next())
			{
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sqlgood = "";
		int page =1;
		if(p.equals("last"))
		{
			page = count/7;
			int n = count%7;
			sqlgood = "select * from "+table+" where type="+type+" ORDER BY id desc limit "+n+"";
			System.out.println(sqlgood);
		}
		else
		{
			page = Integer.parseInt(p);
			sqlgood = "select * from "+table+" where type="+type+" limit "+(page-1)*7+",7";
		}
		 
		try {
			ResultSet rsgood = db.select(sqlgood);
			while(rsgood.next())
			{
				Goods good = new Goods();
				getStr gs = new getStr();
				good.setUrl(rsgood.getString("url"));
				good.setName(rsgood.getString("name").replaceAll(" ",""));
				good.setLlcs(gs.getNumfromStr(rsgood.getString("llcs")));
				good.setLxr(rsgood.getString("lxr"));
				good.setQy(rsgood.getString("qy"));
				good.setType(rsgood.getString("type"));
				good.setXj(rsgood.getString("xj"));
				good.setXmrs(gs.getNumfromStr(rsgood.getString("xmrs")));
				good.setCount(count+"");
				good.setPage(page+"");
				goodlist.add(good);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.colseConn();
		Gson gson = new Gson(); 
		String jsonstr1 = gson.toJson(goodlist);
		out.println("{\"detail\":"+jsonstr1+"}");
				
				
		}
	// 处理 POST 方法请求的方法
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
