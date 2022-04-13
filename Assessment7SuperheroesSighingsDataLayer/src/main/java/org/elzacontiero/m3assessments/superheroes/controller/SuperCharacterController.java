package org.elzacontiero.m3assessments.superheroes.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.elzacontiero.m3assessments.superheroes.dao.CharacterDao;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.elzacontiero.m3assessments.superheroes.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        SuperCharacter superChar = charService.getById(id);
        model.addAttribute("superChar", superChar);
        return "superedit";
    }

}
