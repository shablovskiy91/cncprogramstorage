package com.shablovskiy91.controllers;

import com.shablovskiy91.dao.CncProgramDao;
import com.shablovskiy91.models.CncProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CncWebController {

    private final CncProgramDao cncProgramDao;

    @Autowired
    public CncWebController(CncProgramDao cncProgramDao) {
        this.cncProgramDao = cncProgramDao;
    }

    @GetMapping("/test")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/")
    public String programs(Model model) {
        model.addAttribute("programs", cncProgramDao.findAll());
        return "programs-list";
    }

    @GetMapping("/add-form")
    public String addForm() {
        return "add-form";
    }

    @PostMapping("/create")
    public String create(CncProgram cncProgram) {
        cncProgramDao.save(cncProgram);
        return "redirect:/";
    }

    @GetMapping("/edit-form/{programId}")
    public String editForm(@PathVariable("programId") String programId, Model model) {
        CncProgram programToEdit = cncProgramDao.getById(programId);
        model.addAttribute("cncProgram", programToEdit);
        return "edit-form";
    }

    @PostMapping("/update")
    public String update(CncProgram cncProgram) {
        cncProgramDao.update(cncProgram);
        return "redirect:/";
    }

    /*
     * !!!Only for demonstration project!!! Don't use in real project!!!
     * Search bot (GOOGLE) goes to all links on this site. If he go to link "Delete" - the program will be dleted.
     */
    @GetMapping("/delete/{programId}")
    public String delete(@PathVariable("programId") String programId) {
        CncProgram programToDelete = cncProgramDao.getById(programId);
        cncProgramDao.delete(programToDelete);
        return "redirect:/";
    }
}
