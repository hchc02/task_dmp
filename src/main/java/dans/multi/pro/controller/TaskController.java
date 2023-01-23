package dans.multi.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dans.multi.pro.service.TaskService;

@CrossOrigin()
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/no1")
    public ResponseEntity<?> taskNo1() {
		return ResponseEntity.ok(taskService.taskNo1());
	}

    @PostMapping("/no2/{id}")
    public ResponseEntity<?> taskNo2(@PathVariable String id) {
		return ResponseEntity.ok(taskService.taskNo2(id));
	}
}
