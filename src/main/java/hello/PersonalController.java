package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
public class PersonalController {

    @RequestMapping("/person")
    public Person returnPerson(@RequestParam(value = "name") String name){

        return new Person(name, new Brain(new Random().nextInt(100)));
    }

}
