package Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 4,max = 55,message = "Имя слишком короткое или длинное.Длина должна быть больше 4 и меньше 55")
    private String name;

    @Column(name = "age")
    @Min(value = 14,message = "Минимальный возраст : 14")
    private int age;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Cloth> personClothes;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(min = 4, max = 55, message = "Имя слишком короткое или длинное.Длина должна быть больше 4 и меньше 55") String getName() {
        return name;
    }

    public void setName(@Size(min = 4, max = 55, message = "Имя слишком короткое или длинное.Длина должна быть больше 4 и меньше 55") String name) {
        this.name = name;
    }

    @Min(value = 14, message = "Минимальный возраст : 14")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 14, message = "Минимальный возраст : 14") int age) {
        this.age = age;
    }

    public List<Cloth> getPersonClothes() {
        return personClothes;
    }

    public void setPersonClothes(List<Cloth> personClothes) {
        this.personClothes = personClothes;
    }
}
