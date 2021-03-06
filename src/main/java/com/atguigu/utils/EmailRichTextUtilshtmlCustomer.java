package com.atguigu.utils;

import java.math.BigDecimal;
import java.security.Security;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.atguigu.bean.MlPaypalShipAddress;
import com.atguigu.bean.MlbackEmailRichText;
import com.atguigu.bean.MlfrontOrder;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.bean.MlfrontPayInfo;
import com.atguigu.bean.MlfrontUser;
public class EmailRichTextUtilshtmlCustomer {
	
	/**
     * @Method: readyEmail----Fictitious
     * @Description: 准备发邮件的服务器等相关身份资料
     * @Anthor:zsh
     * @param session
     * @return
     * @throws Exception
     */ 
	
	public static void readyEmailPaySuccessCustomer(String getToEmail, String Message,List<MlfrontOrderItem> mlfrontOrderItemList,MlfrontPayInfo mlfrontPayInfoIOne,
			MlfrontOrder mlfrontOrderResOne, String addressMoney,String patSuccessEndLanguage,MlPaypalShipAddress mlPaypalShipAddress)  throws Exception{
		sendNewEmilPayCustomer(getToEmail, Message, mlfrontOrderItemList,mlfrontPayInfoIOne,mlfrontOrderResOne,addressMoney,patSuccessEndLanguage,mlPaypalShipAddress);
	}
	
	public static void readyEmailVerifyCustomer(String getToEmail, String toCustomerVerifyInfoStr,String payinfoPlateNum,String payinfoAddressEmail) {
		sendNewEmilVerifyCustomer(getToEmail, toCustomerVerifyInfoStr,payinfoPlateNum,payinfoAddressEmail);
	}
	
	public static void readyEmailAbandoningPurchaseCustomer(String getToEmail, String toCustomerAbandoningPurchaseStr) {
		sendNewEmilAbandoningPurchaseCustomer(getToEmail,toCustomerAbandoningPurchaseStr);
	}

	/*
	 * Register通知Customer
	 * megalookweb@outlook.com
	 * mingyueqingl@163.com
	 * */
	public static void readyEmailRichTextRegisterCustomerAll(String to, MlbackEmailRichText mlbackEmailRichText,MlfrontUser mlfrontUserafterIn) {
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
            /*final String username = "发送者邮箱用户名";
            final String password = "发送者邮箱密码或者邮箱授权码";*/
            String username = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String password = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.userhighpwd");
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
            String content= getTruecontent(mlbackEmailRichText,mlfrontUserafterIn);
           
            //通过会话,得到一个邮件,用于发送
            MimeMessage  msg = new MimeMessage(session);
            //设置发件人
//          msg.setFrom(new InternetAddress("发件人邮箱"));
//          msg.setFrom(new InternetAddress("service@megalook.com"));//megalook
//          msg.setFrom(new InternetAddress("sales@megalook.com"));//huashuohair
            String sendEmail = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String MegaLook = (String)PropertiesUtil.getProperty("megalook.properties", "MegaLook");
            msg.setFrom(new InternetAddress(MegaLook+"Hair"+" <"+sendEmail+">"));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Welcome to Register "+MegaLook+".");
            
            Multipart mp = new MimeMultipart("related"); 
            BodyPart bodyPart = new MimeBodyPart(); 
            bodyPart.setDataHandler(new DataHandler(content,"text/html;charset=UTF-8"));
            mp.addBodyPart(bodyPart); 
            msg.setContent(mp);// 设置邮件内容对象 
            
            //设置发送的日期
            msg.setSentDate(new Date());
            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            //System.out.println("给"+to+"客户,发送邮件完毕,"+"邮件内容为"+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static String getTruecontent(MlbackEmailRichText mlbackEmailRichText, MlfrontUser mlfrontUserafterIn) {
		
		String templete = mlbackEmailRichText.getEmailrichtextTemplate();
		String resultContent = mlbackEmailRichText.getEmailrichtextTemplate();
		
		if(templete.contains("{accountValue}")){
			templete = templete.replace("{accountValue}", mlfrontUserafterIn.getUserEmail());
		}
		if(templete.contains("{passwordValue")){
			templete = templete.replace("{passwordValue}", mlfrontUserafterIn.getUserPassword());
		}
		resultContent = templete;
		
		return resultContent;
	}
	
	/*
	 * Pay通知Customer
	 * megalookweb@outlook.com
	 * mingyueqingl@163.com
	 * */
	public static void sendNewEmilPayCustomer(String to, String message,List<MlfrontOrderItem> mlfrontOrderItemList,MlfrontPayInfo mlfrontPayInfoIOne, MlfrontOrder mlfrontOrderResOne, String addressMoney,String patSuccessEndLanguage,MlPaypalShipAddress mlPaypalShipAddressInto) {
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
            /*final String username = "发送者邮箱用户名";
            final String password = "发送者邮箱密码或者邮箱授权码";*/
            String username = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String password = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.userhighpwd");
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

          //联系邮箱：联系人：电话：地址
            String addressDetail ="";
            String addressEmail =mlPaypalShipAddressInto.getShippingaddressEmail();
            String addressUname = mlPaypalShipAddressInto.getShippingaddressRecipientName();
            String addressTel =mlPaypalShipAddressInto.getShippingaddressTelNumber();
            String addressAll = mlPaypalShipAddressInto.getShippingaddressLine1()+","+mlPaypalShipAddressInto.getShippingaddressCity()+
            		","+mlPaypalShipAddressInto.getShippingaddressStateProvinceName()+","+mlPaypalShipAddressInto.getShippingaddressCountryName()+","+mlPaypalShipAddressInto.getShippingaddressPostalCode();
            
            addressDetail=addressDetail+"<b>Receiving address</b>:"+addressAll+"<br>";
            addressDetail=addressDetail+"<b>consignee</b>:"+addressUname+"<br>";
            addressDetail=addressDetail+"<b>Recipient's phone number</b>:"+addressTel+"<br>";
            addressDetail=addressDetail+"<b>Notification email</b>:"+addressEmail+"<br>";
            
            String pdetail ="";
            for(MlfrontOrderItem mlfrontOrderItem :mlfrontOrderItemList){
            	String Pname = mlfrontOrderItem.getOrderitemPname();
            	Integer Pnumber = mlfrontOrderItem.getOrderitemPskuNumber();
            	String Psku = mlfrontOrderItem.getOrderitemPskuName();
            	String pAllmoney = mlfrontOrderItem.getOrderitemPskuReamoney();
            	
            	pdetail=pdetail+Pnumber+" x "+Pname+" ( "+Psku+" )&nbsp;&nbsp;&nbsp;&nbsp;"+pAllmoney+"<br><br>";
            	
            }
            
            String SubTotal = getSubTotal(mlfrontPayInfoIOne.getPayinfoMoney(),addressMoney,mlfrontOrderResOne.getOrderCouponPrice());
            
            String CouponCodeStr ="";
            
            if(mlfrontOrderResOne.getOrderCouponCode()==null){
            	CouponCodeStr ="";
            }else if("".equals(mlfrontOrderResOne.getOrderCouponCode())){
            	CouponCodeStr ="";
            }else{
            	CouponCodeStr = "Coupon"+" ( "+mlfrontOrderResOne.getOrderCouponCode()+" ) : -$"+mlfrontOrderResOne.getOrderCouponPrice()+" <br>";
            }
            
            //读取配置文件
    		String team = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.team");
    		String email = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.email");
    		String whatsapp = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.whatsapp");
    		String Telephone = (String) PropertiesUtil.getProperty("megalook.properties", "delvery.Telephone");
    		String MegaLook = (String)PropertiesUtil.getProperty("megalook.properties", "MegaLook");
            String content="Hi gorgeous girl.Here is "+MegaLook+" Hair, We have received your order and confirmed your payment.<br>  "+
            "Order ID :<b>"+mlfrontPayInfoIOne.getPayinfoPlatenum()+"</b> <br>"+
            "Date Added :"+mlfrontOrderItemList.get(0).getOrderitemMotifytime()+" <br>"+
            "Order Status : Payment completed, order processing... <br><br>"+
	  		patSuccessEndLanguage+"<br><br>"+
            "<b>Notice!!!</b><br>"
            + "We will deliver the goods to the following address, If there do have any discrepancy, please reply this email timely and keep us update.<br>"+addressDetail+"<br>"+
            "<b>Products</b>:<br> "+
            pdetail+
            "<b>payment details</b>:<br> "+
            "products-Total: $"+SubTotal+" <br>"+
            CouponCodeStr +
            "Free Shipping: +$"+addressMoney+"<br>"+
            "Sub-Total: $"+mlfrontPayInfoIOne.getPayinfoMoney()+" <br><br>"+
            "Best Regards,<br>"+
            "------------------------------------------<br>"+
	  		team+"<br>"+
	  		"Email:"+email+"<br>"+
	  		"Whatsapp:"+whatsapp+"<br>"+
	  		"Telephone/SMS:"+Telephone+"<br>";
            
            //通过会话,得到一个邮件,用于发送
            MimeMessage msg = new MimeMessage(session);
            //设置发件人
//          msg.setFrom(new InternetAddress("发件人邮箱"));
//          msg.setFrom(new InternetAddress("service@megalook.com"));//megalook
//          msg.setFrom(new InternetAddress("sales@megalook.com"));//huashuohair
            String sendEmail = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            msg.setFrom(new InternetAddress(MegaLook+"Hair"+" <"+sendEmail+">"));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(addressEmail, false));
            String trackingEmails1 = (String)PropertiesUtil.getProperty("megalook.properties", "trackingEmails1");
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(trackingEmails1, false));
            //msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("mingyueqingl@163.com", false));
            msg.setSubject("Order Confirmation From "+MegaLook+" Hair.");
            
            Multipart mp = new MimeMultipart("related");
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(content,"text/html;charset=UTF-8"));
            mp.addBodyPart(bodyPart);
         // 设置邮件内容对象 
            msg.setContent(mp);
            //设置发送的日期
            msg.setSentDate(new Date());
            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            System.out.println("给"+to+"客户,发送邮件完毕,"+"邮件内容为"+content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static String getSubTotal(BigDecimal payinfoMoney, String addressMoney, BigDecimal orderCouponPrice) {
		//总钱数=物价+运费-优惠
		//原价=总价+优惠-运费
		BigDecimal SubTotal = payinfoMoney.add(orderCouponPrice);
		BigDecimal addressMoneyBig = new BigDecimal(addressMoney);
		SubTotal=SubTotal.subtract(addressMoneyBig);
		DecimalFormat df1 = new DecimalFormat("0.00");
		String SubTotalStr = df1.format(SubTotal);
		return SubTotalStr;
	}
	
	
	/*
	 * Verify通知Customer
	 * megalookweb@outlook.com
	 * mingyueqingl@163.com
	 * */
	private static void sendNewEmilVerifyCustomer(String to, String toCustomerVerifyInfoStr,String payinfoPlateNum,String payinfoAddressEmail) {
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
            /*final String username = "发送者邮箱用户名";
            final String password = "发送者邮箱密码或者邮箱授权码";*/
            String username = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String password = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.userhighpwd");
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
            String content=toCustomerVerifyInfoStr;
            //通过会话,得到一个邮件,用于发送
            MimeMessage msg = new MimeMessage(session);
            //设置发件人
//          msg.setFrom(new InternetAddress("发件人邮箱"));
//          msg.setFrom(new InternetAddress("service@megalook.com"));//megalook
//          msg.setFrom(new InternetAddress("sales@megalook.com"));//huashuohair
            String sendEmail = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String MegaLook = (String)PropertiesUtil.getProperty("megalook.properties", "MegaLook");
            msg.setFrom(new InternetAddress(MegaLook+"Hair"+" <"+sendEmail+">"));
            //msg.setFrom(new InternetAddress("MegaLookHair"+" <"+sendEmail+">"));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(payinfoAddressEmail, false));
            String trackingEmails1 = (String)PropertiesUtil.getProperty("megalook.properties", "trackingEmails1");
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(trackingEmails1, false));
            //msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("mingyueqingl@163.com", false));
            msg.setSubject("Order preparing from "+MegaLook+" hair.");
            
            Multipart mp = new MimeMultipart("related"); 
            BodyPart bodyPart = new MimeBodyPart(); 
            bodyPart.setDataHandler(new DataHandler(content,"text/html;charset=UTF-8"));
            mp.addBodyPart(bodyPart); 
            msg.setContent(mp);// 设置邮件内容对象 
            
            //设置邮件消息
            //msg.setText(message);
            //设置发送的日期
            msg.setSentDate(new Date());
            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            System.out.println("给"+to+"客户,发送邮件完毕,"+"邮件内容为"+content);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private static void sendNewEmilAbandoningPurchaseCustomer(String to, String toCustomerAbandoningPurchaseStr) {
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
            /*final String username = "发送者邮箱用户名";
            final String password = "发送者邮箱密码或者邮箱授权码";*/
            String username = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String password = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.userhighpwd");
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
            String content=toCustomerAbandoningPurchaseStr;
            //通过会话,得到一个邮件,用于发送
            MimeMessage msg = new MimeMessage(session);
            //设置发件人
//           msg.setFrom(new InternetAddress("发件人邮箱"));
//          msg.setFrom(new InternetAddress("service@megalook.com"));//megalook
//          msg.setFrom(new InternetAddress("sales@megalook.com"));//huashuohair
            String sendEmail = (String) PropertiesUtil.getProperty("megalook.properties", "sendNewEmil.username");
            String MegaLook = (String)PropertiesUtil.getProperty("megalook.properties", "MegaLook");
            msg.setFrom(new InternetAddress(MegaLook+"Hair"+" <"+sendEmail+">"));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Customer help from "+MegaLook+" hair.");
            
            Multipart mp = new MimeMultipart("related"); 
            BodyPart bodyPart = new MimeBodyPart(); 
            bodyPart.setDataHandler(new DataHandler(content,"text/html;charset=UTF-8"));
            mp.addBodyPart(bodyPart);
            // 设置邮件内容对象 
            msg.setContent(mp);
            
            //设置发送的日期
            msg.setSentDate(new Date());
            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            System.out.println("给"+to+"客户,发送邮件完毕,"+"邮件内容为"+content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}