package cn.laochou.enums;

public enum RegisterEnums {
	
	SUCCESS("1", "注册成功"),
	CODEERROR("2", "验证码错误"),
	PASSWORDERROR("3", "密码不匹配"),
	EMAILISREGISTERED("4", "邮箱已经被注册");
	
	private String id;
	private String desc;
	
	private RegisterEnums(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
	
	

}
