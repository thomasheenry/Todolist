    package Jala.TodoList.controller;

    import Jala.TodoList.model.Task;
    import Jala.TodoList.service.TaskService;
    import lombok.AllArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1")
    @AllArgsConstructor
    @Slf4j
    public class TaskController {

        private final TaskService taskService;

        @PostMapping("/tasks")
        @ResponseStatus(HttpStatus.CREATED)
        public Task createTask(@RequestBody Task task) {
            log.info("Criando uma nova Tarefa com as informações [{}]", task);
            return taskService.createTask(task);
        }

        @GetMapping("/tasks")
        @ResponseStatus(HttpStatus.OK)
        public List<Task> getAllTasks() {
            log.info("Listando todas as tarefas cadastradas");
            return taskService.listAllTasks();
        }

        @GetMapping("/tasks/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") String id) {
            log.info("Buscando tarefa pelo seu Id [{}]", id);
            Task task = taskService.findTaskById(id);
            if (task != null) {
                return ResponseEntity.ok().body(task);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/tasks/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") String id, @RequestBody Task task) {
            log.info("Atualizando a tarefa [{}], as novas informações são: [{}]", id, task);
            Task updatedTask = taskService.updateTaskById(id, task);
            if (updatedTask != null) {
                return ResponseEntity.ok().body(updatedTask);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/tasks/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public ResponseEntity<Object> deleteById(@PathVariable(value = "id") String id) {
            log.info("Excluindo tarefa com o id [{}]", id);
            boolean deleted = taskService.deleteById(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
