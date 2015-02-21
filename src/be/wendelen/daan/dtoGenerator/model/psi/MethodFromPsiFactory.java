package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Method;
import com.intellij.psi.PsiMethod;

import java.util.LinkedList;
import java.util.List;

public class MethodFromPsiFactory {
    public Method create(PsiMethod psiMethod) {
        ParameterFromPsiFactory parameterFromPsiFactory = new ParameterFromPsiFactory();
        TypeFromPsiFactory typeFromPsiFactory = new TypeFromPsiFactory();
        return new MethodFromPsi(psiMethod, parameterFromPsiFactory, typeFromPsiFactory);
    }

    public List<Method> createList(List<PsiMethod> methods) {
        List<Method> result = new LinkedList<Method>();
        for (PsiMethod method : methods) {
            result.add(create(method));
        }
        return result;
    }
}
