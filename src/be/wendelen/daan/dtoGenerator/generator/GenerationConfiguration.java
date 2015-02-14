package be.wendelen.daan.dtoGenerator.generator;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiPackage;

import java.util.List;

public class GenerationConfiguration {
    public PsiClass from;
    public List<PsiField> fields;

    public PsiDirectory dtoPackage;
    public String dtoClassName;

    public PsiDirectory mapperPackage;
    public String mapperClassName;

    public boolean generateMapperTest;
    public String mapperTestName;
}
