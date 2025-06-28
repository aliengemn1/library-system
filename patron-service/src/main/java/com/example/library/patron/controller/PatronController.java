package com.example.library.patron.controller;

import com.example.library.patron.model.Patron;
import com.example.library.patron.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    // Web UI endpoints
    @GetMapping
    public String index(Model model) {
        List<Patron> patrons = patronService.getAllPatrons();
        model.addAttribute("patrons", patrons);
        return "patron/index";
    }

    @GetMapping("/new")
    public String newPatronForm(Model model) {
        model.addAttribute("patron", new Patron());
        return "patron/form";
    }

    @GetMapping("/edit/{id}")
    public String editPatronForm(@PathVariable Long id, Model model) {
        Patron patron = patronService.getPatronById(id);
        model.addAttribute("patron", patron);
        return "patron/form";
    }

    @PostMapping("/save")
    public String savePatron(@ModelAttribute Patron patron) {
        patronService.savePatron(patron);
        return "redirect:/patrons";
    }

    @GetMapping("/delete/{id}")
    public String deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return "redirect:/patrons";
    }

    @GetMapping("/view/{id}")
    public String viewPatron(@PathVariable Long id, Model model) {
        Patron patron = patronService.getPatronById(id);
        model.addAttribute("patron", patron);
        return "patron/view";
    }

    // REST API endpoints
    @GetMapping("/api/patrons")
    @ResponseBody
    public List<Patron> getAllPatronsApi() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/api/patrons/{id}")
    @ResponseBody
    public Patron getPatronByIdApi(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    @PostMapping("/api/patrons")
    @ResponseBody
    public Patron createPatronApi(@RequestBody Patron patron) {
        return patronService.savePatron(patron);
    }

    @PutMapping("/api/patrons/{id}")
    @ResponseBody
    public Patron updatePatronApi(@PathVariable Long id, @RequestBody Patron patron) {
        patron.setId(id);
        return patronService.savePatron(patron);
    }

    @DeleteMapping("/api/patrons/{id}")
    @ResponseBody
    public void deletePatronApi(@PathVariable Long id) {
        patronService.deletePatron(id);
    }

    @GetMapping("/api/search")
    @ResponseBody
    public List<Patron> searchPatronsApi(@RequestParam String query) {
        return patronService.searchPatrons(query);
    }
} 