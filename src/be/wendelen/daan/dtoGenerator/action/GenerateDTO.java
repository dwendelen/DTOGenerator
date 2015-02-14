package be.wendelen.daan.dtoGenerator.action;

import be.wendelen.daan.dtoGenerator.generator.GenerationConfiguration;
import be.wendelen.daan.dtoGenerator.generator.Generator;
import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.model.psi.ClassFromPsiFactory;
import be.wendelen.daan.dtoGenerator.renderer.Renderer;
import com.intellij.ide.util.PackageUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.Arrays;
import java.util.LinkedList;

public class GenerateDTO extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(DataKeys.PROJECT);
        e.getData(DataKeys.PSI_ELEMENT);
        /*Dia d = new Dia(project);
        d.show();*/
        test(project, e);
    }


    public void test(Project p, AnActionEvent e) {
        PsiClass from = getPsiClassFromContext(e);

        Generator generator = new Generator();

        GenerationConfiguration config = new GenerationConfiguration();
        config.from = from;
        config.fields = Arrays.asList(from.getFields());
        config.dtoClassName = "TestDTO";
        config.dtoPackage = from.getContainingFile().getContainingDirectory();
        config.mapperClassName = "Mapper";
        config.mapperPackage = config.dtoPackage;
        config.mapperTestName = "MapperTest";

        generator.generate(config);
    }

    private PsiClass getPsiClassFromContext(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor;
        editor = e.getData(PlatformDataKeys.EDITOR);
        if (psiFile == null || editor == null) {
            return null;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);
        PsiClass psiClass = PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
        return psiClass;
    }
}
