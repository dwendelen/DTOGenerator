package be.wendelen.daan.dtoGenerator.action;

import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.ide.util.TreeClassChooser;
import com.intellij.ide.util.TreeClassChooserFactory;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.ComboboxWithBrowseButton;
import com.intellij.ui.StringComboboxEditor;
import com.intellij.ui.TextFieldWithAutoCompletion;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xtrit on 2015-02-14.
 */
public class Dia extends DialogWrapper {
    private Project project;

    protected Dia(@Nullable Project project) {
        super(project);
        this.project = project;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {

        /*ComboBox box = new ComboBox();
        StringComboboxEditor e = new StringComboboxEditor(project, JavaClassFileType.INSTANCE, box);

        JComboBox b = new JComboBox();
        ComboboxWithBrowseButton c = new ComboboxWithBrowseButton(b);
        c.addBrowseFolderListener();
        ComponentWithBrowseButton co = new ComboboxWithBrowseButton(b,  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TreeClassChooser test = TreeClassChooserFactory.getInstance(project).createProjectScopeChooser("Test");
                test.showDialog();
                b.
            }
        });

        return textFieldWithBrowseButton;*/
        return null;
    }
}
