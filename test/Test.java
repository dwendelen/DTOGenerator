import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.model.ast.Parameter;
import be.wendelen.daan.dtoGenerator.model.psi.ClassFromPsiFactory;
import be.wendelen.daan.dtoGenerator.renderer.*;
import com.intellij.openapi.command.impl.DummyProject;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;

public class Test {

    @org.junit.Test
    public void test() {
        PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(DummyProject.getInstance());

        PsiClass aClass = elementFactory.createClass(getClassCode());

        Class aClass1 = new ClassFromPsiFactory().create(aClass);

        StringBuilder stringBuilder = new StringBuilder();

        TypeRenderer typeRenderer = new TypeRenderer(stringBuilder);
        FieldRenderer fieldRenderer = new FieldRenderer(typeRenderer);
        ParameterRenderer parameterRenderer = new ParameterRenderer(typeRenderer);
        MethodRenderer methodRenderer = new MethodRenderer(parameterRenderer);
        ClassRenderer classRenderer = new ClassRenderer(fieldRenderer, methodRenderer);

        classRenderer.renderDefinition(aClass1);

        System.out.println(stringBuilder.toString());
    }

    private String getClassCode() {
        return "public class Test {\n" +
                "\n" +
                "    @org.junit.Test\n" +
                "    public String testMethode(String a) {\n" +
                "        Bu b = new Bu(a);\n" +
                "        a = \"ae\" + a;\n" +
                "        return b.getO() + a;\n" +
                "    }\n" +
                "\n" +
                "    private static class Bu() {\n" +
                "        private String o;\n" +
                "        public Bu(o) {\n" +
                "            this.o = o;\n" +
                "        }\n" +
                "        public String getO() { return o;}\n" +
                "    }\n" +
                "}";
    }
}