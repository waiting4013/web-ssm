package test;

import redis.clients.jedis.Jedis;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class RedisTest {


    public  void redis(String...  qw){
        Jedis jedis=new Jedis("121.12.12.12");
        jedis.auth("redis");
        jedis.set("a","asd");
        jedis.lpush("list","qwqw");
        jedis.lpush("list","qwee");
        jedis.rpush("sa","1212");



    }
    public void ss(){

        redis("qw","qeq","qeq","12");
    }
    public static boolean sslSend(MessageInfo msg1, EmailAccount emailAccount)
            throws AddressException, MessagingException, IOException {
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", emailAccount.getPlace());
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");

        final String username = emailAccount.getUsername();
        final String password = emailAccount.getPassword();
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }});
        Message msg = new MimeMessage(session);

        // 设置发件人和收件人
        msg.setFrom(new InternetAddress(emailAccount.getUsername()));
        List<String> tos = msg1.getTo();
        Address to[] = new InternetAddress[tos.size()];
        for(int i=0;i<tos.size();i++){
            to[i] = new InternetAddress(tos.get(i));
        }
        // 多个收件人地址
        msg.setRecipients(Message.RecipientType.TO, to);
        msg.setSubject(msg1.getSubject()); // 标题
        msg.setText(msg1.getMsg());// 内容
        msg.setSentDate(new Date());
        Transport.send(msg);
        System.out.println("EmailUtil ssl协议邮件发送打印" +msg.toString());
        return true;
    }

    public static void main(String[] args) {
        String  data = "asdad,mghjt";

        String substring = data.substring(data.length() - 4);

        System.out.println(substring);

        String[] split = data.split(",");
        if (split.length==1) {

        }
        List<String> list = Arrays.asList(split);
        System.out.println(list);
        System.out.println(list.size());

    }
}
