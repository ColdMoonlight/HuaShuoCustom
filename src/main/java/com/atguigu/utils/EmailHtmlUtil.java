package com.atguigu.utils;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class EmailHtmlUtil {
	
	public static void main(String[] args) throws Exception{  
		
		 
		// 设置要发送的文本,html格式，所以可以内嵌css
//        String content = "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-family:'黑体',Helvetica,Arial,sans-serif;font-size:14px;background: lightblue;border-radius: 10px;\" width=\"100%\"><tbody><tr>\n" +
//                "\t\t<td style=\"font-family:Helvetica,Arial,sans-serif;font-size:14px;text-align: center;color: white;\">\n" +
//                "\t\t<h3 style=\"margin: 15px 0px;\">页面访问升级中...</h3>\n" +
//                "\t\t<a style=\"display: inline-block;text-decoration:none;width: 150px;height: 40px;background: steelblue;line-height: 40px;color: white;border-radius: 10px;margin-bottom: 20px;\" \n" +
//                "\t\thref=\"xxx\">www.se783.co</a>\n" +
//                "\t\t<hr style=\"color:steelblue;border: 2px solid aliceblue;margin: 5px 20px;border-radius: 10px;\">\n" +
//                "\t\t<h5>温馨提示<br>海外网络永久域名<br>www.se783.co<br>记住防止迷路</h5>\n" +
//                "\t\t<table width=\"100%\" border=\"0\" cellpadding=\"5\" cellspacing=\"0\"><tbody><tr>\n" +
//                "\t\t<td style=\"text-align: right;\">\n" +
//                "\t\t<p style=\"font-size:14px;line-height:24px;font-family: '黑体', sans-serif;padding:0px 10px;margin:8px\">尊敬的访问者：<b>FanX</b></p>\n" +
//                "\t\t<p style=\"color:white;font-size:14px;line-height:24px;font-family:'黑体',Helvetica,Arial,sans-serif;padding:0px 10px;margin:8px\">您的访问次数程度：<span style=\"color:#af5c61;\">99颗星</span> (最高5颗星)</p>\n" +
//                "\t\t</td></tr></tbody></table></td></tr></tbody></table>";
		
		String content = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.content");
		
		sendHtmlUnCheckoutEmil("mingyueqingl@163.com", content) ;
        
    }  
	
	public static void sendHtmlUnCheckoutEmil(String to, String contentS) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            //设置邮件会话参数
            Properties props = new Properties();
            //邮箱的发送服务器地址
            props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            //邮箱发送服务器端口,这里设置为465端口
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            /*final String username = "发送者邮箱用户名";final String password = "发送者邮箱密码或者邮箱授权码";*/
            String username = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String password = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.userhighpwd");
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            String content= contentS;
            //通过会话,得到一个邮件,用于发送
            MimeMessage  msg = new MimeMessage(session);
            //设置发件人
//          msg.setFrom(new InternetAddress("发件人邮箱"));
            String sendEmail = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            msg.setFrom(new InternetAddress("MegaLookTeam"+" <"+sendEmail+">"));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse("licindy770@gmail.com", false));
            msg.setSubject("Baby?You Really Do not want Me?Final Chance lol");
            
            Multipart mp = new MimeMultipart("related"); 
            BodyPart bodyPart = new MimeBodyPart(); 
            bodyPart.setDataHandler(new DataHandler(content,"text/html;charset=UTF-8"));
            mp.addBodyPart(bodyPart); 
            msg.setContent(mp);// 设置邮件内容对象 
            //设置发送的日期
            msg.setSentDate(new Date());
            
            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            //System.out.println("给 "+mlfrontUserafterIn.getUserId()+" 客户,发送邮件完毕,"+"邮件内容为"+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
