package cn.laochou.enums;

public enum EmailEnums {
	USERABLE("1","���������"),
	REGISTERED("0","�������Ѿ���ע����"),
	UNKNOWN("-1","δ֪����");
	
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
