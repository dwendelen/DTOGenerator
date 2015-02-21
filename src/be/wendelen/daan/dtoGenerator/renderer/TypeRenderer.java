package be.wendelen.daan.dtoGenerator.renderer;

import be.wendelen.daan.dtoGenerator.model.ast.Type;

public class TypeRenderer {
    private StringBuilder stringBuilder;

    public TypeRenderer(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public void render(Type type) {
        stringBuilder.append(type.getQualifiedName());
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }
}
