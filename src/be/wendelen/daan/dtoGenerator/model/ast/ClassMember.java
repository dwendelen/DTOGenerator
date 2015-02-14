package be.wendelen.daan.dtoGenerator.model.ast;


public interface ClassMember {

    public abstract Type getReturnType();

    public abstract AccessModifier getAccessModifier();

    public abstract String getAnnotations();

    public abstract String getName();

    public abstract boolean isStatic();

    public abstract boolean isFinal();
}