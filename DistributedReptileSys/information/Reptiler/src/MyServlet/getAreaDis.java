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

import Entity.AreaNum;
import Entity.LxNum;
import Entity.ShangPu;
import Util.CalMinandMax;
import Util.DBBean;
import Util.getStr;

import com.google.gson.Gson;

public class getAreaDis extends HttpServlet {
	// 处理 GET 方法请求的方法
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		String table = request.getParameter("table");
		String p = request.getParameter("page");
		System.out.println("p:!!!!!!!"+p);
		
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
		//定义数组存储数据
		String str[] = new String[22];
		//获取数量在栾城区
		String lcq="0";
		String sqllcq = "select count(*) from "+table+" where qy like '栾城%'";
		try {
			ResultSet rslcq = db.select(sqllcq);
			while(rslcq.next())
			{
				lcq = rslcq.getString(1);
				System.out.println("lcq"+lcq);
				str[0] = lcq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在正定县
		String zdx="0";
		String sqlzdx = "select count(*) from "+table+" where qy like '正定%'";
		try {
			ResultSet rszdx = db.select(sqlzdx);
			while(rszdx.next())
			{
				zdx = rszdx.getString(1);
				str[1] = zdx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在无极县
		String wjx="0";
		String sqlwjx = "select count(*) from "+table+" where qy like '无极%'";
		try {
			ResultSet rswjx = db.select(sqlwjx);
			while(rswjx.next())
			{
				wjx = rswjx.getString(1);
				str[2] = wjx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在井陉县
		String jxx="0";
		String sqljxx = "select count(*) from "+table+" where qy like '井陉%'";
		try {
			ResultSet rsjxx = db.select(sqljxx);
			while(rsjxx.next())
			{
				jxx = rsjxx.getString(1);
				str[3] = jxx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在鹿泉区
		String lqq="0";
		String sqllqq = "select count(*) from "+table+" where qy like '鹿泉%'";
		try {
			ResultSet rslqq = db.select(sqllqq);
			while(rslqq.next())
			{
				lqq = rslqq.getString(1);
				str[4] = lqq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在藁城区
		String scq="0";
		String sqlscq = "select count(*) from "+table+" where qy like '藁城%'";
		try {
			ResultSet rsscq = db.select(sqlscq);
			while(rsscq.next())
			{
				scq = rsscq.getString(1);
				str[5] = scq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在裕华区
		String yhq="0";
		String sqlyhq = "select count(*) from "+table+" where qy like '裕华%'";
		try {
			ResultSet rsyhq = db.select(sqlyhq);
			while(rsyhq.next())
			{
				yhq = rsyhq.getString(1);
				str[6] = yhq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在长安区
		String caq="0";
		String sqlcaq = "select count(*) from "+table+" where qy like '桥东%' or qy like '长安%'";
		try {
			ResultSet rscaq = db.select(sqlcaq);
			while(rscaq.next())
			{
				caq = rscaq.getString(1);
				str[7] = caq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在新华区
		String xhq="0";
		String sqlxhq = "select count(*) from "+table+" where qy like '新华%'";
		try {
			ResultSet rsxhq = db.select(sqlxhq);
			while(rsxhq.next())
			{
				xhq = rsxhq.getString(1);
				str[8] = xhq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在桥西区
		String qxq="0";
		String sqlqxq = "select count(*) from "+table+" where qy like '桥西%'";
		try {
			ResultSet rsqxq = db.select(sqlqxq);
			while(rsqxq.next())
			{
				qxq = rsqxq.getString(1);
				str[9] = qxq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在井陉矿区
		String jxkq="0";
		String sqljxkq = "select count(*) from "+table+" where qy like '井陉矿%'";
		try {
			ResultSet rsjxkq = db.select(sqljxkq);
			while(rsjxkq.next())
			{
				jxkq = rsjxkq.getString(1);
				str[10] = jxkq;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在元氏县
		String ysx="0";
		String sqlysx = "select count(*) from "+table+" where qy like '元氏县%'";
		try {
			ResultSet rsysx = db.select(sqlysx);
			while(rsysx.next())
			{
				ysx = rsysx.getString(1);
				str[11] = ysx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在赵县
		String zx="0";
		String sqlzx = "select count(*) from "+table+" where qy like '赵县%'";
		try {
			ResultSet rszx = db.select(sqlzx);
			while(rszx.next())
			{
				zx = rszx.getString(1);
				str[12] = zx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在赞皇县
		String zhx="0";
		String sqlzhx = "select count(*) from "+table+" where qy like '赞皇%'";
		try {
			ResultSet rszhx = db.select(sqlzhx);
			while(rszhx.next())
			{
				zhx = rszhx.getString(1);
				str[13] = zhx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在新乐市
		String xls="0";
		String sqlxls = "select count(*) from "+table+" where qy like '新乐%'";
		try {
			ResultSet rsxls = db.select(sqlxls);
			while(rsxls.next())
			{
				xls = rsxls.getString(1);
				str[14] = xls;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在深泽县
		String szx="0";
		String sqlszx = "select count(*) from "+table+" where qy like '深泽%'";
		try {
			ResultSet rsszx = db.select(sqlszx);
			while(rsszx.next())
			{
				szx = rsszx.getString(1);
				str[15] = szx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在晋州市
		String jzs="0";
		String sqljzs = "select count(*) from "+table+" where qy like '晋州%'";
		try {
			ResultSet rsjzs = db.select(sqljzs);
			while(rsjzs.next())
			{
				jzs = rsjzs.getString(1);
				str[16] = jzs;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在辛集市
		String xjs="0";
		String sqlxjs = "select count(*) from "+table+" where qy like '辛集%'";
		try {
			ResultSet rsxjs = db.select(sqlxjs);
			while(rsxjs.next())
			{
				xjs = rsxjs.getString(1);
				str[17] = xjs;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在行唐县
		String xtx="0";
		String sqlxtx = "select count(*) from "+table+" where qy like '行唐%'";
		try {
			ResultSet rsxtx = db.select(sqlxtx);
			while(rsxtx.next())
			{
				xtx = rsxtx.getString(1);
				str[18] = xtx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在灵寿县
		String lsx="0";
		String sqllsx = "select count(*) from "+table+" where qy like '灵寿%'";
		try {
			ResultSet rslsx = db.select(sqllsx);
			while(rslsx.next())
			{
				lsx = rslsx.getString(1);
				str[19] = lsx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在平山县
		String psx="0";
		String sqlpsx = "select count(*) from "+table+" where qy like '平山%'";
		try {
			ResultSet rspsx = db.select(sqlpsx);
			while(rspsx.next())
			{
				psx = rspsx.getString(1);
				str[20] = psx;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取数量在平山县
		String gyx="0";
		String sqlgyx = "select count(*) from "+table+" where qy like '高邑%'";
		try {
			ResultSet rsgyx = db.select(sqlgyx);
			while(rsgyx.next())
			{
				gyx = rsgyx.getString(1);
				str[21] = gyx;
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//封装AreaNum对象
		AreaNum an = new AreaNum();
		an.setCaq(caq);
		an.setJxkq(jxkq);
		an.setJxx(jxx);
		an.setJzs(jzs);
		an.setLcq(lcq);
		an.setLqq(lqq);
		an.setLsx(lsx);
		an.setPsx(psx);
		an.setQxq(qxq);
		an.setScq(scq);
		an.setSzx(szx);
		an.setWjx(wjx);
		an.setXhq(xhq);
		an.setXjs(xjs);
		an.setXls(xls);
		an.setXtx(xtx);
		an.setYhq(yhq);
		an.setYsx(ysx);
		an.setZdx(zdx);
		an.setZhx(zhx);
		an.setZx(zx);
		an.setGyx(gyx);
		CalMinandMax cmm = new CalMinandMax();
		String s[] = new String[2];
		s= cmm.getMinandMax(str);
		an.setMax(s[0]);
		an.setMin(s[1]);
		
		//获取分类信息
		List<LxNum> lnlist = new ArrayList<LxNum>();
		//获取所有类型
		List<String> lx = new ArrayList<String>();
		String sqllx = "select distinct lx from "+table;
		try {
			ResultSet rslx = db.select(sqllx);
			while(rslx.next())
			{
				try{
					lx.add(rslx.getString(1));
				}catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<lx.size();i++)
		{
			LxNum ln = new LxNum();
			String leixing = lx.get(i);
			if(leixing==null)
			{
				continue;
			}
			String sql = "select count(*) from "+table+" where lx='"+leixing+"'";
			try {
				ResultSet rs = db.select(sql);
				while(rs.next())
				{
					ln.setLx(leixing);
					ln.setNum(rs.getString(1));
					lnlist.add(ln);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//获取价格区域信息
		//获取所有区域
		String sqlarea = "select distinct qy from "+table;
		
		
		//获取商铺表信息
		List<ShangPu> splist = new ArrayList<ShangPu>();
		//分页查询
		//计算总页数
		String sql = "select count(*) from "+table;
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
		String sqlsp = "";
		int page =1;
		if(p.equals("last"))
		{
			page = count/7;
			int n = count%7;
			sqlsp = "select url,lj,mj,zj,sj,lxr,xydj from "+table+" ORDER BY id desc limit "+n+"";
			System.out.println(sqlsp);
		}
		else
		{
			page = Integer.parseInt(p);
			sqlsp = "select url,lj,mj,zj,sj,lxr,xydj from "+table+" limit "+(page-1)*7+",7";
		}
		 
		try {
			ResultSet rssp = db.select(sqlsp);
			while(rssp.next())
			{
				ShangPu sp = new ShangPu();
				sp.setUrl(rssp.getString("url"));
				sp.setLj(rssp.getString("lj"));
				sp.setLxr(rssp.getString("lxr"));
				sp.setMj(rssp.getString("mj"));
				if(rssp.getString("sj")!=null)
				{
					sp.setSj(rssp.getString("sj").split(" ")[0]);
					sp.setZj("");
				}
				if(rssp.getString("zj")!=null)
				{
					sp.setZj(rssp.getString("zj").split(" ")[0]);
					sp.setSj("");
				}
				getStr gs = new getStr();
				sp.setXydj(gs.getNumfromStr(rssp.getString("xydj")));
				sp.setCount(count/7+"");
				sp.setPage(page+"");
				splist.add(sp);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.colseConn();
		Gson gson = new Gson(); 
		String jsonstr1 = gson.toJson(an);
		String jsonstr2 = gson.toJson(lnlist);
		String jsonstr3 = gson.toJson(splist);
		out.println("{\"area\":["+jsonstr1+"],\"leixing\":"+jsonstr2+",\"spxx\":"+jsonstr3+"}");
				
				
		}
	// 处理 POST 方法请求的方法
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
