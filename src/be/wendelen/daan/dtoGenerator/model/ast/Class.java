package be.wendelen.daan.dtoGenerator.model.ast;

import be.wendelen.daan.dtoGenerator.model.dto.DTOField;

import java.util.List;

public interface Class extends ClassMember, Type {

    public List<? extends Method> getMethods();
    public List<? extends Field> getFields();

    public String getQualifiedName();
}