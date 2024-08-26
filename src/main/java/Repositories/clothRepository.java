package Repositories;

import Model.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface clothRepository extends JpaRepository<Cloth,Integer> {
    List<Cloth> findByPersonIdIsNull();
    List<Cloth> findByPersonId(Integer personId);
}
