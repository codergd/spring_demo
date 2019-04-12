package hello;

public class Person {

    private String name;

    private Brain brain;

    public Person(String name, Brain brain){
        this.name = name;
        this.brain = brain;
    }

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

}
