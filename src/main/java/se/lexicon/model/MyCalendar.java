package se.lexicon.model;


public class MyCalendar {
    private int id;
    private int personId;
    private String name;
    private String description;

    //Constructors
    public MyCalendar(int id, int personId, String name, String description) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.description = description;
    }

    public MyCalendar(int personId, String name, String description) {
        this.personId = personId;
        this.name = name;
        this.description = description;
    }

    //Getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //override methods

    @Override
    public String toString() {
        return "MyCalendar{" +
                "id=" + id +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
