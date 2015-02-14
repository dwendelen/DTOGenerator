package be.wendelen.daan.dtoGenerator.model.ast;

import com.intellij.openapi.util.text.StringUtil;

import java.util.LinkedList;
import java.util.List;

public class PublicSetter implements Method {

    private Field field;

    public PublicSetter(Field field) {
        this.field = field;
    }

    @Override
    public List<Parameter> getParameters() {
        Parameter parameter = new Parameter() {
            @Override
            public Type getType() {
                return field.getReturnType();
            }
            @Override
            public String getName() {
                return field.getName();
            }
        };

        List<Parameter> parameters = new LinkedList<Parameter>();
        parameters.add(parameter);

        return parameters;
    }

    @Override
    public void appendBody(StringBuilder stringBuilder) {
        stringBuilder.append("this.");
        stringBuilder.append(field.getName());
        stringBuilder.append(" = ");
        stringBuilder.append(field.getName());
        stringBuilder.append(";");
    }

    @Override
    public ConcreteType getReturnType() {
        return new ConcreteType("void");
    }

    @Override
    public AccessModifier getAccessModifier() {
        return AccessModifier.PUBLIC;
    }

    @Override
    public String getAnnotations() {
        return null;
    }

    @Override
    public String getName() {
        String capitalizedFieldName = StringUtil.capitalize(field.getName());
        return "set" + capitalizedFieldName;
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
