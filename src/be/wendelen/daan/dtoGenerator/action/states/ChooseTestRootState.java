package be.wendelen.daan.dtoGenerator.action.states;

import com.intellij.psi.PsiDirectory;

public class ChooseTestRootState extends AbstractChooseRootState {
    public ChooseTestRootState(Data data) {
        super(data);
    }

    @Override
    public State doYourThing() {
        PsiDirectory testRoot = null;
        if(getData().getGenerationConfiguration().generateMapperTest) {
            testRoot = chooseDir(getData().getTestSourceRoots());

            if(testRoot == null) {
                getData().setCancelled(true);
                return null;
            }
        }

        getData().getGenerationConfiguration().testRoot = testRoot;

        return null;
    }
}
