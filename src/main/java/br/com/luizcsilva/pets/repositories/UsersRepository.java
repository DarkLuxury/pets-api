package br.com.luizcsilva.pets.repositories;

import br.com.luizcsilva.pets.models.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.jws.soap.SOAPBinding;

public interface UsersRepository extends MongoRepository<Users, String> {
    Users findByUsername(String username);
    Users findBy_id(ObjectId _id);
}
