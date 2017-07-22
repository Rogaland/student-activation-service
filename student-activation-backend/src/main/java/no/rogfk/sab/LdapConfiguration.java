package no.rogfk.sab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Profile("!test")
@Configuration
public class LdapConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(env.getRequiredProperty("spring.ldap.url"));
        contextSource.setUserDn(env.getRequiredProperty("spring.ldap.user"));
        contextSource.setPassword(env.getRequiredProperty("spring.ldap.password"));
        contextSource.setBase(env.getProperty("spring.ldap.base"));
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }


}