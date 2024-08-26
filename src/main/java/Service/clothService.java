package Service;

import Model.Cloth;
import Repositories.clothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class clothService {
    private clothRepository operations;

    @Autowired
    public void setOperations(clothRepository operations) {
        this.operations = operations;
    }

    public List<Cloth> getAll(){
        return operations.findAll();
    }
    public Cloth getConcret(int id){
        return operations.findById(id).orElse(null);
    }
    public void save(Cloth cloth){
        operations.save(cloth);
    }
    public void update(int id,Cloth cloth){
        cloth.setId(id);
        operations.save(cloth);
    }
    public void delete(int id){
        operations.deleteById(id);
    }
    public List<Cloth>getAllClothWherePartnerId(int id){
        return operations.findByPersonId(id);
    }
    public List<Cloth>getAllClothesWherePartnerIdNULL(){
        return operations.findByPersonIdIsNull();
    }
}
