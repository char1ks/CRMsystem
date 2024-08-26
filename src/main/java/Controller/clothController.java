package Controller;

import Repositories.clothRepository;
import Model.*;
import Service.clothService;
import Service.personService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/clothes")
public class clothController {
    private clothService operations;
    private  personService operationsPerson;
    @Autowired
    public void setOperations(clothService operations) {
        this.operations = operations;
    }
    @Autowired
    public void setOperationsPerson(personService operationsPerson) {
        this.operationsPerson = operationsPerson;
    }

    //ELEMENTS
    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("allClothes",operations.getAll());
        return "cloth/MainPage";
    }
    @GetMapping("/{id}")
    public String concretPage(@PathVariable("id")int id,
                              Model model){
        model.addAttribute("concretCloth",operations.getConcret(id));
        if(operations.getConcret(id).getPerson()==null)
            model.addAttribute("owner","Владельца нету!");
        else
            model.addAttribute("owner",operations.getConcret(id).getPerson().getName());
        return "cloth/ConcretPage";
    }
    //ADD
    @GetMapping("/new")
    public String addPage(@ModelAttribute("newCloth") Cloth cloth){
        return "cloth/newPage";
    }
    @PostMapping
    public String addInDB(@ModelAttribute("newCloth") @Valid Cloth clothn,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "cloth/newPage";
        operations.save(clothn);
        return "redirect:/clothes";
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id,
                           Model model){
        model.addAttribute("editCloth",operations.getConcret(id));
        return "cloth/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@ModelAttribute("editCloth") @Valid Cloth cloth,
                           BindingResult bindingResult,@PathVariable("id")int id){
        if(bindingResult.hasErrors())
            return "cloth/editPage";
        operations.update(id,cloth);
        return "redirect:/clothes";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        operations.delete(id);
        return "redirect:/clothes";
    }
    //CHOOSE AN ELEMENTS
    @GetMapping("/{person_id}/choose_cloth")
    public String chooseClothPage(@PathVariable("person_id") int personId,
                                  Model model){
        model.addAttribute("person_id",personId).addAttribute("AllAvaliableClothes",
                operations.getAllClothesWherePartnerIdNULL());
        return "cloth/chooseCloth";
    }
    @PostMapping("/{person_id}/{cloth_id}")
    public String saveInDBchosen(@PathVariable("person_id") int person_id,
                                 @PathVariable("cloth_id") int cloth_id){
        Cloth cloth=operations.getConcret(cloth_id);
        cloth.setPerson(operationsPerson.getConcret(person_id));

        Person editablePerson=operationsPerson.getConcret(person_id);
        List<Cloth> beforeCloth = new ArrayList<>(editablePerson.getPersonClothes());
        beforeCloth.add(cloth);

        operations.update(cloth_id,cloth);
        operationsPerson.update(person_id,editablePerson);
        return "redirect:/clothes/"+person_id+"/choose_cloth";
    }
    //DETACH
    @PostMapping("/{clothId}/detach")
    public String detach(@PathVariable("clothId") int clothId){
        Cloth cloth=operations.getConcret(clothId);
        cloth.setPerson(null);
        operations.update(clothId,cloth);
        return "redirect:/person";
    }
}
