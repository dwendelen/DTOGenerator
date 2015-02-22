package be.wendelen.daan.dtoGenerator.util;

import be.wendelen.daan.dtoGenerator.model.ast.Method;
import com.intellij.openapi.util.text.StringUtil;
import be.wendelen.daan.dtoGenerator.model.ast.Class;
import com.intellij.psi.PsiMethod;

import java.util.List;

public class ClassUtil {
    public static String getInstanceName(Class aClass) {
        List<String> pieces = StringUtil.split(aClass.getName(), ".");
        return StringUtil.decapitalize(pieces.get(pieces.size() - 1));
    }
    public static void generateDeclaration(StringBuilder stringBuilder, Class aClass) {
        stringBuilder.append(aClass.getQualifiedName());
        stringBuilder.append(" ");
        stringBuilder.append(getInstanceName(aClass));
        stringBuilder.append(" = ");
    }
    public static void generateInitialisation(StringBuilder stringBuilder, Class aClass) {
        generateDeclaration(stringBuilder, aClass);
        stringBuilder.append("new ");
        stringBuilder.append(aClass.getQualifiedName());
        stringBuilder.append("();\n");
    }

    public static String getFieldName(Method method) {
        String name = method.getName();

        if(name.startsWith("get")) {
            return name.substring(3);
        }

        if("boolean".equals(method.getReturnType().getQualifiedName())) {
            if(name.startsWith("is")) {
                return name.substring(2);
            }
        }

        return name;
    }

    public static boolean isGetter(PsiMethod method) {
        if(method.getReturnType() == null) {
            return false;
        }

        if("boolean".equals(method.getReturnType().getCanonicalText())) {
            return method.getName().startsWith("is");
        } else {
            return method.getName().startsWith("get");
        }
    }
}
