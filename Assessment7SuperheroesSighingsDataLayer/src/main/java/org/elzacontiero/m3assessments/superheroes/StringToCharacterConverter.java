package org.elzacontiero.m3assessments.superheroes;

import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.springframework.core.convert.converter.Converter;

public class StringToCharacterConverter implements Converter<String, SuperCharacter> {

    @Override
    public SuperCharacter convert(String from) {
        System.out.println("StringToCharacterConverter.convert: " + from);
        SuperCharacter org = new SuperCharacter();
        org.setId(Long.parseLong(from));
        return org;
    }
}
