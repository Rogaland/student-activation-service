package no.rogfk.sab.controller.integration

import no.rogfk.jwt.SpringJwtTokenizer
import no.rogfk.sab.controller.ActivationController
import no.rogfk.sab.modell.StudentToken
import no.rogfk.sab.modell.StudentVM
import no.rogfk.sab.testutils.LdapWebIntegrationSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpStatus

class ActivationControllerSpec extends LdapWebIntegrationSpecification {

    @Value('${local.server.port}')
    private int port

    @Autowired
    private ActivationController activationController

    @Autowired
    private SpringJwtTokenizer springJwtTokenizer

    private TestRestTemplate restTemplate
    private String studentUrl


    void setup() {
        restTemplate = new TestRestTemplate()
        studentUrl = "http://localhost:${port}/account/activation/student"
    }

    def "Get student, no id request param"() {
        when:
        def response = restTemplate.getForEntity(studentUrl, StudentVM)

        then:
        response.statusCode == HttpStatus.FORBIDDEN
    }

    def "Get student, valid id request param"() {
        given:
        def token = springJwtTokenizer.create(new StudentToken(id: "test1"))
        def url = studentUrl + "?t={id}"

        when:
        def response = restTemplate.getForEntity(url, StudentVM, token)

        then:
        response.statusCode == HttpStatus.OK
        response.body != null
    }

    def "Get student, InvalidTokenException"() {
        given:
        def url = studentUrl + "?t=1234"

        when:
        def response = restTemplate.getForEntity(url, StudentVM, "test123")

        then:
        response.statusCode == HttpStatus.FORBIDDEN
    }

    def "Activate student"() {
        given:
        def token = springJwtTokenizer.create(new StudentToken(id: "test2"))
        def url = "${studentUrl}?t=${token}"

        when:
        def response = restTemplate.postForEntity(url, Void, String)

        then:
        response.statusCode == HttpStatus.OK
    }
}
