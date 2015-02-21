package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.*;
import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.model.dto.DTOField;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierListOwner;

import java.util.LinkedList;
import java.util.List;

public class ClassFromPsi extends ClassMemberFromPsi implements Class {

    private PsiClass psiClass;
    private MethodFromPsiFactory methodFromPsiFactory;
    private FieldFromPsiFactory fieldFromPsiFactory;

    private List<Method> methods = null;
    private List<Field> fields = null;
    private Type type = new ConcreteType("class");

    @Override
    protected PsiModifierListOwner getPsiModifierListOwner() {
        return psiClass;
    }

    public ClassFromPsi(PsiClass psiClass,
                        MethodFromPsiFactory methodFromPsiFactory,
                        FieldFromPsiFactory fieldFromPsiFactory) {
        this.psiClass = psiClass;
        this.methodFromPsiFactory = methodFromPsiFactory;
        this.fieldFromPsiFactory = fieldFromPsiFactory;
    }

    @Override
    public List<Method> getMethods() {
        if(methods == null) {
            methods = new LinkedList<Method>();
            for (PsiMethod psiMethod : psiClass.getMethods()) {
                methods.add(methodFromPsiFactory.create(psiMethod));
            }
        }

        return methods;
    }

    @Override
    public List<Field> getFields() {
        if(fields == null) {
            fields = new LinkedList<Field>();

            for (PsiField psiField : psiClass.getFields()) {
                fields.add(fieldFromPsiFactory.create(psiField));
            }
        }

        return fields;
    }

    @Override
    public Type getReturnType() {
        return type;
    }

    @Override
    public String getName() {
        return psiClass.getName();
    }

    @Override
    public String getQualifiedName() {
        return psiClass.getQualifiedName();
    }
}
