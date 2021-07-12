package com.shablovskiy91.controllers;

import com.shablovskiy91.models.CncProgramStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CncWebController {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/test")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/programs")
    public String programList(Model model) {
        model.addAttribute("programs", CncProgramStorage.getCncProgramList());
        return "programs";
    }





}
