package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.model.ast.*;
import be.wendelen.daan.dtoGenerator.util.ClassUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class MapMethod implements Method {

    private Class from;
    private DTO to;

    public MapMethod(Class from, DTO to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<Parameter> getParameters() {
        final String decapitalizedClassName = ClassUtil.getInstanceName(from);

        Parameter parameter = new Parameter() {
            @Override
            public Type getType() {
                return from;
            }
            @Override
            public String getName() {
                return decapitalizedClassName;
            }
        };

        List<Parameter> parameters = new LinkedList<Parameter>();
        parameters.add(parameter);

        return parameters;
    }

    @Override
    public void appendBody(StringBuilder stringBuilder) {

        String fromInstanceName = getParameters().get(0).getName();

        to.generateInitialisation(stringBuilder);

        for (DTOField field : to.getFields()) {
            stringBuilder.append(to.getInstanceName());
            stringBuilder.append(".");
            stringBuilder.append(field.getSetter().getName());
            stringBuilder.append("(");
            stringBuilder.append(fromInstanceName);
            stringBuilder.append(".");
            stringBuilder.append(field.getGetter().getName());
            stringBuilder.append("());\n");
        }
        stringBuilder.append("return ");
        stringBuilder.append(to.getInstanceName());
        stringBuilder.append(";");
    }

    @Override
    public Type getReturnType() {
        return to;
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
        return "map";
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
