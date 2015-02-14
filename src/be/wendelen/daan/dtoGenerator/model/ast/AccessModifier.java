package be.wendelen.daan.dtoGenerator.model.ast;

import com.intellij.psi.PsiModifierList;

public enum AccessModifier {
    PUBLIC,
    PROTECTED,
    PRIVATE,
    NONE {
        @Override
        protected String getRenderedName() {
            return null;
        }
    };

    protected String getRenderedName() {
        return name().toLowerCase();
    }

    public boolean renderUsage(StringBuilder stringBuilder) {
        if(getRenderedName() == null) {
            return false;
        }

        stringBuilder.append(getRenderedName());
        return true;
    }

    public static AccessModifier from(PsiModifierList modifierList) {
        for(AccessModifier accessModifier: AccessModifier.values()) {
            if(accessModifier.getRenderedName() == null) {
                continue;
            }
            if(modifierList.hasExplicitModifier(accessModifier.getRenderedName())) {
                return  accessModifier;
            }
        }

        return NONE;
    }
}
