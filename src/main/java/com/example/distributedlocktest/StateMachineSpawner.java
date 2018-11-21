package com.example.distributedlocktest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class StateMachineSpawner {

    @Autowired
    StartAction startAction;

    @Bean(name = "stateMachineTaskScheduler")
    public ConcurrentTaskScheduler stateMachineTaskScheduler() {
        ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(10);
        ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler(threadPool);
        return taskScheduler;
    }


    @PostConstruct
    void postConstruct() throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StateMachineBuilder.Builder<StateMachineConfig.States, StateMachineConfig.Events> builder = StateMachineBuilder.builder();

            builder.configureConfiguration()
                    // uncomment to enable custom executor
//                    .taskScheduler(stateMachineTaskScheduler())
                    .withConfiguration().machineId("Test-" + i);

            builder.configureStates()
                    .withStates()
                    .initial(StateMachineConfig.States.START)
                    .state(StateMachineConfig.States.START, startAction)
                    .end(StateMachineConfig.States.STOP);

            builder.configureTransitions()
                    .withExternal()
                    .source(StateMachineConfig.States.START).target(StateMachineConfig.States.STOP).event(StateMachineConfig.Events.GO);

            StateMachine stateMachine = builder.build();
            stateMachine.start();
            stateMachine.stop();
        }
    }
}
