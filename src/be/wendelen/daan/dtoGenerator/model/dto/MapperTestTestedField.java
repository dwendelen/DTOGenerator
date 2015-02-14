package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.AccessModifier;
import be.wendelen.daan.dtoGenerator.model.ast.Field;
import be.wendelen.daan.dtoGenerator.model.ast.Type;

public class MapperTestTestedField implements Field {

    private Mapper mapper;

    public MapperTestTestedField(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Type getReturnType() {
        return mapper;
    }

    @Override
    public AccessModifier getAccessModifier() {
        return AccessModifier.PRIVATE;
    }

    @Override
    public String getAnnotations() {
        return "@mockit.Tested\n";
    }

    @Override
    public String getName() {
        return mapper.getInstanceName();
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
