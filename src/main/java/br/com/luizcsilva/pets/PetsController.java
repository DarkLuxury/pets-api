package br.com.luizcsilva.pets;

import br.com.luizcsilva.pets.models.Pets;
import br.com.luizcsilva.pets.repositories.PetsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {
    @Autowired
    private PetsRepository repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Pets> getPets(){
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pets getPetById(@PathVariable("id") ObjectId id){
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Pets modifyPetById(@PathVariable("id") ObjectId id, @RequestBody Pets pet) {
        Pets modifiedPet = getPetById(id);
        if (pet.getName() != null){
            modifiedPet.setName(pet.getName());
        }
        if (pet.getBreed() != null){
            modifiedPet.setBreed(pet.getBreed());
        }
        if (pet.getSpecies() != null){
            modifiedPet.setSpecies(pet.getSpecies());
        }
        repository.save(modifiedPet);
        return modifiedPet;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Pets postNewPet(@RequestBody Pets pet) {
        pet.set_id(ObjectId.get());
        repository.save(pet);
        return pet;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }

}
