package com.frank.mswl.mini.email;

import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * 报告发送邮件.
 *
 * @author 张孝党 2020/01/12.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/12. 张孝党 创建.
 */
@Slf4j
public class GramtuMailUtil {

    private static final String myEmailAccount = "turnin@gramtu.com";
    private static final String myEmailPassword = "Jianying850225";

    public static boolean sendMail(String subject, String receiveMail, String attach) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
            props.setProperty("mail.smtp.auth", "true");
            String smtpPort = "465";
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            // 创建一封邮件
            MimeMessage message = createMimeMessage(session, myEmailAccount, subject, receiveMail, attach);

            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (Exception ex) {
            log.error("发送邮件时异常：\n{}", ex.getMessage());
            return false;
        }
    }

    private static MimeMessage createMimeMessage(Session session, String sendMail, String subject, String receiveMail, String attach) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, subject, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "微信昵称", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        //message.setContent("XXXXXXXXXXXX", "text/html;charset=UTF-8");

        //MimeBodyPart text = new MimeBodyPart();
        //text.setContent("XXXXXXXXXXXX", "text/html;charset=UTF-8");

        MimeBodyPart attachment = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(attach));
        attachment.setDataHandler(dh);
        attachment.setFileName(MimeUtility.encodeText(dh.getName()));

        MimeMultipart mm = new MimeMultipart();
        //mm.addBodyPart(text);
        mm.addBodyPart(attachment);
        mm.setSubType("mixed");

        message.setContent(mm);

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
