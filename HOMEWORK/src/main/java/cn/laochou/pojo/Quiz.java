package cn.laochou.pojo;

public class Quiz {
	
	private Integer id;
	private String author;
	private String title;
	private String content;
	private String image;
	private String pubTime;
	private String category;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public Quiz setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getAuthor() {
		return author;
	}
	public Quiz setAuthor(String author) {
		this.author = author;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Quiz setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Quiz setContent(String content) {
		this.content = content;
		return this;
	}
	public String getImage() {
		return image;
	}
	public Quiz setImage(String image) {
		this.image = image;
		return this;
	}
	public String getPubTime() {
		return pubTime;
	}
	public Quiz setPubTime(String pubTime) {
		this.pubTime = pubTime;
		return this;
	}
	public String getCategory() {
		return category;
	}
	public Quiz setCategory(String category) {
		this.category = category;
		return this;
	}
	public Integer getUserId() {
		return userId;
	}
	public Quiz setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content + ", image="
				+ image + ", pubTime=" + pubTime + ", category=" + category + ", userId=" + userId
				+ "]";
	}
	
}
