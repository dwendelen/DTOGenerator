package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Type;
import com.intellij.psi.PsiType;

public class TypeFromPsi implements Type {

    private PsiType psiType;

    public TypeFromPsi(PsiType psiType) {
        this.psiType = psiType;
    }

    @Override
    public String getName() {
        return psiType.getCanonicalText();
    }
}
