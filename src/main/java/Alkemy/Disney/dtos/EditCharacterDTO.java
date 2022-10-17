package Alkemy.Disney.dtos;


public class EditCharacterDTO {

    private Long id;
    private String name, history;
    private int age;
    private float weight;

    public EditCharacterDTO(){}

    public Long getId() {  return id; }

    public String getName() {
        return name;
    }

    public String getHistory() {
        return history;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }
}
