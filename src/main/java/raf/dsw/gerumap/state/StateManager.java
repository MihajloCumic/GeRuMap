package raf.dsw.gerumap.state;

import raf.dsw.gerumap.state.implementation.*;

public class StateManager {
    private State currentState;
    private PojamState pojamState;
    private VezaState vezaState;
    private DeleteState deleteState;
    private MoveState moveState;
    private SelectState selectState;

    public StateManager(){
        initStates();
    }

    private void initStates(){
        pojamState = new PojamState();
        vezaState = new VezaState();
        deleteState = new DeleteState();
        moveState = new MoveState();
        selectState = new SelectState();
        currentState = pojamState;
    }

    public State getCurrentState(){
        return currentState;
    }

    public void setPojamState(){
        currentState = pojamState;
    }

    public void setVezaState(){
        currentState = vezaState;
    }

    public void setDeleteState(){
        currentState = deleteState;
    }

    public void setMoveState(){
        currentState = moveState;
    }

    public void setSelectState(){
        currentState = selectState;
    }
}
