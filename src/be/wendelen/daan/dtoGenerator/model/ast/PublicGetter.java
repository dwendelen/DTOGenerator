package be.wendelen.daan.dtoGenerator.model.ast;

import com.intellij.openapi.util.text.StringUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PublicGetter implements Method {

    private Field field;

    public PublicGetter(Field field) {
        this.field = field;
    }

    @Override
    public List<Parameter> getParameters() {
        return new ArrayList<Parameter>();
    }

    @Override
    public void appendBody(StringBuilder stringBuilder) {
        stringBuilder.append("return this.");
        stringBuilder.append(field.getName());
        stringBuilder.append(";");
    }

    @Override
    public Type getReturnType() {
        return field.getReturnType();
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
        if("boolean".equals(field.getReturnType().getName())) {
            return "is" + capitalizedFieldName;
        } else {
            return "get" + capitalizedFieldName;
        }
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
