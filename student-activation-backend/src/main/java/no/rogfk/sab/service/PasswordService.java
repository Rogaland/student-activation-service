package no.rogfk.sab.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PasswordService {

    String generatePassword() {
        String alpha = RandomStringUtils.randomAlphabetic(6);
        String numeric = RandomStringUtils.random(2, false,true);
        String password = alpha + numeric;
        log.info(password);

        return password;
    }

}
