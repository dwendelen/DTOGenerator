package be.wendelen.daan.dtoGenerator.action;

import be.wendelen.daan.dtoGenerator.generator.GenerationConfiguration;
import be.wendelen.daan.dtoGenerator.generator.Generator;
import be.wendelen.daan.dtoGenerator.ui.Dialog;
import be.wendelen.daan.dtoGenerator.ui.MessageDialog;
import com.intellij.ide.util.DirectoryChooserUtil;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.ide.util.TreeClassChooser;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.libraries.ui.impl.RootDetectionUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import java.util.LinkedList;
import java.util.List;

public class GenerateDTO extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(DataKeys.PROJECT);
        Module module = e.getData(DataKeys.MODULE);

        ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
        PsiDirectory[] sourceRoots = getDirectories(project,
                moduleRootManager.getSourceRoots(JavaSourceRootType.SOURCE));
        PsiDirectory[] testSourceRoots = getDirectories(project,
                moduleRootManager.getSourceRoots(JavaSourceRootType.TEST_SOURCE));

        if(sourceRoots.length == 0) {
            MessageDialog.show(project, "No source root found.");
            return;
        }

        Dialog d = new Dialog(project, getPsiClassFromContext(e));

        if(testSourceRoots.length == 0) {
            d.disableTests();
        }

        d.show();

        GenerationConfiguration generationConfiguration = d.getGenerationConfiguration();

        PsiDirectory sourceRoot = null;
        if(sourceRoots.length == 1) {
            sourceRoot = sourceRoots[0];
        } else {
            sourceRoot = DirectoryChooserUtil.chooseDirectory(sourceRoots, null, project, null);
        }

        PsiDirectory testRoot = null;
        if(generationConfiguration.generateMapperTest) {
            if (testSourceRoots.length == 1) {
                testRoot = testSourceRoots[0];
            } else {
                testRoot = DirectoryChooserUtil.chooseDirectory(testSourceRoots, null, project, null);
            }
        }

        generationConfiguration.module = module;
        generationConfiguration.sourceRoot = sourceRoot;
        generationConfiguration.testRoot = testRoot;
        generationConfiguration.mapperTestName = generationConfiguration.mapperClassName + "Test";

        test(project, generationConfiguration);
    }

    private PsiDirectory[] getDirectories(Project project, List<VirtualFile> files) {
        PsiManager psiManager = PsiManager.getInstance(project);

        int i = 0;
        PsiDirectory[] result = new PsiDirectory[files.size()];
        for (VirtualFile file : files) {
            result[i++] = psiManager.findDirectory(file);
        }

        return result;
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
