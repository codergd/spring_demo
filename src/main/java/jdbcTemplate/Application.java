package jdbcTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Create table....");
        jdbcTemplate.execute("DROP TABLE CUSTOMER IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE CUSTOMER(ID SERIAL, FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255))");

        List<Object[]> splitUpNames = Arrays.asList("gao da","faker li","rookie li").stream().map(name -> name.split(" ")).collect(Collectors.toList());
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s....", name[0], name[1])));

        jdbcTemplate.batchUpdate("INSERT INTO CUSTOMER(FIRST_NAME, LAST_NAME) VALUES(?,?)", splitUpNames);

        log.info("Querying for customer records where first_name = faker....");
        jdbcTemplate.query("SELECT ID, FIRST_NAME, LAST_NAME FROM CUSTOMER WHERE FIRST_NAME=?", new Object[]{"faker"},
                (rs, rowNum) -> new Customer(rs.getInt("ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME")))
                .forEach(customer -> log.info(customer.toString()));

    }
}
