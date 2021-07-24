package com.shablovskiy91.controllers;

import com.shablovskiy91.models.CncProgram;
import com.shablovskiy91.models.CncProgramStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class CncWebController {

    @GetMapping("/test")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/")
    public String programs(Model model) {
        model.addAttribute("programs", CncProgramStorage.getCncProgramList());
        return "programs-list";
    }

    @GetMapping("/add-form")
    public String addForm() {
        return "add-form";
    }

    @PostMapping("/create")
    public String create(CncProgram cncProgram) {
        CncProgramStorage.getCncProgramList().add(cncProgram);
        return "redirect:/";
    }

}
