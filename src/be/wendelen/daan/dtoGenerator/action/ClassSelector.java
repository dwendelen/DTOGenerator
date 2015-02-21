package be.wendelen.daan.dtoGenerator.action;

import com.intellij.ide.util.TreeClassChooser;
import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.psi.PsiClass;
import com.intellij.ui.TextFieldWithHistoryWithBrowseButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassSelector extends TextFieldWithBrowseButton {
    private final Project project;
    private PsiClass selectedClass;

    public ClassSelector(Project project, PsiClass selectedClass) {
        this.project = project;
        setSelectedClass(selectedClass);
    }

    public void init() {
        getChildComponent().setEditable(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TreeClassChooser
                classChooser = TreeClassChooserFactory.getInstance(project)
                        .createProjectScopeChooser("Test");
                classChooser.showDialog();
                if (classChooser.getSelected() != null) {
                    setSelectedClass(classChooser.getSelected());
                }
            }
        });
    }

    private void selectedClassChanged() {
        getChildComponent().setText(selectedClass.getQualifiedName());
    }

    private void classUnselected() {
        getChildComponent().setText("");
    }

    private void setSelectedClass(PsiClass psiClass) {
        this.selectedClass = psiClass;
        if(psiClass == null) {
            classUnselected();
        }else {
            selectedClassChanged();
        }
    }

    public PsiClass getSelectedClass() {
        return selectedClass;
    }
}
