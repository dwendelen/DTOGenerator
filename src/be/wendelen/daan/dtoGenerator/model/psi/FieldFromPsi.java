package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Field;
import be.wendelen.daan.dtoGenerator.model.ast.Type;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifierListOwner;

public class FieldFromPsi extends ClassMemberFromPsi implements Field {
    private PsiField psiField;
    private TypeFromPsiFactory typeFromPsiFactory;
    private Type type = null;

    public FieldFromPsi(PsiField psiField, TypeFromPsiFactory typeFromPsiFactory) {
        this.psiField = psiField;
        this.typeFromPsiFactory = typeFromPsiFactory;
    }

    @Override
    protected PsiModifierListOwner getPsiModifierListOwner() {
        return psiField;
    }

    @Override
    public Type getReturnType() {
        if (type == null) {
            type = typeFromPsiFactory.create(psiField.getType());
        }
        return type;
    }

    @Override
    public String getAnnotations() {
        return null;
    }

    @Override
    public String getName() {
        return psiField.getName();
    }
}