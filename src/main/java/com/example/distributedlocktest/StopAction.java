package com.example.distributedlocktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class StopAction implements Action<StateMachineConfig.States, StateMachineConfig.Events> {
    static final Logger log = LoggerFactory.getLogger(StopAction.class);

    @Override
    @Transactional
    public void execute(StateContext<StateMachineConfig.States, StateMachineConfig.Events> context) {
        log.info("In stop event", context.getStateMachine().getId());
    }
}
