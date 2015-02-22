package be.wendelen.daan.dtoGenerator.action.states;

import be.wendelen.daan.dtoGenerator.generator.GenerationConfiguration;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.jps.model.java.JavaSourceRootType;

public class Data {
    private Module module;

    private PsiClass getClassFromContext;
    private PsiDirectory[] sourceRoots;
    private PsiDirectory[] testSourceRoots;
    private GenerationConfiguration generationConfiguration;
    private boolean isCancelled = false;

    public Data(Module module, PsiClass getClassFromContext) {
        this.module = module;
        this.getClassFromContext = getClassFromContext;
    }

    public Project getProject() {
        return getModule().getProject();
    }

    public Module getModule() {
        return module;
    }

    public GenerationConfiguration getGenerationConfiguration() {
        return generationConfiguration;
    }

    public void setGenerationConfiguration(GenerationConfiguration generationConfiguration) {
        this.generationConfiguration = generationConfiguration;
    }


    public PsiDirectory[] getSourceRoots() {
        return sourceRoots;
    }

    public void setSourceRoots(PsiDirectory[] sourceRoots) {
        this.sourceRoots = sourceRoots;
    }

    public PsiDirectory[] getTestSourceRoots() {
        return testSourceRoots;
    }

    public void setTestSourceRoots(PsiDirectory[] testSourceRoots) {
        this.testSourceRoots = testSourceRoots;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public PsiClass getGetClassFromContext() {
        return getClassFromContext;
    }
}
