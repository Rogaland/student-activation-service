package no.rogfk.sab.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ConfigService {

    @Value("${spring.application.student-activation-sms-message}")
    private String studentActivationSmsMessage;


}
