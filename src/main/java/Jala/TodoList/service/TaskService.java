package Jala.TodoList.service;

import Jala.TodoList.model.Task;
import Jala.TodoList.repository.TaskRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createtask(Task task) {
        return taskRepository.save(task);
    }
    // método listar tarefas
    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }
    // buscar tarefa por ID
    public ResponseEntity<Task> findTaskById(Long Id) {
        return taskRepository.findById(Id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }
    // método atualizar tarefa
    public ResponseEntity<Task> updateTaskById(Task task, Long Id) {
        return taskRepository.findById(Id)
                .map(taskToUpdate ->{
                    taskToUpdate.setTitle(task.getTitle());
                    taskToUpdate.setDescription(task.getDescription());
                    taskToUpdate.setDeadLine(task.getDeadLine());
                    Task updated = taskRepository.save(taskToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    // método deletar tarefa
    public ResponseEntity<Object> deleteById (Long Id){
        return taskRepository.findById(Id)
                .map(taskToDelete ->{
                    taskRepository.deleteById(Id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
