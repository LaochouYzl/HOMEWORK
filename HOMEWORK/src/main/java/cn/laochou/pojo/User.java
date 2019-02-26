package cn.laochou.pojo;

public class User {
	private Integer id;
	private String userName;
	private String password;
	private String email;
	private Integer type;
	
	
	public Integer getId() {
		return id;
	}
	public User setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public User setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
	
	public Integer getType() {
		return type;
	}
	public User setType(Integer type) {
		this.type = type;
		return this;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + ", type="
				+ type + "]";
	}
	
	


}
