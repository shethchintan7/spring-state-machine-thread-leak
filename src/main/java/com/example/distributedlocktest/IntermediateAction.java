package com.example.distributedlocktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class IntermediateAction implements Action<StateMachineConfig.States, StateMachineConfig.Events> {
    static final Logger log = LoggerFactory.getLogger(IntermediateAction.class);

    @Override
    public void execute(StateContext<StateMachineConfig.States, StateMachineConfig.Events> context) {
        for (int i = 0; i < 3; i++) {
            try {
                log.info("In start action {} {}", i, context.getStateMachine().getId());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.info("INTERRUPTED");
                e.printStackTrace();
            }
        }
//        context.getStateMachine().sendEvent(StateMachineConfig.Events.GO2);
//        if (ThreadLocalRandom.current().nextInt() % 5 == 0) {
//            context.getStateMachine().sendEvent(StateMachineConfig.Events.GO);
//        }
    }
}
