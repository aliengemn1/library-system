package com.ashehada.library.catalog.controller;

import com.ashehada.library.catalog.model.MarcRecord;
import com.ashehada.library.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    // Web UI endpoints
    @GetMapping
    public String index(Model model) {
        List<MarcRecord> records = catalogService.getAllRecords();
        model.addAttribute("records", records);
        return "catalog/index";
    }

    @GetMapping("/new")
    public String newRecordForm(Model model) {
        model.addAttribute("record", new MarcRecord());
        return "catalog/form";
    }

    @GetMapping("/edit/{id}")
    public String editRecordForm(@PathVariable Long id, Model model) {
        MarcRecord record = catalogService.getRecordById(id);
        model.addAttribute("record", record);
        return "catalog/form";
    }

    @PostMapping("/save")
    public String saveRecord(@ModelAttribute MarcRecord record) {
        catalogService.saveRecord(record);
        return "redirect:/catalog";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
        catalogService.deleteRecord(id);
        return "redirect:/catalog";
    }

    @GetMapping("/view/{id}")
    public String viewRecord(@PathVariable Long id, Model model) {
        MarcRecord record = catalogService.getRecordById(id);
        model.addAttribute("record", record);
        return "catalog/view";
    }

    // REST API endpoints
    @GetMapping("/api/records")
    @ResponseBody
    public List<MarcRecord> getAllRecordsApi() {
        return catalogService.getAllRecords();
    }

    @GetMapping("/api/records/{id}")
    @ResponseBody
    public MarcRecord getRecordByIdApi(@PathVariable Long id) {
        return catalogService.getRecordById(id);
    }

    @PostMapping("/api/records")
    @ResponseBody
    public MarcRecord createRecordApi(@RequestBody MarcRecord record) {
        return catalogService.saveRecord(record);
    }

    @PutMapping("/api/records/{id}")
    @ResponseBody
    public MarcRecord updateRecordApi(@PathVariable Long id, @RequestBody MarcRecord record) {
        record.setId(id);
        return catalogService.saveRecord(record);
    }

    @DeleteMapping("/api/records/{id}")
    @ResponseBody
    public void deleteRecordApi(@PathVariable Long id) {
        catalogService.deleteRecord(id);
    }

    @GetMapping("/api/search")
    @ResponseBody
    public List<MarcRecord> searchRecordsApi(@RequestParam String query) {
        return catalogService.searchRecords(query);
    }
} 