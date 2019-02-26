package cn.laochou.pojo;

public class Comment {
	
	private Integer id;
	private String userName;
	private String content;
	private String pubTime;
	public Integer getId() {
		return id;
	}
	public Comment setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public Comment setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Comment setContent(String content) {
		this.content = content;
		return this;
	}
	public String getPubTime() {
		return pubTime;
	}
	public Comment setPubTime(String pubTime) {
		this.pubTime = pubTime;
		return this;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", userName=" + userName + ", content=" + content + ", pubTime=" + pubTime + "]";
	}
	
	
	

}
