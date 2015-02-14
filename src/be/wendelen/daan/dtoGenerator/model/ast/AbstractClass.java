package be.wendelen.daan.dtoGenerator.model.ast;

import be.wendelen.daan.dtoGenerator.util.ClassUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.List;

public abstract class AbstractClass implements Class {

    private String name;

    protected AbstractClass(String name) {
        this.name = name;
    }

    @Override
    public Type getReturnType() {
        return new ConcreteType("class");
    }

    @Override
    public String getName() {
        return name;
    }

    public String getInstanceName() {
        return ClassUtil.getInstanceName(this);
    }

    public boolean isBoolean() {
        return false;
    }

    public void generateInitialisation(StringBuilder stringBuilder) {
        ClassUtil.generateInitialisation(stringBuilder, this);
    }
}
