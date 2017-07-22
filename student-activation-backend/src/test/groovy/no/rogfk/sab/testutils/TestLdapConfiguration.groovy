package no.rogfk.sab.testutils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.ldap.core.LdapOperations

@Profile("test")
@Configuration
class TestLdapConfiguration {

    @Bean
    LdapOperations ldapOperations() {
        return new TestLdapOperations()
    }

}
