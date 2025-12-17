package local.raif.todo.controller;

import jakarta.validation.Valid;
import local.raif.todo.dto.TodoCreateRequest;
import local.raif.todo.dto.TodoResponse;
import local.raif.todo.dto.TodoUpdateRequest;
import local.raif.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoResponse> list(@RequestParam(required = false) Boolean completed) {
        return service.list(completed);
    }

    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@Valid @RequestBody TodoCreateRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @Valid @RequestBody TodoUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
