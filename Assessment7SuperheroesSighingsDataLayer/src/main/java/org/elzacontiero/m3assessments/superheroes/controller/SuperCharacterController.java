package org.elzacontiero.m3assessments.superheroes.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.elzacontiero.m3assessments.superheroes.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SuperCharacterController {

    @Autowired
    CharacterService charService;

    @GetMapping("test")
    public String test(SuperCharacter character) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(Date.from(Instant.now()));
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Oi mundo!");

        return "welcome"; //view
    }


    @GetMapping("/super/all")
    public String superAll(Model model) {
        List<SuperCharacter> chars = charService.listAll();
        model.addAttribute("chars", chars);
        model.addAttribute("size", chars.size());
        return "superall";
    }


    @GetMapping("/super/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        charService.delete(id);
        return "forward:/super/all";
    }

    @GetMapping("/super/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        SuperCharacter superChar = null;
        if (id==0) {
            superChar = new SuperCharacter();
        } else {
            superChar = charService.getById(id);
        }
        model.addAttribute("superChar", superChar);
        return "superedit";
    }

    @PostMapping("/super")
    public String edit(@ModelAttribute SuperCharacter superChar, Model model) {
        System.out.println("POST: got " + superChar);
        SuperCharacter superCharFound = charService.getById(superChar.getId());
        if (superCharFound == null) {
            charService.insert(superChar);
        } else {
            // Handle update
            System.out.println("POST: found id -> update");
            charService.update(superChar);
        }

        return "redirect:/super/all";
    }
}
