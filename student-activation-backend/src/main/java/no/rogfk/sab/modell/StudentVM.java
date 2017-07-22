package no.rogfk.sab.modell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"})
public class StudentVM {

    // cn=ola,ou=elev,ou=personer,o=rfk-meta
    @Id
    @JsonIgnore
    private Name dn;

    @Attribute(name = "fullname")
    private String fullName;

    @Attribute(name = "sbasaktiveringtillatt")
    private Boolean allowActivation;
    
}
