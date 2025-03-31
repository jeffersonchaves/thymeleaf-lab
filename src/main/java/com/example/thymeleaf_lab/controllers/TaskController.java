package com.example.thymeleaf_lab.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.thymeleaf_lab.models.Task;

@Controller
public class TaskController {

    private List<Task> tasks = new ArrayList<Task>();

    @GetMapping("/list")
    public String list(Model model) {

        Task task = new Task(1L, "Read a book", LocalDateTime.now());
        tasks.add(task);

        model.addAttribute("task", task);
        model.addAttribute("tasks", tasks);

        return "task-list";
    }

}
