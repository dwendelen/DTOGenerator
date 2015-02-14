package be.wendelen.daan.dtoGenerator.model.ast;


public class ConcreteType implements Type {
    private String name;

    public ConcreteType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}