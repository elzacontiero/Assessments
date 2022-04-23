package org.elzacontiero.m3assessments.superheroes.controller;

import java.util.List;

import org.elzacontiero.m3assessments.superheroes.dto.Organization;
import org.elzacontiero.m3assessments.superheroes.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OrganizationsController {

    @Autowired
    OrganizationService service;

    @GetMapping("/orgs/all")
    public String orgsAll(Model model) {
        List<Organization> orgs = service.listAll();
        model.addAttribute("orgs", orgs);
        return "organizations";
    }

    @GetMapping("/orgs/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "forward:/orgs/all";
    }

    @GetMapping("/orgs/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Organization org = null;
        if (id==0) {
            org = new Organization();
        } else {
            org = service.getById(id);
        }
        model.addAttribute("org", org);
        return "orgedit";
    }

    @PostMapping("/orgs/edit")
    public String edit(@ModelAttribute Organization org, Model model) {
        Organization superCharFound = service.getById(org.getId());
        if (superCharFound == null) {
            service.insert(org);
        } else {
            service.update(org);
        }
        return "redirect:/orgs/all";
    }

}
