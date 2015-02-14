package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Parameter;
import com.intellij.psi.PsiParameter;


public class ParameterFromPsiFactory {
    public Parameter create(PsiParameter psiParameter) {
        TypeFromPsiFactory typeFromPsiFactory = new TypeFromPsiFactory();
        return new ParameterFromPsi(psiParameter, typeFromPsiFactory);
    }
}
