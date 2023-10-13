package Jala.TodoList.repository;


import Jala.TodoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoTaskRepository extends MongoRepository<Task, String> {

    List<Task> findByUserId(String userId);
}
