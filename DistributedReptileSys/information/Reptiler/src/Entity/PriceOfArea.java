package Entity;

public class PriceOfArea {
	
	private String highprice;
	private String lowprice;
	private String avgprice;
	private String area;
	
	public PriceOfArea(String highprice, String lowprice, String avgprice,
			String area) {
		super();
		this.highprice = highprice;
		this.lowprice = lowprice;
		this.avgprice = avgprice;
		this.area = area;
	}

	public PriceOfArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHighprice() {
		return highprice;
	}

	public void setHighprice(String highprice) {
		this.highprice = highprice;
	}

	public String getLowprice() {
		return lowprice;
	}

	public void setLowprice(String lowprice) {
		this.lowprice = lowprice;
	}

	public String getAvgprice() {
		return avgprice;
	}

	public void setAvgprice(String avgprice) {
		this.avgprice = avgprice;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
}
