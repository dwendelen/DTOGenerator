package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.*;
import be.wendelen.daan.dtoGenerator.model.ast.Class;

import java.util.LinkedList;
import java.util.List;

public class Mapper extends AbstractClass {
    private Class from;
    private DTO to;

    public Mapper(String name, String pkg, Class from, DTO to) {
        super(name, pkg);
        this.from = from;
        this.to = to;
    }

    public Class getFrom() {
        return from;
    }

    public DTO getTo() {
        return to;
    }

    @Override
    public List<Method> getMethods() {
        MapMethod mapMethod = new MapMethod(from, to);

        List<Method> mapMethods = new LinkedList<Method>();
        mapMethods.add(mapMethod);

        return mapMethods;
    }

    @Override
    public List<Field> getFields() {
        return new LinkedList<Field>();
    }

    @Override
    public AccessModifier getAccessModifier() {
        return AccessModifier.PUBLIC;
    }

    @Override
    public String getAnnotations() {
        return "@org.springframework.stereotype.Component\n";
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}