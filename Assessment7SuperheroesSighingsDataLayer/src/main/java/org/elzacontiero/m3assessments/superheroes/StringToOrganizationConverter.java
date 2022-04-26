package org.elzacontiero.m3assessments.superheroes;

import org.elzacontiero.m3assessments.superheroes.dto.Organization;
import org.springframework.core.convert.converter.Converter;

public class StringToOrganizationConverter implements Converter<String, Organization> {

    @Override
    public Organization convert(String from) {
        Organization org = new Organization();
        org.setId(Long.parseLong(from));
        return org;
    }
}
