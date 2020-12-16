package com.thiethaa.dockermavenplugin;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping("/{name}")
    public String greeting(@PathVariable("name") String name){
        return "Hi "+name;
    }
}
