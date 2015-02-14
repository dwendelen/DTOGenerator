package be.wendelen.daan.dtoGenerator.renderer;

import be.wendelen.daan.dtoGenerator.model.ast.ClassMember;

public abstract class ClassMemberRenderer {
    private TypeRenderer typeRenderer;

    public TypeRenderer getTypeRenderer() {
        return typeRenderer;
    }

    public StringBuilder getStringBuilder() {
        return typeRenderer.getStringBuilder();
    }

    protected ClassMemberRenderer(TypeRenderer typeRenderer) {
        this.typeRenderer = typeRenderer;
    }

    protected void renderHeader(ClassMember classMember){
        String annotations = classMember.getAnnotations();
        if(annotations != null) {
            getStringBuilder().append(annotations);
        }
        boolean hasModifier = classMember.getAccessModifier().renderUsage(getStringBuilder());
        if(hasModifier) {
            getStringBuilder().append(" ");
        }
        if(classMember.isStatic()) {
            getStringBuilder().append("static ");
        }
        if(classMember.isFinal()) {
            getStringBuilder().append("final ");
        }
        if(classMember.getReturnType() != null) {
            typeRenderer.render(classMember.getReturnType());
            getStringBuilder().append(" ");
        }
        getStringBuilder().append(classMember.getName());
    }
}
