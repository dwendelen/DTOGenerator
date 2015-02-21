package be.wendelen.daan.dtoGenerator.action;

import be.wendelen.daan.dtoGenerator.generator.GenerationConfiguration;
import be.wendelen.daan.dtoGenerator.generator.Generator;
import be.wendelen.daan.dtoGenerator.model.ast.Class;
import be.wendelen.daan.dtoGenerator.model.psi.ClassFromPsiFactory;
import be.wendelen.daan.dtoGenerator.renderer.Renderer;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.ide.util.PackageUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.JavaSourceUtil;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GenerateDTO extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(DataKeys.PROJECT);
        Module module = e.getData(DataKeys.MODULE);

        Dia d = new Dia(project, getPsiClassFromContext(e));
        d.show();



        ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
        List<VirtualFile> sourceRoots = moduleRootManager.getSourceRoots(JavaSourceRootType.SOURCE);
        List<VirtualFile> testSourceRoots = moduleRootManager.getSourceRoots(JavaSourceRootType.TEST_SOURCE);

        if(sourceRoots.size() != 1) {
            throw new IllegalStateException("There must be exactly one source root");
        }

        if(testSourceRoots.size() != 1) {
            throw new IllegalStateException("There must be exactly one test source root");
        }

        PsiDirectory sourceRoot = PsiManager.getInstance(project).findDirectory(sourceRoots.get(0));
        PsiDirectory testRoot = PsiManager.getInstance(project).findDirectory(testSourceRoots.get(0));

        GenerationConfiguration generationConfiguration = d.getGenerationConfiguration();
        generationConfiguration.module = module;
        generationConfiguration.sourceRoot = sourceRoot;
        generationConfiguration.testRoot = testRoot;
        generationConfiguration.mapperTestName = generationConfiguration.mapperClassName + "Test";

        test(project, generationConfiguration);
    }


    public void test(Project p, GenerationConfiguration config) {

        Generator generator = new Generator();
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
