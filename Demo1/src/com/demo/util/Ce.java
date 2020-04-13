package com.demo.util;
import java.util.*;
import javax.activation.*;
import javax.mail.internet.*;
import javax.mail.*;
public class Ce {
     public static void getdata(String mail)throws Exception{
    	 Properties p=new Properties();
    	    Session sessions;
    	    Transport transport;
    	    sessions=Session.getInstance(p,null);
    	    p.put("mail.smtp.host", "smtp.163.com");
    	    sessions.setDebug(true);
    	    Message msg=new MimeMessage(sessions);
    	    msg.setFrom(new InternetAddress("swwacln@163.com"));
    	    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
    	    msg.setSubject("网络在线考试系统");
    	    msg.setSentDate(new Date());
    	    msg.setText("你好，你已成功注册网络在线考试系统，请按时参与课程考试，如有问题，请及时与后台管理人员联系。");
    	    msg.saveChanges();
    	    transport=sessions.getTransport("smtp");
    	    transport.connect("smtp.163.com","swwacln@163.com","529913ln");
    	    transport.sendMessage(msg, msg.getAllRecipients());
    	    transport.close();
     }
}
