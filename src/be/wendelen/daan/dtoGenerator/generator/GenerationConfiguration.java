package be.wendelen.daan.dtoGenerator.generator;

import com.intellij.openapi.externalSystem.model.project.ContentRootData;
import com.intellij.openapi.module.Module;
import com.intellij.psi.*;

import java.util.List;

public class GenerationConfiguration {
    public PsiClass from;
    public List<PsiMethod> methods;

    public PsiPackage dtoPackage;
    public String dtoClassName;

    public PsiPackage mapperPackage;
    public String mapperClassName;

    public boolean generateMapperTest;
    public String mapperTestName;

    public Module module;
    public PsiDirectory sourceRoot;
    public PsiDirectory testRoot;
}
