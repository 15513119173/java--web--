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
    	    msg.setSubject("�������߿���ϵͳ");
    	    msg.setSentDate(new Date());
    	    msg.setText("��ã����ѳɹ�ע���������߿���ϵͳ���밴ʱ����γ̿��ԣ��������⣬�뼰ʱ���̨������Ա��ϵ��");
    	    msg.saveChanges();
    	    transport=sessions.getTransport("smtp");
    	    transport.connect("smtp.163.com","swwacln@163.com","529913ln");
    	    transport.sendMessage(msg, msg.getAllRecipients());
    	    transport.close();
     }
}
