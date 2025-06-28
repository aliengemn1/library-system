package com.ashehada.library.integration.controller;

import com.ashehada.library.integration.model.IntegrationConfig;
import com.ashehada.library.integration.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/integration")
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    // Web UI endpoints
    @GetMapping
    public String index(Model model) {
        List<IntegrationConfig> configs = integrationService.getAllConfigs();
        model.addAttribute("configs", configs);
        return "integration/index";
    }

    @GetMapping("/new")
    public String newConfigForm(Model model) {
        model.addAttribute("config", new IntegrationConfig());
        return "integration/form";
    }

    @GetMapping("/edit/{id}")
    public String editConfigForm(@PathVariable Long id, Model model) {
        IntegrationConfig config = integrationService.getConfigById(id);
        model.addAttribute("config", config);
        return "integration/form";
    }

    @PostMapping("/save")
    public String saveConfig(@ModelAttribute IntegrationConfig config) {
        integrationService.saveConfig(config);
        return "redirect:/integration";
    }

    @GetMapping("/delete/{id}")
    public String deleteConfig(@PathVariable Long id) {
        integrationService.deleteConfig(id);
        return "redirect:/integration";
    }

    @GetMapping("/view/{id}")
    public String viewConfig(@PathVariable Long id, Model model) {
        IntegrationConfig config = integrationService.getConfigById(id);
        model.addAttribute("config", config);
        return "integration/view";
    }

    @GetMapping("/test/{id}")
    public String testConnection(@PathVariable Long id) {
        integrationService.testConnection(id);
        return "redirect:/integration";
    }

    // REST API endpoints
    @GetMapping("/api/configs")
    @ResponseBody
    public List<IntegrationConfig> getAllConfigsApi() {
        return integrationService.getAllConfigs();
    }

    @GetMapping("/api/configs/{id}")
    @ResponseBody
    public IntegrationConfig getConfigByIdApi(@PathVariable Long id) {
        return integrationService.getConfigById(id);
    }

    @PostMapping("/api/configs")
    @ResponseBody
    public IntegrationConfig createConfigApi(@RequestBody IntegrationConfig config) {
        return integrationService.saveConfig(config);
    }

    @PutMapping("/api/configs/{id}")
    @ResponseBody
    public IntegrationConfig updateConfigApi(@PathVariable Long id, @RequestBody IntegrationConfig config) {
        config.setId(id);
        return integrationService.saveConfig(config);
    }

    @DeleteMapping("/api/configs/{id}")
    @ResponseBody
    public void deleteConfigApi(@PathVariable Long id) {
        integrationService.deleteConfig(id);
    }

    @PostMapping("/api/configs/{id}/test")
    @ResponseBody
    public boolean testConnectionApi(@PathVariable Long id) {
        return integrationService.testConnection(id);
    }

    @GetMapping("/api/configs/type/{type}")
    @ResponseBody
    public List<IntegrationConfig> getConfigsByTypeApi(@PathVariable String type) {
        return integrationService.getConfigsByType(type);
    }

    @GetMapping("/api/configs/status/{status}")
    @ResponseBody
    public List<IntegrationConfig> getConfigsByStatusApi(@PathVariable String status) {
        return integrationService.getConfigsByStatus(status);
    }
} 