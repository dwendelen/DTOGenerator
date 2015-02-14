package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Class;
import com.intellij.psi.PsiClass;

public class ClassFromPsiFactory {
    public Class create(PsiClass psiClass) {
        MethodFromPsiFactory methodFromPsiFactory = new MethodFromPsiFactory();
        FieldFromPsiFactory fieldFromPsiFactory = new FieldFromPsiFactory();
        return new ClassFromPsi(psiClass, methodFromPsiFactory, fieldFromPsiFactory);
    }
}
