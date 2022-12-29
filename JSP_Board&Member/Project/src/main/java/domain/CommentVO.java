package domain;

public class CommentVO {
	
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String reg_at;

	public CommentVO() { }
	
	// post(bno, writer, content)
	public CommentVO(int bno, String writer, String content) {
		this.bno = bno;
		this.writer = writer;
		this.content = content;
	}

	// modify
	public CommentVO(int cno, String content) {
		this.cno = cno;
		this.content = content;
	}
	
	// list(bno, cno, writer, content, reg_at)
	public CommentVO(int cno, int bno, String writer, String content, String reg_at) {
		this.cno = cno;
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.reg_at = reg_at;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}
	
	
}
