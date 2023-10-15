package com.example.AOManager.controller;

import com.example.AOManager.request.CreateImportFormRequest;
import com.example.AOManager.response.ApiResponse;
import com.example.AOManager.service.ImportFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/import-forms")
public class ImportFormController {

    @Autowired
    ImportFormService importFormService;

    @GetMapping("/{id}")
    public ApiResponse<?> getImportForm(@PathVariable String id) {return this.importFormService.getImportForm(id);}

    @GetMapping
    public ApiResponse<?> getImportFormList(@RequestParam int page, @RequestParam int limit) {return this.importFormService.getImportFormList(page, limit);}

    @PostMapping
    public ApiResponse<?> createImportForm(@Validated @RequestBody CreateImportFormRequest createImportFormRequest) {return this.importFormService.createImportForm(createImportFormRequest);}
}
