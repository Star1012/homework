package Entity;

public class Goods {

	private String type;		//商品类型
	private String name;
	private String url;			//链接地址
	private String llcs;		//浏览次数
	private String xmrs;		//想买人数
	private String xj;			//价格
	private String qy;			//区域
	private String lxr;			//联系人
	private String page;		//当前页
	private String count;		//总页数
	
	public Goods(String type,String name, String url, String llcs, String xmrs, String xj,
			String qy, String lxr, String page, String count) {
		super();
		this.type = type;
		this.name = name;
		this.url = url;
		this.llcs = llcs;
		this.xmrs = xmrs;
		this.xj = xj;
		this.qy = qy;
		this.lxr = lxr;
		this.page = page;
		this.count = count;
	}

	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLlcs() {
		return llcs;
	}

	public void setLlcs(String llcs) {
		this.llcs = llcs;
	}

	public String getXmrs() {
		return xmrs;
	}

	public void setXmrs(String xmrs) {
		this.xmrs = xmrs;
	}

	public String getXj() {
		return xj;
	}

	public void setXj(String xj) {
		this.xj = xj;
	}

	public String getQy() {
		return qy;
	}

	public void setQy(String qy) {
		this.qy = qy;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
