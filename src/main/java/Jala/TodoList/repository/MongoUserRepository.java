package Jala.TodoList.repository;

import Jala.TodoList.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<User, String> {
}
