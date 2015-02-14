package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.AccessModifier;
import be.wendelen.daan.dtoGenerator.model.ast.Method;
import be.wendelen.daan.dtoGenerator.model.ast.Parameter;
import be.wendelen.daan.dtoGenerator.model.ast.Type;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiStatement;

import java.util.LinkedList;
import java.util.List;

public class MethodFromPsi extends ClassMemberFromPsi implements Method {

    private PsiMethod psiMethod;
    private ParameterFromPsiFactory parameterFromPsiFactory;
    private TypeFromPsiFactory typeFromPsiFactory;

    private Type type = null;
    private List<Parameter> parameters = null;

    @Override
    protected PsiModifierListOwner getPsiModifierListOwner() {
        return psiMethod;
    }

    public MethodFromPsi(PsiMethod psiMethod,
                         ParameterFromPsiFactory parameterFromPsiFactory,
                         TypeFromPsiFactory typeFromPsiFactory) {
        this.psiMethod = psiMethod;
        this.parameterFromPsiFactory = parameterFromPsiFactory;
        this.typeFromPsiFactory = typeFromPsiFactory;
    }

    @Override
    public List<Parameter> getParameters() {
        if(parameters == null) {
            parameters = new LinkedList<Parameter>();

            for (PsiParameter psiTypeParameter : psiMethod.getParameterList().getParameters()) {
                Parameter parameter = parameterFromPsiFactory.create(psiTypeParameter);
                parameters.add(parameter);
            }
        }

        return parameters;
    }

    @Override
    public void appendBody(StringBuilder stringBuilder) {
        for (PsiStatement psiStatement : psiMethod.getBody().getStatements()) {
            stringBuilder.append(psiStatement.getText());
            stringBuilder.append("\n");
        }
    }

    @Override
    public Type getReturnType() {
        if(type == null) {
            type = typeFromPsiFactory.create(psiMethod.getReturnType());
        }
        return type;
    }

    @Override
    public String getName() {
        return psiMethod.getName();
    }
}
