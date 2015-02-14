package be.wendelen.daan.dtoGenerator.renderer;

import be.wendelen.daan.dtoGenerator.model.ast.Field;

public class FieldRenderer extends ClassMemberRenderer {
    public FieldRenderer(TypeRenderer typeRenderer) {
        super(typeRenderer);
    }

    public void renderDefinition(Field field) {
        renderHeader(field);
        getStringBuilder().append(";");
    }
}
