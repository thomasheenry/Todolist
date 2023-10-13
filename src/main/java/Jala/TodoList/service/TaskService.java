package Jala.TodoList.service;

import Jala.TodoList.model.Task;
import Jala.TodoList.repository.MongoTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final MongoTaskRepository mongoTaskRepository;

    public Task createTask(String userId, Task task) {
        task.setUserId(userId);
        task.setStatus(false);
        return mongoTaskRepository.save(task);
    }


    public Task findTaskById(String id) {
        return mongoTaskRepository.findById(id).orElse(null);
    }

    public Task updateTaskById(String id, Task newTask) {
        Task existingTask = mongoTaskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitle(newTask.getTitle());
            existingTask.setDescription(newTask.getDescription());
            existingTask.setDeadLine(newTask.getDeadLine());
            return mongoTaskRepository.save(existingTask);
        }
        return null;
    }

    public boolean deleteById(String id) {
        Task existingTask = mongoTaskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            mongoTaskRepository.delete(existingTask);
            return true;
        }
        return false;
    }

    public List<Task> listAllTasksByUserId(String userId) {
        return mongoTaskRepository.findByUserId(userId);
    }
}