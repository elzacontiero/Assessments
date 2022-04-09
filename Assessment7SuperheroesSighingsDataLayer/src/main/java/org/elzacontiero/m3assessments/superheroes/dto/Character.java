package org.elzacontiero.m3assessments.superheroes.dto;

public class Character {
    long id;
    String name;
    String description;
    String superpower;

    String characterType;

    public Character() {}

    public Character(long id, String name, String description, String superpower, String characterType) {
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
}
