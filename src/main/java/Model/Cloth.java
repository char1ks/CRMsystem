package Model;

import javax.persistence.*;

@Entity
@Table(name = "clothes")
public class Cloth {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nomination")
    private String nomination;

    @ManyToOne
    @JoinColumn(name = "personId",referencedColumnName = "person_id")
    private Person person;

    public Cloth() {}

    public Cloth(String nomination, Person person) {
        this.nomination = nomination;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
