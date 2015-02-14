package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Method;
import com.intellij.psi.PsiMethod;

public class MethodFromPsiFactory {
    public Method create(PsiMethod psiMethod) {
        ParameterFromPsiFactory parameterFromPsiFactory = new ParameterFromPsiFactory();
        TypeFromPsiFactory typeFromPsiFactory = new TypeFromPsiFactory();
        return new MethodFromPsi(psiMethod, parameterFromPsiFactory, typeFromPsiFactory);
    }
}
