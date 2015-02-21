package be.wendelen.daan.dtoGenerator.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.popup.PopupComponent;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MessageDialog extends DialogWrapper {
    private String message;

    protected MessageDialog(@Nullable Project project, String message) {
        super(project);
        this.message = message;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return new JLabel(message);
    }

    public static void show(Project project, String message) {
        MessageDialog messageDialog = new MessageDialog(project, message);
        messageDialog.show();
    }
}
