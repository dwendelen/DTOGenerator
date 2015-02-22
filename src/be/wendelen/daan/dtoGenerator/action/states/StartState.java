package be.wendelen.daan.dtoGenerator.action.states;

import be.wendelen.daan.dtoGenerator.ui.MessageDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import java.util.List;

public class StartState extends State {

    private DialogState dialogState;

    public StartState(Data data, DialogState dialogState) {
        super(data);
        this.dialogState = dialogState;
    }

    @Override
    public State doYourThing() {
        ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(getData().getModule());
        PsiDirectory[] sourceRoots = getDirectories(getData().getProject(),
                moduleRootManager.getSourceRoots(JavaSourceRootType.SOURCE));
        PsiDirectory[] testSourceRoots = getDirectories(getData().getProject(),
                moduleRootManager.getSourceRoots(JavaSourceRootType.TEST_SOURCE));

        if(sourceRoots.length == 0) {
            MessageDialog.show(getData().getProject(), "No source root found.");
            getData().setCancelled(true);
            return null;
        }

        getData().setSourceRoots(sourceRoots);
        getData().setTestSourceRoots(testSourceRoots);

        return dialogState;
    }

    private PsiDirectory[] getDirectories(Project project, List<VirtualFile> files) {
        PsiManager psiManager = PsiManager.getInstance(project);

        int i = 0;
        PsiDirectory[] result = new PsiDirectory[files.size()];
        for (VirtualFile file : files) {
            result[i++] = psiManager.findDirectory(file);
        }

        return result;
    }
}
