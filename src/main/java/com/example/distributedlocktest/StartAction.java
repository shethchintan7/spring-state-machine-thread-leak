package com.example.distributedlocktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class StartAction implements Action<StateMachineConfig.States, StateMachineConfig.Events> {
    static final Logger log = LoggerFactory.getLogger(StartAction.class);

    @Override
    @Transactional
    public void execute(StateContext<StateMachineConfig.States, StateMachineConfig.Events> context) {
        for (int i = 0; i < 3; i++) {
            log.info("In start action {} {}", i, context.getStateMachine().getId());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
