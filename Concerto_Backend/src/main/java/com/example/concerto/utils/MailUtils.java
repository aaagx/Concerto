package com.example.concerto.utils;

import com.example.concerto.exception.CustomException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 21:16 2021/5/10
 * @Version: $
 */
@Component
public class MailUtils {

    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    JavaMailSender mailSender;

    public void sendTemplateMail(String email,String content)  {
        try {
            //添加动态数据，替换模板里面的占位符
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
            //将模板文件及数据渲染完成之后，转换为html字符串
            Map<String,String> model = new HashMap<>();
            model.put("Content",content);
            String templateHtml = null;
            templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Concerto验证码");
        //第二个参数是否是html，true表示发送的邮件正文是html文本
        helper.setText(templateHtml, true);
        helper.setFrom("675452601@qq.com");
        mailSender.send(message);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(500,"邮件发送失败");
        } catch (TemplateException e) {
            e.printStackTrace();
            throw new CustomException(500,"邮件发送失败");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new CustomException(500,"邮件发送失败");

        }

    }
}
