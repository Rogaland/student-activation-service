package no.rogfk.sab.service;

import lombok.extern.slf4j.Slf4j;
import no.rogfk.ldap.utilities.LdapTimestamp;
import no.rogfk.sab.exception.StudentAlreadyActivatedException;
import no.rogfk.sab.exception.StudentNotFoundException;
import no.rogfk.sab.modell.Student;
import no.rogfk.sab.modell.StudentVM;
import no.rogfk.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@Slf4j
@Profile("!mock")
@Service
public class LdapStudentService implements StudentService {

    private static final String base = "cn=%s,ou=elev,ou=personer,o=rfk-meta";

    @Autowired
    private LdapOperations ldapOperations;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private ConfigService configService;

    @Override
    public StudentVM getStudentVM(String username) {
        try {
            Name dn = LdapNameBuilder.newInstance(String.format(base, username)).build();
            log.info(dn.toString());
            return ldapOperations.findByDn(dn, StudentVM.class);
        } catch (NameNotFoundException e) {
            throw new StudentNotFoundException(e.getMessage());
        }
    }

    @Override
    public void activateStudent(String username) {
        Name dn = LdapNameBuilder.newInstance(String.format(base, username)).build();
        log.info(dn.toString());

        Student student = ldapOperations.findByDn(dn, Student.class);
        if (!student.getAllowActivation()) {
            throw new StudentAlreadyActivatedException("Already activated");
        }

        String password = passwordService.generatePassword();
        student.setLoginDisabled(false);
        student.setAllowActivation(false);
        student.setEndUserAgreedTime(LdapTimestamp.getTimestampString());
        student.setUserPassword(password);
        ldapOperations.update(student);

        String message = String.format(configService.getStudentActivationSmsMessage(), username, password);
        String response = smsService.sendSms(message, student.getMobile());
        log.info("SMS response: {}", response);
    }


}
