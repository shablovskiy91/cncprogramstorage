package com.shablovskiy91.controllers;

import com.shablovskiy91.models.CncProgram;
import com.shablovskiy91.models.CncProgramStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /*
     * !!!Only for demonstration project!!! Don't use in real project!!!
     * Search bot (GOOGLE) goes to all links on this site. If he go to link "Delete" - the program will be dleted.
     */
    @GetMapping("/delete/{programId}")
    public String delete(@PathVariable("programId") String programId) {
        CncProgram programToDelete = CncProgramStorage.getCncProgramList().stream().
                filter(cncProgram -> cncProgram.getProgramId().equals(programId)).
                findFirst().
                orElseThrow(RuntimeException::new);
        CncProgramStorage.getCncProgramList().remove(programToDelete);
        return "redirect:/";
    }

    @GetMapping("/edit-form/{programId}")
    public String editForm(@PathVariable("programId") String programId, Model model) {
        CncProgram programToEdit = CncProgramStorage.getCncProgramList().stream().
                filter(cncProgram -> cncProgram.getProgramId().equals(programId)).
                findFirst().
                orElseThrow(RuntimeException::new);
        model.addAttribute("cncProgram", programToEdit);
        return "edit-form";
    }

    @PostMapping("/update")
    public String update(CncProgram cncProgram) {
        delete(cncProgram.getProgramId());
        CncProgramStorage.getCncProgramList().add(cncProgram);
        return "redirect:/";
    }
}
