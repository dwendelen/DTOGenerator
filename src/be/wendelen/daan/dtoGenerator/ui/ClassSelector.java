package be.wendelen.daan.dtoGenerator.ui;

import com.intellij.ide.util.TreeClassChooser;
import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.psi.PsiClass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class ClassSelector extends TextFieldWithBrowseButton {
    private final Project project;
    private PsiClass selectedClass;
    private List<ClassSelectorListener> listeners = new LinkedList<ClassSelectorListener>();

    public ClassSelector(Project project, PsiClass selectedClass) {
        this.project = project;
        setSelectedClass(selectedClass);
    }

    public void addListener(ClassSelectorListener listener) {
        listeners.add(listener);
    }

    public void init() {
        getChildComponent().setEditable(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TreeClassChooser classChooser = TreeClassChooserFactory.getInstance(project)
                        .createProjectScopeChooser("Test");
                classChooser.select(getSelectedClass());

                classChooser.showDialog();
                if (classChooser.getSelected() != null) {
                    setSelectedClass(classChooser.getSelected());
                }
            }
        });
    }

    private void selectedClassChanged() {
        getChildComponent().setText(selectedClass.getQualifiedName());
        for (ClassSelectorListener listener : listeners) {
            listener.classSelected(selectedClass);
        }
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
