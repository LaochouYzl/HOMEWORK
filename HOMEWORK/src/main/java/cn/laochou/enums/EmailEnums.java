package cn.laochou.enums;

public enum EmailEnums {
	USERABLE("1","该邮箱可用"),
	REGISTERED("0","该邮箱已经被注册了"),
	UNKNOWN("-1","未知邮箱");
	
	private String id;
	private String desc;
	
	private EmailEnums(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public String getDesc() {
		return this.desc;
	}

	public String getId() {
		return id;
	}
	
	

}
