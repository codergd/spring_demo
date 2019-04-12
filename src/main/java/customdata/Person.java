package customdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    private String name;

    private Brain brain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brain getBrain() {
        return brain;
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
    }

    public String toString(){
        return "person["+
                "name: "+ name +
                ", brain" + brain +
                "]";
    }
}
