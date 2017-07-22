package no.rogfk.sab.controller;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import no.rogfk.jwt.annotations.JwtParam;
import no.rogfk.jwt.exceptions.ClaimValidatorException;
import no.rogfk.jwt.exceptions.InvalidTokenException;
import no.rogfk.jwt.exceptions.MissingJwtParamException;
import no.rogfk.sab.exception.StudentAlreadyActivatedException;
import no.rogfk.sab.exception.StudentNotFoundException;
import no.rogfk.sab.modell.ResponseError;
import no.rogfk.sab.modell.StudentToken;
import no.rogfk.sab.modell.StudentVM;
import no.rogfk.sab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/account/activation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivationController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public StudentVM getStudent(@JwtParam(name = "t") StudentToken studentToken) {
        return studentService.getStudentVM(studentToken.getId());
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public void activateStudent(@JwtParam(name = "t") StudentToken studentToken) {
        studentService.activateStudent(studentToken.getId());
    }

    @ExceptionHandler({MissingJwtParamException.class, InvalidTokenException.class, JwtException.class, ClaimValidatorException.class})
    public ResponseEntity handleInvalidTokenException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseError("Invalid token"));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity handleStudentNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError("Vi fant deg ikke i systemet."));
    }

    @ExceptionHandler(StudentAlreadyActivatedException.class)
    public ResponseEntity handleStudentAlreadyActivatedException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError("Du har aktivert kontoen din tidligere."));
    }
}

