package cn.laochou.service;

import cn.laochou.dao.CodeDao;
import cn.laochou.enums.EmailEnums;

public class CodeService {
	
	private CodeDao codeDao = new CodeDao();
	
	public boolean checkEmailAndCode(String email, String code) {
		return codeDao.selectCodeByEmailAndCode(email, code) == true ? true : false;
	}
	
	public EmailEnums insertCode(String email, String code) {
		// ��������Ҫȷ�����ǵ�������û�б�ע�����
		if(codeDao.checkTheEmailIsRegistered(email)) {
			return EmailEnums.REGISTERED;
		}else {
			if(codeDao.checkTheEmailIsOnRecord(email)) {
				codeDao.updateCode(email, code);
			}else {
				codeDao.insertCode(email, code);
			}
			return EmailEnums.USERABLE;
		}
	}


}
