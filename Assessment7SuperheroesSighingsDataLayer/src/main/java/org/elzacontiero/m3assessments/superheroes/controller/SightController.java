package org.elzacontiero.m3assessments.superheroes.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elzacontiero.m3assessments.superheroes.dto.Recording;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.elzacontiero.m3assessments.superheroes.service.CharacterService;
import org.elzacontiero.m3assessments.superheroes.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SightController {

    @Autowired
    SightService service;

    @Autowired
    CharacterService characterService;

    @GetMapping("/sight/all")
    public String orgsAll(Model model) {
        List<Recording> recs = service.listAll();
        List<SuperCharacter> allCharacters = characterService.listAll();
        Map<Long,String> characterIdToName = new HashMap<>();
        for (SuperCharacter ch : allCharacters) {
            characterIdToName.put(ch.getId(), ch.getName());
        }

        model.addAttribute("recs", recs);
        model.addAttribute("characters", characterIdToName);
        return "sightall";
    }

    @GetMapping("/sight/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "forward:/sight/all";
    }

    @GetMapping("/sight/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Recording rec = null;
        if (id==0) {
            rec = new Recording();
        } else {
            rec = service.getById(id);
        }
        System.out.println("/sight/edit/{id}: id="+id + " rec="+rec);
        List<SuperCharacter> allChars = characterService.listAll();
        model.addAttribute("rec", rec);
        model.addAttribute("chars", allChars);
        return "sightedit";
    }

    @PostMapping("/sight/edit")
    public String edit(@ModelAttribute Recording newRec, Model model) {
        System.out.println("POST REC="+newRec);
        Recording rec = service.getById(newRec.getId());
        if (rec == null) {
            System.out.println("POST REC: rec==null, therefore, insert.");
            newRec.setTimeOfSight(Timestamp.from(Instant.now()));
            service.insert(newRec);
        } else {
            System.out.println("POST REC: rec==" + rec + ", therefore, update.");
            service.update(newRec);
        }
        return "redirect:/sight/all";
    }

}
