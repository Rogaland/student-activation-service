package no.rogfk.sab.controller;

import no.rogfk.jwt.SpringJwtTokenizer;
import no.rogfk.sab.modell.StudentToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Profile("mock")
@RestController
@RequestMapping(value = "/mock", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
public class MockController {

    private static final String link = "<a href='%s'>Go to student activation page</a>";

    @Autowired
    private SpringJwtTokenizer springJwtTokenizer;

    @Value("${jwt.issuer}")
    private String issuer;

    @RequestMapping("/token")
    public String generateToken(HttpServletRequest request, @RequestParam String id) {
        StudentToken studentToken = new StudentToken();
        studentToken.setId(id);
        studentToken.setIss(issuer);
        studentToken.setIat(String.valueOf(System.currentTimeMillis()));
        String token = springJwtTokenizer.create(studentToken);

        String url = request.getRequestURL().toString();
        url = url.substring(0, url.indexOf("/mock/token"));
        url = url + "/#/?t=" + token;
        return String.format(link, url);
    }

}
