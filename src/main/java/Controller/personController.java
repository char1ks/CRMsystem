package Controller;

import Model.Person;
import Service.clothService;
import Service.personService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class personController {
    private personService operations;
    private clothService operationsService;

    @Autowired
    public void setOperations(personService operations) {
        this.operations = operations;
    }
    @Autowired
    public void setOperationsService(clothService operationsService) {
        this.operationsService = operationsService;
    }

    //ELEMENTS
    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("allPerson",operations.getAll());
        return "person/MainPage";
    }
    @GetMapping("/{id}")
    public String concretPage(@PathVariable("id")int id,
                              Model model){
        model.addAttribute("concretPerson",operations.getConcret(id));
        model.addAttribute("allClothes",operationsService.getAllClothWherePartnerId(id));
        return "person/ConcretPage";
    }
    //ADD
    @GetMapping("/new")
    public String addPage(@ModelAttribute("newPerson") Person person){
        return "person/newPage";
    }
    @PostMapping
    public String addInDB(@ModelAttribute("newPerson") @Valid Person person,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "person/newPage";
        operations.save(person);
        return "redirect:/clothes/" + person.getId() + "/choose_cloth";
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id,
                           Model model){
        model.addAttribute("editPerson",operations.getConcret(id));
        return "person/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@ModelAttribute("editPerson") @Valid Person person,
                           BindingResult bindingResult,@PathVariable("id")int id){
        if(bindingResult.hasErrors())
            return "person/editPage";
        operations.update(id,person);
        return "redirect:/person";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        operations.delete(id);
        return "redirect:/person";
    }

}
