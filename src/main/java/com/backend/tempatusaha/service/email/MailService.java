package com.backend.tempatusaha.service.email;

import com.backend.tempatusaha.dto.request.MailRequest;
import org.springframework.scheduling.annotation.Async;

public interface MailService {

    @Async("threadPoolTaskExecutor")
    void sendEmail(MailRequest mail);
}
