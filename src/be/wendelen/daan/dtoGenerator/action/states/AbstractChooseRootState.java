package be.wendelen.daan.dtoGenerator.action.states;

import com.intellij.ide.util.DirectoryChooserUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

public abstract class AbstractChooseRootState extends State {
    public AbstractChooseRootState(Data data) {
        super(data);
    }

    protected PsiDirectory chooseDir(PsiDirectory[] dirs) {
        if (dirs.length == 1) {
            return dirs[0];
        }

        return DirectoryChooserUtil.chooseDirectory(dirs, null, getData().getProject(), null);
    }
}
