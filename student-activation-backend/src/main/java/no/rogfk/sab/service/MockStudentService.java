package no.rogfk.sab.service;

import lombok.extern.slf4j.Slf4j;
import no.rogfk.sab.modell.StudentVM;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Profile("mock")
@Service
public class MockStudentService implements StudentService {
    private Map<String, StudentVM> students = new HashMap<>();

    @PostConstruct
    public void init() {
        StudentVM student1 = new StudentVM();
        student1.setDn(new MockName("cn=test1,ou=elev,ou=personer,o=rfk-meta"));
        student1.setFullName("test1");
        student1.setAllowActivation(true);

        StudentVM student2 = new StudentVM();
        student2.setDn(new MockName("cn=test2,ou=elev,ou=personer,o=rfk-meta"));
        student2.setFullName("test2");
        student2.setAllowActivation(false);

        students.put("test1", student1);
        students.put("test2", student2);
    }

    @Override
    public StudentVM getStudentVM(String cn) {
        return students.get(cn);
    }

    @Override
    public void activateStudent(String cn) {
        log.info("Activate student called in mock service, cn={}", cn);
    }
}
