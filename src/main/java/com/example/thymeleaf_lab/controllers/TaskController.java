package com.example.thymeleaf_lab.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.thymeleaf_lab.models.Task;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    private List<Task> tasks = new ArrayList<Task>();

    @GetMapping("/create")
    public String createForm() {

        return "task-create";
    }

    // @PostMapping("/create")
    // @ResponseBody
    // public String create(HttpServletRequest request) {

    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
    // HH:mm:ss");

    // String name = (String) request.getParameter("name");
    // LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"),
    // formatter);

    // Task task = new Task(1L, name, dateTime);

    // return task.toString();
    // }

    @PostMapping("/create")
    public String create(Model model, Task task) {

        task.setId(Long.valueOf(tasks.size() + 1));

        tasks.add(task);

        model.addAttribute("tasks", tasks);

        return "task-list";
    }

    @GetMapping({ "", "/", "/list" })
    public String list(Model model) {

        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {

        System.out.println("Path Variable: " + id);

        for (Task task : tasks) {
            if (task.getId() == id) {

                model.addAttribute("task", task);

                return "task-edit";

            }
        }

        return "redirect:/list";

    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Task task) {

        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.set(tasks.indexOf(t), task);
            }
        }

        return "redirect:/list";

    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {

        tasks.removeIf(task -> task.getId().equals(id));

        return "redirect:/list";

    }

}
