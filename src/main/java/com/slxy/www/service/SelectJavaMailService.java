package com.slxy.www.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhengql
 * @description
 * @className SelectJavaMailService
 * @create 2018年04月16日  9:26
 */
@Service
public class SelectJavaMailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本的简单邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            sender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

    /**
     * 发送html格式的邮件
     * @param to
     * @param name
     * @param passWord 密码，或者是题目名称
     */
    public void sendHtmlMail(String to, String name, String passWord,String subject){

        String content ;

        if (subject.equals("密码重置")){
            content = getString(name, passWord);
        }else if (subject.equals("选题成功")){
            content = getString3(name,passWord);
        }else if (subject.equals("选题失败")){
            content = getString4(name,passWord);
        }else if (subject.equals("通知教师审核")){
            content = getString5(name,passWord);
        }else if (subject.equals("题目审核失败")){
            content = getString6(name,passWord);
        }else {//发送验证码
            content = getString2(name, passWord);
        }
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            sender.send(message);
            logger.info("html邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 教师题目审核失败，驳回
     * @param name
     * @param passWord
     * @return
     */
    private String getString6(String name, String passWord) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "<html lang='en'>\n" +
                "<body style='text-align: center;'>\n" +
                "<h3>"+name+"老师您好：</h3>\n" +
                "<p>您的毕业设计题目：<b style='color: red'>"+passWord+"</b>在北京时间"+now+"，被驳回, <b style='color: red'>审核失败</b>，请登录系统进行修改。</p>\n" +
                "<h4 style='text-align: right;'>毕设小管家</h4>\n" +
                "</body>\n" +
                "</html>";
    }


    /***
     * 重置密码，邮件模板
     * @param name
     * @param passWord
     * @return
     */
    private String getString(String name, String passWord) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "<html lang='en'>\n" +
                "<body style='text-align: center;'>\n" +
                "<h3>"+name+"您好：</h3>\n" +
                "<p>您在北京时间"+now+"，进行了密码重置操作，您的新密码是:<b style='color: red'>"+passWord+"</b>，请妥善保管。</p>\n" +
                "<h4 style='text-align: right;'>毕设小管家</h4>\n" +
                "</body>\n" +
                "</html>";
    }
    /***
     * 验证码通知，邮件模板
     * @param name
     * @param sixCode
     * @return
     */
    private String getString2(String name, String sixCode) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "<html lang='en'>\n" +
                "<body style='text-align: center;'>\n" +
                "<h3>"+name+"您好：</h3>\n" +
                "<p>您在北京时间"+now+"，进行了身份验证操作，您的验证码是:<b style='color: red'>"+sixCode+"</b>，打死都不要告诉别人哦！要妥善保管。</p>\n" +
                "<h4 style='text-align: right;'>毕设小管家</h4>\n" +
                "</body>\n" +
                "</html>";
    }
    /***
     * 选题成功，邮件模板
     * @param name
     * @param passWord
     * @return
     */
    private String getString3(String name, String passWord) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "<html lang='en'>\n" +
                "<body style='text-align: center;'>\n" +
                "<h3>"+name+"您好：</h3>\n" +
                "<p>您所选的毕业设计题目：<b style='color: red'>"+passWord+"</b>在北京时间"+now+"，通过了教师审核<b style='color: red'>选题成功</b>，请登录系统进行查看。</p>\n" +
                "<h4 style='text-align: right;'>毕设小管家</h4>\n" +
                "</body>\n" +
                "</html>";
    }

    /***
     * 选题失败，邮件模板
     * @param name
     * @param passWord
     * @return
     */
    private String getString4(String name, String passWord) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "<html lang='en'>\n" +
                "<body style='text-align: center;'>\n" +
                "<h3>"+name+"您好：</h3>\n" +
                "<p>您所选的毕业设计题目：<b style='color: red'>"+passWord+"</b>在北京时间"+now+"，未通过教师审核<b style='color: red'>选题失败</b>，请登录系统删除失败记录重新选题。</p>\n" +
                "<h4 style='text-align: right;'>毕设小管家</h4>\n" +
                "</body>\n" +
                "</html>";
    }

    /***
     * 通知教师审核选题，邮件模板
     * @param name
     * @param passWord
     * @return
     */
    private String getString5(String name, String passWord) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "<html lang='en'>\n" +
                "<body style='text-align: center;'>\n" +
                "<h3>"+name+"老师您好：</h3>\n" +
                "<p>您的毕业设计题目：<b style='color: red'>"+passWord+"</b>在北京时间"+now+"，已被学生选择，请登录系统进行审核。</p>\n" +
                "<h4 style='text-align: right;'>毕设小管家</h4>\n" +
                "</body>\n" +
                "</html>";
    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            sender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     * @param to
     * @param subject
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath 静态资源路径和文件名
     * @param rscId 静态资源id
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            sender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
}

