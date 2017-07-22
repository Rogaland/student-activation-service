package no.rogfk.sab.testutils

import org.springframework.ldap.core.LdapOperations

class TestLdapOperations implements LdapOperations {
    @Delegate LdapOperations delegate
}
