package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Parameter;
import be.wendelen.daan.dtoGenerator.model.ast.Type;
import com.intellij.psi.PsiParameter;

public class ParameterFromPsi implements Parameter {

    private PsiParameter psiParameter;
    private TypeFromPsiFactory typeFromPsiFactory;

    private Type type;

    public ParameterFromPsi(PsiParameter psiParameter, TypeFromPsiFactory typeFromPsiFactory) {
        this.psiParameter = psiParameter;
        this.typeFromPsiFactory = typeFromPsiFactory;
    }

    @Override
    public Type getType() {
        if (type == null) {
            type = typeFromPsiFactory.create(psiParameter.getType());
        }
        return type;
    }

    @Override
    public String getName() {
        return psiParameter.getName();
    }
}
