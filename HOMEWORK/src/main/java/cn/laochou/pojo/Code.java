package cn.laochou.pojo;

public class Code {

	private String email;
	private String code;
	public String getEmail() {
		return email;
	}
	public Code setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getCode() {
		return code;
	}
	public Code setCode(String code) {
		this.code = code;
		return this;
	}
	@Override
	public String toString() {
		return "Code [email=" + email + ", code=" + code + "]";
	}
	
	
}
