package Jala.TodoList.service;

import Jala.TodoList.model.User;
import Jala.TodoList.repository.MongoUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final MongoUserRepository mongoUserRepository;
    public User createUser(User user){
        return mongoUserRepository.save(user);
    }
    public User findUserbyId(String id){
        return mongoUserRepository.findById(id).orElse(null);
    }
    public User updateUserbyId(String id, User newUser){
        User existingUser = mongoUserRepository.findById(id).orElse(null);
        if(existingUser != null){
            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPassword(newUser.getPassword());
            return mongoUserRepository.save(existingUser);
        }else {
            return null;
        }

    }
    public boolean deleteUser(String id, String email, String senha){
        User existingUser = mongoUserRepository.findById(id).orElse(null);
        if (existingUser != null && existingUser.getEmail().equals(email) && existingUser.getPassword().equals(senha)){
            mongoUserRepository.delete(existingUser);
            return true;
        }
        return false;
    }
}
