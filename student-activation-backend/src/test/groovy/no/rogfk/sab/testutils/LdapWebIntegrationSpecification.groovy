package no.rogfk.sab.testutils

import no.rogfk.sab.Application
import no.rogfk.sab.modell.Student
import no.rogfk.sab.modell.StudentVM
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.ldap.core.LdapOperations
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import javax.naming.Name

@ActiveProfiles(["test", "mock"])
@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration(classes = Application)
abstract class LdapWebIntegrationSpecification extends Specification {

    @Autowired
    private TestLdapOperations testLdapOperations

    protected Student student
    protected StudentVM studentVM

    void setup() {
        student = new Student(allowActivation: true)
        studentVM = new StudentVM()
        testLdapOperations.delegate = Mock(LdapOperations) {
            findByDn(_ as Name, _ as Class) >> { args -> return (args[1] == Student) ? student : studentVM }
        }
    }
}
