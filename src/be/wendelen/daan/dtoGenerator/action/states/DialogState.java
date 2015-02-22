package be.wendelen.daan.dtoGenerator.action.states;

import be.wendelen.daan.dtoGenerator.generator.GenerationConfiguration;
import be.wendelen.daan.dtoGenerator.ui.Dialog;

public class DialogState extends State {
    private ChooseSourceRootState chooseSourceRootState;

    public DialogState(Data data, ChooseSourceRootState chooseSourceRootState) {
        super(data);
        this.chooseSourceRootState = chooseSourceRootState;
    }

    @Override
    public State doYourThing() {
        Dialog d = new Dialog(getData().getProject(), getData().getGetClassFromContext());

        if(getData().getTestSourceRoots().length == 0) {
            d.disableTests();
        }

        d.show();
        if(!d.isOK()) {
            getData().setCancelled(true);
            return null;
        }

        getData().setGenerationConfiguration(d.getGenerationConfiguration());

        return chooseSourceRootState;
    }
}
