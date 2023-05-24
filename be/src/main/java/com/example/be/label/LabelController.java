package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
import com.example.be.label.dto.LabelUpdateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabelController {

    public final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping("/api/label")
    public String createLabel(@ModelAttribute LabelCreateFormDTO labelCreateFormDTO) {
        if (labelService.createLabel(labelCreateFormDTO)) {
            return "fail";
        }
        return "ok";
    }

    @PutMapping("/api/label")
    public String updateLabel(@ModelAttribute LabelUpdateFormDTO labelUpdateFormDTO) {
        if (labelService.updateLabel(labelUpdateFormDTO)) {
            return "fail";
        }
        return "ok";
    }

}
