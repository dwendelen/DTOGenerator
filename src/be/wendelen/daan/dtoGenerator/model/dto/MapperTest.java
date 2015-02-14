package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.AbstractClass;
import be.wendelen.daan.dtoGenerator.model.ast.AccessModifier;
import be.wendelen.daan.dtoGenerator.model.ast.Method;

import java.util.LinkedList;
import java.util.List;

public class MapperTest extends AbstractClass {
    private Mapper mapper;

    public MapperTest(String name, Mapper mapper) {
        super(name);
        this.mapper = mapper;
    }

    @Override
    public List<? extends Method> getMethods() {
        List<MapperTestMethod> res = new LinkedList<MapperTestMethod>();
        res.add(new MapperTestMethod(mapper, getFields().get(0)));
        return res;
    }

    @Override
    public List<MapperTestTestedField> getFields() {
        List<MapperTestTestedField> res = new LinkedList<MapperTestTestedField>();
        res.add(new MapperTestTestedField(mapper));
        return res;
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
    public boolean isStatic() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
