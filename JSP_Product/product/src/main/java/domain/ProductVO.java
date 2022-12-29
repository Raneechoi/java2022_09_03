package domain;

public class ProductVO {
	// pno, pname, price, regdate, madeby

		private int pno; //자동증가 
		private String pname;
		private int price;
		private String regdate; //default 오늘날짜 자동 저장
		private String madeby;

		

		public ProductVO() {}

		//상품등록

		public ProductVO(String pname, int price, String madeby) {
			this.pname = pname;
			this.price = price;
			this.madeby = madeby;

		}

		//상품리스트  (대략리스트)

		public ProductVO(int pno, String pname, String regdate) {
			this.pno = pno;
			this.pname = pname;
			this.regdate = regdate;
		}

		//상품수정 : pno, pname, price, madeby

		public ProductVO(int pno, String pname, int price, String madeby) {
			this(pname, price, madeby);  //생성자 호출
			this.pno = pno;
		}

		//상픔상세 (전체데이터)

		public ProductVO(int pno, String pname, int price, String regdate, String madeby) {
			this(pno, pname, price, madeby);
			this.regdate = regdate;
		}

		public int getPno() {
			return pno;
		}

		public void setPno(int pno) {
			this.pno = pno;
		}

		public String getPname() {
			return pname;
		}

		public void setPname(String pname) {
			this.pname = pname;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getRegdate() {
			return regdate;
		}

		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}

		public String getMadeby() {
			return madeby;
		}

		public void setMadeby(String madeby) {
			this.madeby = madeby;
		}

		@Override
		public String toString() {
			return "Product [pno=" + pno + ", pname=" + pname + ", price=" + price + ", regdate=" + regdate + ", madeby="
					+ madeby + "]";

		}

}
