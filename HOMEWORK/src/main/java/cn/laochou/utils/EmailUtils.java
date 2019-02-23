package cn.laochou.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.laochou.dao.CodeDao;
import cn.laochou.enums.EmailEnums;
import cn.laochou.service.CodeService;

public class EmailUtils {
	
	private static CodeService codeService = new CodeService();
	
	private static CodeDao codeDao = new CodeDao();

    private static final String SMTPSERVER = "smtp.163.com";
    private static final String SMTPPORT = "465";
    private static final String ACCOUT = "13367925359@163.com";
    private static final String PWD = "13367925359wasd";

    public static EmailEnums sendEmail(String email){
        // �����ʼ�����
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // ʹ�õ�Э�飨JavaMail�淶Ҫ��
        props.setProperty("mail.smtp.host", SMTPSERVER); // �����˵������ SMTP ��������ַ
        props.setProperty("mail.smtp.port", SMTPPORT); 
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // ��Ҫ������֤
        props.setProperty("mail.smtp.ssl.enable", "true");// ����ssl
        String code = createCode();
        // �����ʼ����ô����Ự��ע��session�𵼴��
        Session session = Session.getDefaultInstance(props);
        // ����debugģʽ�����Կ���������ϸ��������־
        session.setDebug(false);
        //�����ʼ�
        MimeMessage message;
        EmailEnums result = EmailEnums.UNKNOWN;
		try {
			message = createEmail(session, email, code);
			//��ȡ����ͨ��
	        Transport transport = session.getTransport();
	        transport.connect(SMTPSERVER,ACCOUT, PWD);
	        //���ӣ��������ʼ�
	        if(!codeDao.checkTheEmailIsRegistered(email)) {
	        	transport.sendMessage(message, message.getAllRecipients());
	        	result = codeService.insertCode(email, code);
	        }else {
	        	result = EmailEnums.REGISTERED;
	        }
	        transport.close();
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return result;
    }


    public static MimeMessage createEmail(Session session, String email, String code) throws Exception {
    	String title = "LAOCHOU-LINE-GENERATION";
        // ���ݻỰ�����ʼ�
        MimeMessage msg = new MimeMessage(session);
        // address�ʼ���ַ, personal�ʼ��ǳ�, charset���뷽ʽ
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                "LAOCHOU", "utf-8");
        // ���÷����ʼ���
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(
                email, "LAOCHOU", "utf-8");
        // �����ʼ����շ�
        msg.setRecipient(RecipientType.TO, receiveAddress);
        // �����ʼ�����
        msg.setSubject(title, "utf-8");
        msg.setText("����, ������֤��Ϊ"+code+"����5��������Ч, ��ʱ����, �뾡��ע��");
        // ������ʾ�ķ���ʱ��
        msg.setSentDate(new Date());
        // ��������
        msg.saveChanges();
        return msg;
    }
    
    
    
    public static String createCode() {
    	int newNum = (int)((Math.random()*9+1)*100000);
        return String.valueOf(newNum);
    }
}