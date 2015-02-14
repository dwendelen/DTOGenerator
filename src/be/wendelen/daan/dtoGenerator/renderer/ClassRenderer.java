package be.wendelen.daan.dtoGenerator.renderer;

import be.wendelen.daan.dtoGenerator.model.ast.*;

import java.util.List;

public class ClassRenderer extends ClassMemberRenderer {

    private FieldRenderer fieldRenderer;
    private MethodRenderer methodRenderer;

    public ClassRenderer(FieldRenderer fieldRenderer, MethodRenderer methodRenderer) {
        super(fieldRenderer.getTypeRenderer());

        if(methodRenderer.getTypeRenderer() != fieldRenderer.getTypeRenderer()) {
            throw new IllegalStateException("TypeRenderer in fieldrenderer and method" +
                    "renderer must be the same object.");
        }

        this.fieldRenderer = fieldRenderer;
        this.methodRenderer = methodRenderer;
    }

    public void renderDefinition(be.wendelen.daan.dtoGenerator.model.ast.Class aClass) {
        renderHeader(aClass);
        getStringBuilder().append(" {\n");

        boolean hasRenderedFields = renderFields(aClass.getFields());

        boolean newLineBeforeFirstMethod;
        if(hasRenderedFields) {
            newLineBeforeFirstMethod = true;
        } else {
            newLineBeforeFirstMethod = false;
        }

        renderMethods(aClass.getMethods(), newLineBeforeFirstMethod);

        renderFooter(getStringBuilder());
    }

    private boolean renderFields(List<? extends Field> fields) {
        boolean hasRenderedFields = false;
        for (Field field : fields) {
            fieldRenderer.renderDefinition(field);
            getStringBuilder().append("\n");
            hasRenderedFields = true;
        }

        return hasRenderedFields;
    }

    private void renderMethods(List<? extends Method> methods, boolean newLineBeforeFirstMethod) {
        boolean isFirstMethod = true;
        for (Method method : methods) {
            if(isFirstMethod) {
                renderFirstMethod(getStringBuilder(), newLineBeforeFirstMethod, method);
            } else {
                renderNotFirstMethod(getStringBuilder(), method);
            }
            getStringBuilder().append("\n");
        }
    }

    private void renderFirstMethod(StringBuilder stringBuilder, boolean newLineBeforeFirstMethod, Method method) {
        if(newLineBeforeFirstMethod) {
            stringBuilder.append("\n");
        }
        methodRenderer.renderDefinition(method);
    }

    private void renderNotFirstMethod(StringBuilder stringBuilder, Method method) {
        stringBuilder.append("\n");
        methodRenderer.renderDefinition(method);
    }

    private void renderFooter(StringBuilder stringBuilder) {
        stringBuilder.append("}\n");
    }
}
