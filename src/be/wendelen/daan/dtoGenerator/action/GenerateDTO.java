package be.wendelen.daan.dtoGenerator.action;

import be.wendelen.daan.dtoGenerator.action.states.StateManager;
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
        Module module = e.getData(DataKeys.MODULE);

        StateManager stateManager = new StateManager(module, getPsiClassFromContext(e));
        GenerationConfiguration generationConfiguration = stateManager.doYourThing();
        if(generationConfiguration == null) {
            return;
        }

        generationConfiguration.module = module;
        generationConfiguration.mapperTestName = generationConfiguration.mapperClassName + "Test";

        Generator generator = new Generator();
        generator.generate(generationConfiguration);
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
