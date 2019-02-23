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
        // 创建邮件配置
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", SMTPSERVER); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", SMTPPORT); 
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
        String code = createCode();
        // 根据邮件配置创建会话，注意session别导错包
        Session session = Session.getDefaultInstance(props);
        // 开启debug模式，可以看到更多详细的输入日志
        session.setDebug(false);
        //创建邮件
        MimeMessage message;
        EmailEnums result = EmailEnums.UNKNOWN;
		try {
			message = createEmail(session, email, code);
			//获取传输通道
	        Transport transport = session.getTransport();
	        transport.connect(SMTPSERVER,ACCOUT, PWD);
	        //连接，并发送邮件
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
        // 根据会话创建邮件
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                "LAOCHOU", "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(
                email, "LAOCHOU", "utf-8");
        // 设置邮件接收方
        msg.setRecipient(RecipientType.TO, receiveAddress);
        // 设置邮件标题
        msg.setSubject(title, "utf-8");
        msg.setText("您好, 您的验证码为"+code+"将在5分钟内有效, 过时不候, 请尽快注册");
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        return msg;
    }
    
    
    
    public static String createCode() {
    	int newNum = (int)((Math.random()*9+1)*100000);
        return String.valueOf(newNum);
    }
}