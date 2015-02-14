package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.*;
import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.util.ClassUtil;

import java.util.LinkedList;
import java.util.List;

public class MapperTestMethod implements Method {
    private Mapper mapperToTest;
    private MapperTestTestedField mapperTestTestedField;

    public MapperTestMethod(Mapper mapperToTest, MapperTestTestedField mapperTestTestedField) {
        this.mapperToTest = mapperToTest;
        this.mapperTestTestedField = mapperTestTestedField;
    }

    @Override
    public List<? extends Parameter> getParameters() {
        return new LinkedList<Parameter>();
    }

    @Override
    public void appendBody(StringBuilder stringBuilder) {
        Class from = mapperToTest.getFrom();
        DTO to = mapperToTest.getTo();
        stringBuilder.append("org.assertj.core.api.Assertions.fail(\"Not implemented\");\n\n");
        stringBuilder.append("//TODO initialize class\n");
        ClassUtil.generateInitialisation(stringBuilder, from);
        stringBuilder.append("\n");

        ClassUtil.generateDeclaration(stringBuilder, to);
        stringBuilder.append(mapperTestTestedField.getName());
        stringBuilder.append(".map(");
        stringBuilder.append(ClassUtil.getInstanceName(from));
        stringBuilder.append("); \n\n");

        stringBuilder.append("//TODO fillIn");
        for (DTOField dtoField : to.getFields()) {
            stringBuilder.append("\norg.assertj.core.api.Assertions.assertThat(");
            stringBuilder.append(to.getInstanceName());
            stringBuilder.append(".");
            stringBuilder.append(dtoField.getGetter().getName());
            stringBuilder.append("()).isEqualTo(null);");
        }
    }

    @Override
    public Type getReturnType() {
        return new ConcreteType("void");
    }

    @Override
    public AccessModifier getAccessModifier() {
        return AccessModifier.PUBLIC;
    }

    @Override
    public String getAnnotations() {
        return "@org.junit.Test\n";
    }

    @Override
    public String getName() {
        return "testMap";
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
