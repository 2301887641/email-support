package com.email.support.api;

import com.email.support.entity.MailSendEntity;
import com.email.support.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suiguozhen
 * @date 19/07/05
 */
@Controller
public class MainSendController {

    @Autowired
    private MailSendService mailSendService;

    @PostMapping("send")
    public void send(MailSendEntity mailSendEntity) {
        mailSendService.insert(mailSendEntity);
    }
}
