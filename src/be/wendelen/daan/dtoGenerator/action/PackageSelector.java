package be.wendelen.daan.dtoGenerator.action;

import com.intellij.ide.util.PackageChooserDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.psi.PsiPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackageSelector extends TextFieldWithBrowseButton {
    private final Project project;
    private PsiPackage selectedPackage;

    public PackageSelector(Project project, PsiPackage selectedPackage) {
        this.project = project;
        setSelectedPackage(selectedPackage);
    }

    public void init() {
        getChildComponent().setEditable(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                PackageChooserDialog packageChooserDialog =
                        new PackageChooserDialog("titel", project);

                packageChooserDialog.show();

                if (packageChooserDialog.getSelectedPackage() != null) {
                    setSelectedPackage(packageChooserDialog.getSelectedPackage());
                }
            }
        });
    }

    private void selectedPackageChanged() {
        getChildComponent().setText(selectedPackage.getQualifiedName());
    }

    private void packageUnselected() {
        getChildComponent().setText("");
    }

    private void setSelectedPackage(PsiPackage selectedPackage) {
        this.selectedPackage = selectedPackage;
        if(selectedPackage == null) {
            packageUnselected();
        }else {
            selectedPackageChanged();
        }
    }

    public PsiPackage getSelectedPackage() {
        return selectedPackage;
    }
}
