package Entity;

public class ShangPu {
	

	private String url;			//网址
	private String lj;			//临近(详细地址)
	private String mj;			//面积
	private String zj;			//租金
	private String sj;			//售价
	private String lxr;			//联系人
	private String xydj;		//信用等级
	private String page;		//当前页数
	private String count;		//总页数


	
	public ShangPu(String url, String lj,String mj,String zj, String sj,String lxr, String xydj,String page,String count) {
		super();
		this.url = url;
		this.lj = lj;
		this.mj = mj;
		this.zj = zj;
		this.sj = sj;
		this.lxr = lxr;
		this.xydj = xydj;
		this.page = page;
		this.count = count;
	}

	public ShangPu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLj() {
		return lj;
	}

	public void setLj(String lj) {
		this.lj = lj;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getZj() {
		return zj;
	}

	public void setZj(String zj) {
		this.zj = zj;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getXydj() {
		return xydj;
	}

	public void setXydj(String xydj) {
		this.xydj = xydj;
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
