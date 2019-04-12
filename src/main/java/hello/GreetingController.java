package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {


    private final static String template = "hello, %s";

    /**线程安全的long**/
    private final AtomicLong atomicLong = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name){

        return new Greeting(atomicLong.incrementAndGet(), String.format(template, name));
    }
}
