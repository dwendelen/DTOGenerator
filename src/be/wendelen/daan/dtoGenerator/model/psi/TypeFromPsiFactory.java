package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Type;
import com.intellij.psi.PsiType;

public class TypeFromPsiFactory {
    public Type create(PsiType psiType) {
        if(psiType == null) {
            return null;
        }
        return new TypeFromPsi(psiType);
    }
}
