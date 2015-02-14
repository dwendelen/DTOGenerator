package be.wendelen.daan.dtoGenerator.renderer;

import be.wendelen.daan.dtoGenerator.model.ast.Parameter;

public class ParameterRenderer {

    private TypeRenderer typeRenderer;

    public ParameterRenderer(TypeRenderer typeRenderer) {
        this.typeRenderer = typeRenderer;
    }

    public TypeRenderer getTypeRenderer() {
        return typeRenderer;
    }

    protected StringBuilder getStringBuilder() {
        return typeRenderer.getStringBuilder();
    }

    public void renderUsage(Parameter parameter) {
        getStringBuilder().append(parameter.getName());
    }

    public void renderDefinition(Parameter parameter) {
        typeRenderer.render(parameter.getType());
        getStringBuilder().append(" ");
        getStringBuilder().append(parameter.getName());
    }


}
