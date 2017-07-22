package no.rogfk.sab;

import no.rogfk.jwt.annotations.EnableJwt;
import no.rogfk.sms.annotations.EnableSmsWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSmsWrapper
@EnableJwt
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
