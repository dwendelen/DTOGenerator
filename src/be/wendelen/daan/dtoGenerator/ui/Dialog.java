package be.wendelen.daan.dtoGenerator.ui;

import be.wendelen.daan.dtoGenerator.generator.GenerationConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;

public class Dialog extends DialogWrapper {
    private Project project;
    private PsiClass psiClassFromContext;
    private boolean enableTests = true;

    private ClassSelector classSelector;
    private PackageSelector dtoPackageSelector;
    private PackageSelector mapperPackageSelector;
    private JTextField dtoNameTextField;
    private JTextField mapperNameTextField;
    private JCheckBox generateMapperTest;

    public Dialog(@Nullable Project project, PsiClass psiClassFromContext) {
        super(project);
        this.project = project;
        this.psiClassFromContext = psiClassFromContext;
    }

    public GenerationConfiguration getGenerationConfiguration() {
        GenerationConfiguration generationConfiguration = new GenerationConfiguration();
        generationConfiguration.from = classSelector.getSelectedClass();
        generationConfiguration.dtoClassName = dtoNameTextField.getText();
        generationConfiguration.dtoPackage = dtoPackageSelector.getSelectedPackage();
        generationConfiguration.mapperClassName = mapperNameTextField.getText();
        generationConfiguration.mapperPackage = mapperPackageSelector.getSelectedPackage();
        generationConfiguration.methods = Arrays.asList(generationConfiguration.from.getMethods());
        generationConfiguration.generateMapperTest = generateMapperTest.isSelected();
        return generationConfiguration;
    }

    public void show() {
        init();
        super.show();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        String packageName = ((PsiJavaFile)psiClassFromContext.getContainingFile()).getPackageName();
        PsiPackage psiPackage = JavaPsiFacade.getInstance(project).findPackage(packageName);

        classSelector = new ClassSelector(project, psiClassFromContext);
        dtoPackageSelector = new PackageSelector(project, psiPackage);
        mapperPackageSelector = new PackageSelector(project, psiPackage);

        dtoNameTextField = new JTextField();
        mapperNameTextField = new JTextField();
        generateMapperTest = new JCheckBox();
        generateMapperTest.setSelected(enableTests);

        DialogContentFactory dialogContentFactory = DialogContentFactory.newDialogContent()
                .withClassSelector(classSelector)
                .withDtoPackageSelector(dtoPackageSelector)
                .withMapperPackageSelector(mapperPackageSelector)
                .withDtoNameTextField(dtoNameTextField)
                .withMapperNameTextField(mapperNameTextField)
                .withGenerateMapperTest(generateMapperTest)
                .build();

        return dialogContentFactory.createContent(enableTests);
    }

    public void disableTests() {
        enableTests = false;
    }
}
