package no.rogfk.sab.service

import no.rogfk.sab.modell.Student
import no.rogfk.sms.SmsService
import org.springframework.ldap.core.LdapOperations
import spock.lang.Specification

import javax.naming.Name

class LdapStudentServiceSpec extends Specification {

    private LdapStudentService ldapStudentService
    private LdapOperations ldapOperations
    private SmsService smsService

    void setup() {
        ldapOperations = Mock(LdapOperations)
        smsService = Mock(SmsService) {}
        def configService = Mock(ConfigService) {
            getStudentActivationSmsMessage() >> "Brukerkonto aktivert. %s"
        }
        ldapStudentService = new LdapStudentService(ldapOperations: ldapOperations, passwordService: new PasswordService(),
        smsService: smsService, configService: configService)

    }

    def "Activate student"() {
        when:
        ldapStudentService.activateStudent("test1")

        then:
        1 * ldapOperations.findByDn(_ as Name, _ as Class) >> new Student(allowActivation: true, mobile: "12345678")
        1 * ldapOperations.update(_ as Student)
        1 * smsService.sendSms(_ as String, _ as String) >> "test message"
    }
}
