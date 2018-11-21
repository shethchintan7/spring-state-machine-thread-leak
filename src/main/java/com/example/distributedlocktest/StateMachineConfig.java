package com.example.distributedlocktest;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<StateMachineConfig.States, StateMachineConfig.Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.START)
                .end(States.STOP);

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.START).target(States.STOP).event(Events.GO)
                .and()
                .withExternal();
    }

    public enum States {
        START,
        STOP
    }

    public enum Events {
        GO
    }

}
