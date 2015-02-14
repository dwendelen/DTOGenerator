package be.wendelen.daan.dtoGenerator.model.ast;

import java.util.List;

public interface Method extends ClassMember {
    public List<? extends Parameter> getParameters();
    public void appendBody(StringBuilder stringBuilder);
}