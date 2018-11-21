package com.example.distributedlocktest;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class StartAction implements Action<StateMachineConfig.States, StateMachineConfig.Events> {

    @Override
    @Transactional
    public void execute(StateContext<StateMachineConfig.States, StateMachineConfig.Events> context) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.getStateMachine().sendEvent(StateMachineConfig.Events.GO);
    }
}
