package no.rogfk.sab.modell;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"})
public class Student {
    @Id
    private Name dn;

    @Attribute(name = "sbasaktiveringtillatt")
    private Boolean allowActivation;

    @Attribute(name = "brfkEndUserAgreedTime")
    private String endUserAgreedTime;

    @Attribute(name = "loginDisabled")
    private Boolean loginDisabled;

    @Attribute(name = "userpassword")
    private String userPassword;

    @Attribute(name = "mobile")
    private String mobile;

}
