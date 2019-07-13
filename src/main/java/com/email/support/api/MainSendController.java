package com.email.support.api;

import com.email.support.entity.MailSendEntity;
import com.email.support.service.MailSendService;
import com.email.support.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suiguozhen
 * @date 19/07/05
 */
@Controller
@RequestMapping("/send")
public class MainSendController {

    @Autowired
    private MailSendService mailSendService;

    @PostMapping
    public void send(MailSendEntity mailSendEntity) {
        mailSendEntity.setSendId(UUIDUtils.generate());
        mailSendService.insert(mailSendEntity);
    }
}
