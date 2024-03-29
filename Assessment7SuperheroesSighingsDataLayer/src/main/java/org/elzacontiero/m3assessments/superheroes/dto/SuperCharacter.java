package org.elzacontiero.m3assessments.superheroes.dto;

import java.util.List;

public class SuperCharacter {
    long id;
    String name;
    String description;
    String superpower;

    String characterType;

    List<Organization> organizations;

    public SuperCharacter() {}

    public SuperCharacter(long id, String name, String description, String superpower, String characterType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.superpower = superpower;
        this.characterType = characterType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public Boolean belongsTo(Organization someOrg) {
        for (Organization org : organizations) {
            if (org.getId() == someOrg.getId()) {
                System.out.println("Character.belongsTo: id="+org.getId());
                return Boolean.valueOf(true);
            }
        }
        return Boolean.valueOf(false);
    }

    @Override
    public String toString() {
        return "Character [characterType=" + characterType + ", description=" + description + ", id=" + id + ", name="
                + name + ", superpower=" + superpower + "]";
    }
}
