package be.wendelen.daan.dtoGenerator.model.dto;

import be.wendelen.daan.dtoGenerator.model.ast.*;

import java.util.LinkedList;
import java.util.List;

public class DTO extends AbstractClass {

    private List<DTOField> fields = new LinkedList<DTOField>();

    public DTO(String name, String pkg, List<Method> methods) {
        super(name, pkg);
        for (Method method : methods) {
            this.fields.add(new DTOField(method));
        }
    }

    @Override
    public List<Method> getMethods() {
        List<Method> res = new LinkedList<Method>();
        for (DTOField field : fields) {
            res.add(field.getGetter());
            res.add(field.getSetter());
        }
        return res;
    }

    @Override
    public List<DTOField> getFields() {
        return fields;
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
