package com.example.distributedlocktest;

import org.springframework.context.annotation.Configuration;

@Configuration
class StateMachineConfig {

    public enum States {
        START,
        INTERMEDIATE,
        STOP
    }

    public enum Events {
        GO1,
        GO2
    }


}
