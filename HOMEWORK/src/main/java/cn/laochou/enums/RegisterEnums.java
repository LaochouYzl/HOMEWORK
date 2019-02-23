package cn.laochou.enums;

public enum RegisterEnums {
	
	SUCCESS("1", "ע��ɹ�"),
	CODEERROR("2", "��֤�����"),
	PASSWORDERROR("3", "���벻ƥ��"),
	EMAILISREGISTERED("4", "�����Ѿ���ע��");
	
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
