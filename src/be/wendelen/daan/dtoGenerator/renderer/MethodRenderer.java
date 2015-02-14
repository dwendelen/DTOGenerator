package be.wendelen.daan.dtoGenerator.renderer;

import be.wendelen.daan.dtoGenerator.model.ast.Method;
import be.wendelen.daan.dtoGenerator.model.ast.Parameter;

public class MethodRenderer extends ClassMemberRenderer {
    private ParameterRenderer parameterRenderer;

    public MethodRenderer(ParameterRenderer parameterRenderer) {
        super(parameterRenderer.getTypeRenderer());
        this.parameterRenderer = parameterRenderer;
    }

    public void renderDefinition(Method method) {
        renderHeader(method);
        getStringBuilder().append("(");
        boolean isFirst = true;
        for (Parameter parameter : method.getParameters()) {
            if(!isFirst) {
                getStringBuilder().append(", ");
            } else {
                isFirst = false;
            }
            parameterRenderer.renderDefinition(parameter);
        }
        getStringBuilder().append(") {\n");
        method.appendBody(getStringBuilder());
        getStringBuilder().append("\n}");
    }
}