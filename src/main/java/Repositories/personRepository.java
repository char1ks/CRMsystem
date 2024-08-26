package Repositories;

import Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface personRepository extends JpaRepository<Person,Integer> {
}
