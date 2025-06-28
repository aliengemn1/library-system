package com.ashehada.library.circulation.controller;

import com.ashehada.library.circulation.model.CirculationRecord;
import com.ashehada.library.circulation.service.CirculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/circulation")
public class CirculationController {

    @Autowired
    private CirculationService circulationService;

    // Web UI endpoints
    @GetMapping
    public String index(Model model) {
        List<CirculationRecord> records = circulationService.getAllRecords();
        model.addAttribute("records", records);
        return "circulation/index";
    }

    @GetMapping("/new")
    public String newRecordForm(Model model) {
        model.addAttribute("record", new CirculationRecord());
        return "circulation/form";
    }

    @GetMapping("/edit/{id}")
    public String editRecordForm(@PathVariable Long id, Model model) {
        CirculationRecord record = circulationService.getRecordById(id);
        model.addAttribute("record", record);
        return "circulation/form";
    }

    @PostMapping("/save")
    public String saveRecord(@ModelAttribute CirculationRecord record) {
        circulationService.saveRecord(record);
        return "redirect:/circulation";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
        circulationService.deleteRecord(id);
        return "redirect:/circulation";
    }

    @GetMapping("/view/{id}")
    public String viewRecord(@PathVariable Long id, Model model) {
        CirculationRecord record = circulationService.getRecordById(id);
        model.addAttribute("record", record);
        return "circulation/view";
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("record", new CirculationRecord());
        return "circulation/checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute CirculationRecord record) {
        circulationService.checkoutBook(record);
        return "redirect:/circulation";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        circulationService.returnBook(id);
        return "redirect:/circulation";
    }

    // REST API endpoints
    @GetMapping("/api/records")
    @ResponseBody
    public List<CirculationRecord> getAllRecordsApi() {
        return circulationService.getAllRecords();
    }

    @GetMapping("/api/records/{id}")
    @ResponseBody
    public CirculationRecord getRecordByIdApi(@PathVariable Long id) {
        return circulationService.getRecordById(id);
    }

    @PostMapping("/api/records")
    @ResponseBody
    public CirculationRecord createRecordApi(@RequestBody CirculationRecord record) {
        return circulationService.saveRecord(record);
    }

    @PutMapping("/api/records/{id}")
    @ResponseBody
    public CirculationRecord updateRecordApi(@PathVariable Long id, @RequestBody CirculationRecord record) {
        record.setId(id);
        return circulationService.saveRecord(record);
    }

    @DeleteMapping("/api/records/{id}")
    @ResponseBody
    public void deleteRecordApi(@PathVariable Long id) {
        circulationService.deleteRecord(id);
    }

    @GetMapping("/api/checkout")
    @ResponseBody
    public CirculationRecord checkoutBookApi(@RequestParam Long bookId, @RequestParam Long patronId) {
        return circulationService.checkoutBook(bookId, patronId);
    }

    @PostMapping("/api/return/{id}")
    @ResponseBody
    public CirculationRecord returnBookApi(@PathVariable Long id) {
        return circulationService.returnBook(id);
    }

    @GetMapping("/api/overdue")
    @ResponseBody
    public List<CirculationRecord> getOverdueRecordsApi() {
        return circulationService.getOverdueRecords();
    }
} 