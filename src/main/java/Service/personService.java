package Service;

import Model.Person;
import Repositories.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class personService {
    private personRepository operations;

    @Autowired
    public void setOperations(personRepository operations) {
        this.operations = operations;
    }

    public List<Person> getAll(){
        return operations.findAll();
    }
    public Person getConcret(int id){
        return operations.findById(id).orElse(null);
    }
    public void save(Person person){
        operations.save(person);
    }
    public void update(int id,Person person){
        person.setId(id);
        operations.save(person);
    }
    public void delete(int id){
        operations.deleteById(id);
    }
}
