package no.rogfk.sab.service;

import no.rogfk.sab.modell.StudentVM;

public interface StudentService {
    StudentVM getStudentVM(String cn);
    void activateStudent(String cn);
}
