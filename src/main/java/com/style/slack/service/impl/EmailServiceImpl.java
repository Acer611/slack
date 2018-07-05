package com.style.slack.service.impl;

import com.style.slack.config.EmailConfig;
import com.style.slack.model.vo.Pair;
import com.style.slack.service.IEmailService;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service(value = "emailService")
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;


    /**
     *
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String sendTo, String titel, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(titel);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     *
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content 邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
    @Override
    public void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments) {


        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(titel);
            helper.setText(content);

            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getLeft(), new FileSystemResource(pair.getRight()));
            }
        } catch (Exception e) {
           //
        }

        mailSender.send(mimeMessage);


    }


}
