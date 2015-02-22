package be.wendelen.daan.dtoGenerator.action.states;


import be.wendelen.daan.dtoGenerator.generator.GenerationConfiguration;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;

public class StateManager  {
    private State currentState;
    private Data data;

    public StateManager(Module module, PsiClass classFromContext) {
        data = new Data(module, classFromContext);
    }

    public GenerationConfiguration doYourThing() {
        createStates();

        while (true) {
            currentState = currentState.doYourThing();
            if(currentState == null) {
                if(data.isCancelled()) {
                    return null;
                }

                return data.getGenerationConfiguration();
            }
        }
    }

    private void createStates() {
        ChooseTestRootState chooseTestRootState = new ChooseTestRootState(data);
        ChooseSourceRootState chooseSourceRootState = new ChooseSourceRootState(data, chooseTestRootState);
        DialogState dialogState = new DialogState(data, chooseSourceRootState);
        StartState startState = new StartState(data, dialogState);

        currentState = startState;
    }
}
