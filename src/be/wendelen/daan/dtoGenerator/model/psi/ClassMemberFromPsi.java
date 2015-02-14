package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.AccessModifier;
import be.wendelen.daan.dtoGenerator.model.ast.ClassMember;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiModifierListOwner;

public abstract class ClassMemberFromPsi implements ClassMember {

    protected abstract PsiModifierListOwner getPsiModifierListOwner();

    private PsiModifierList getModifierList() {
        return getPsiModifierListOwner().getModifierList();
    }

    @Override
    public AccessModifier getAccessModifier() {
        return AccessModifier.from(getModifierList());
    }

    @Override
    public boolean isStatic() {
        return getModifierList().hasModifierProperty("static");
    }

    @Override
    public boolean isFinal() {
        return getModifierList().hasModifierProperty("final");
    }

    @Override
    public String getAnnotations() {
        if(getModifierList().getAnnotations().length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (PsiAnnotation psiAnnotation : getModifierList().getAnnotations()) {
            sb.append("@");
            sb.append(psiAnnotation.getQualifiedName());
            sb.append("\n");
        }
        return sb.toString();
    }
}
