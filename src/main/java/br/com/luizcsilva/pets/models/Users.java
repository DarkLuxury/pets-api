package br.com.luizcsilva.pets.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.jws.soap.SOAPBinding;

public class Users {
    @Id
    public ObjectId _id;
    public String username;
    public String password;

    public Users(){}

    public Users(ObjectId _id, String username, String password) {
        this._id = _id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return this._id.toHexString();
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
