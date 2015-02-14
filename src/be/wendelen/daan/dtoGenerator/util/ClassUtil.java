package be.wendelen.daan.dtoGenerator.util;

import com.intellij.openapi.util.text.StringUtil;
import be.wendelen.daan.dtoGenerator.model.ast.Class;

import java.util.List;

public class ClassUtil {
    public static String getInstanceName(Class aClass) {
        List<String> pieces = StringUtil.split(aClass.getName(), ".");
        return StringUtil.decapitalize(pieces.get(pieces.size() - 1));
    }
    public static void generateDeclaration(StringBuilder stringBuilder, Class aClass) {
        stringBuilder.append(aClass.getName());
        stringBuilder.append(" ");
        stringBuilder.append(getInstanceName(aClass));
        stringBuilder.append(" = ");
    }
    public static void generateInitialisation(StringBuilder stringBuilder, Class aClass) {
        generateDeclaration(stringBuilder, aClass);
        stringBuilder.append("new ");
        stringBuilder.append(aClass.getName());
        stringBuilder.append("();\n");
    }
}
