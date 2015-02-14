package be.wendelen.daan.dtoGenerator.model.psi;

import be.wendelen.daan.dtoGenerator.model.ast.Field;
import com.intellij.psi.PsiField;

import java.util.LinkedList;
import java.util.List;

public class FieldFromPsiFactory {
    public Field create(PsiField psiField) {
        TypeFromPsiFactory typeFromPsiFactory = new TypeFromPsiFactory();
        return new FieldFromPsi(psiField, typeFromPsiFactory);
    }

    public List<Field> createList(Iterable<PsiField> psiFields) {
        List<Field> fields = new LinkedList<Field>();

        for (PsiField psiField : psiFields) {
            fields.add(create(psiField));
        }

        return fields;
    }
}
