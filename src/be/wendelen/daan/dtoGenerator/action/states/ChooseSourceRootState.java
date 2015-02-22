package be.wendelen.daan.dtoGenerator.action.states;

import com.intellij.psi.PsiDirectory;

public class ChooseSourceRootState extends AbstractChooseRootState {
    private ChooseTestRootState chooseTestRootState;

    public ChooseSourceRootState(Data data, ChooseTestRootState chooseTestRootState) {
        super(data);
        this.chooseTestRootState = chooseTestRootState;
    }

    @Override
    public State doYourThing() {
        PsiDirectory sourceRoot = chooseDir(getData().getSourceRoots());

        if(sourceRoot == null) {
            getData().setCancelled(true);
            return null;
        }

        getData().getGenerationConfiguration().sourceRoot = sourceRoot;

        return chooseTestRootState;
    }
}
