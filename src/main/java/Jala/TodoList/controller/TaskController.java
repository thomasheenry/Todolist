package Jala.TodoList.controller;

import Jala.TodoList.model.Task;
import Jala.TodoList.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    TaskService taskService;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        log.info("Criando uma nova Tarefa com as informações [{}]", task);
        return taskService.createtask(task);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long Id) {
        log.info("Buscando tarefa pelo seu Id [{}]", Id);
        return taskService.findTaskById(Id);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long Id, @RequestBody Task task) {
        log.info("Atualizando a tarefa [{}], as novas informações são: [{}]",Id,  task);
        return taskService.updateTaskById(task, Id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long Id) {
        log.info("Excluindo tarefa com o id [{}]", Id);
        return taskService.deleteById(Id);
    }
}
