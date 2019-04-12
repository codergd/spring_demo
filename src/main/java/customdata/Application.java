package customdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject("http://localhost:8080/person?name=gaoda", Person.class);
        log.info(person.toString());
    }
}
