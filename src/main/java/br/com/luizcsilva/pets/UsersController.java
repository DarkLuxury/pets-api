package br.com.luizcsilva.pets;

import br.com.luizcsilva.pets.models.Users;
import br.com.luizcsilva.pets.repositories.UsersRepository;
import br.com.luizcsilva.pets.services.MongoUserDetailsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersRepository repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("admin")){
            return ResponseEntity.ok().body(repository.findAll());
        }
        return ResponseEntity.status(401).body(null);
    }

    @RequestMapping(value = "/{_id}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable ObjectId _id) {
        return ResponseEntity.ok().body(repository.findBy_id(_id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity setNewUser(@RequestBody Users newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("admin") && newUser.getUsername() != null && newUser.getPassword() != null){
            if (repository.findByUsername(newUser.getUsername()) == null ){
                repository.save(newUser);
                System.out.println(newUser);
                System.out.println(authentication.getName());
                return ResponseEntity.ok().body(newUser);
            }
            if (repository.findByUsername(newUser.getUsername()) != null){
                return ResponseEntity.badRequest().body("The user " + newUser.getUsername() + " already exists");
            }

            return ResponseEntity.badRequest().body("Invalid user");
        }
        return ResponseEntity.status(401).body(null);
    }


}
