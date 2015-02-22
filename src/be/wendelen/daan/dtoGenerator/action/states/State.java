package be.wendelen.daan.dtoGenerator.action.states;

public abstract class State {

    private Data data;

    public State(Data data) {

        this.data = data;
    }

    protected Data getData() {
        return data;
    }

    public abstract State doYourThing();
}
