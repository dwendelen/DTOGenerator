package be.wendelen.daan.dtoGenerator.renderer;

import be.wendelen.daan.dtoGenerator.model.ast.*;

public class Renderer {
    public String renderClass(be.wendelen.daan.dtoGenerator.model.ast.Class aClass) {
        StringBuilder stringBuilder = new StringBuilder();

        TypeRenderer typeRenderer = new TypeRenderer(stringBuilder);
        FieldRenderer fieldRenderer = new FieldRenderer(typeRenderer);
        ParameterRenderer parameterRenderer = new ParameterRenderer(typeRenderer);
        MethodRenderer methodRenderer = new MethodRenderer(parameterRenderer);
        ClassRenderer classRenderer = new ClassRenderer(fieldRenderer, methodRenderer);

        classRenderer.renderDefinition(aClass);

        return stringBuilder.toString();
    }
}
