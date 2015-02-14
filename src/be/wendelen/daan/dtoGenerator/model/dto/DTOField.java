package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.*;

public class DTOField implements Field {
    private Field field;
    private Method getter;
    private Method setter;

    public DTOField(Field field) {
        this.field = field;
        this.getter = new PublicGetter(field);
        this.setter = new PublicSetter(field);
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

    @Override
    public Type getReturnType() {
        return field.getReturnType();
    }

    @Override
    public AccessModifier getAccessModifier() {
        return field.getAccessModifier();
    }

    @Override
    public String getAnnotations() {
        return null;
    }

    @Override
    public String getName() {
        return field.getName();
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
