package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.*;
import be.wendelen.daan.dtoGenerator.util.ClassUtil;

public class DTOField implements Field {
    private Method method;
    private Method getter;
    private Method setter;

    public DTOField(Method method) {
        this.method = method;
        this.getter = new PublicGetter(this);
        this.setter = new PublicSetter(this);
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

    @Override
    public Type getReturnType() {
        return method.getReturnType();
    }

    @Override
    public AccessModifier getAccessModifier() {
        return AccessModifier.PRIVATE;
    }

    @Override
    public String getAnnotations() {
        return null;
    }

    @Override
    public String getName() {
        return ClassUtil.getFieldName(method);
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    public Method getCorrespondingMethod() {
        return method;
    }
}
