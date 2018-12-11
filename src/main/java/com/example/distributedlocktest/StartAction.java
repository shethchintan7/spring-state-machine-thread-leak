package com.example.distributedlocktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;


@Component
public class StartAction implements Action<StateMachineConfig.States, StateMachineConfig.Events> {
    static final Logger log = LoggerFactory.getLogger(StartAction.class);

    @Override
    public void execute(StateContext<StateMachineConfig.States, StateMachineConfig.Events> context) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("Interrupted");
        }
        log.info("Sending Event");
        context.getStateMachine().sendEvent(StateMachineConfig.Events.GO1);
    }
}
