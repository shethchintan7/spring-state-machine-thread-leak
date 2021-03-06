package com.example.distributedlocktest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class StateMachineSpawner {

    @Autowired
    StartAction startAction;

    @Autowired
    StopAction stopAction;

    @Autowired
    IntermediateAction intermediateAction;

    //(4) This is correct way to create BEAN, This can be any ()other  @Configuration bean
    @Bean(name = "stateMachineTaskScheduler")
    public ConcurrentTaskScheduler stateMachineTaskScheduler() {
        // (6) We need to figure out how many threads are actually engaged for our kind of state machines.
        //     This depends on how much time our ACTION class is blocking..
        //     Ideally our action should just trigger http calls to NiFi and must return as soon as possible
        //     So we should be fine having just around 50 to 100 threads.
        ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(100
                , new CustomizableThreadFactory("my-pool-"));
        ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler(threadPool);
        return taskScheduler;
    }

    //(5) This is how we should inject singleton reference
    @Autowired
    @Qualifier("stateMachineTaskScheduler")
    public ConcurrentTaskScheduler scheduler;

    @PostConstruct
    void postConstruct() throws Exception {
        for (int i = 0; i < 1; i++) {
            StateMachineBuilder.Builder<StateMachineConfig.States, StateMachineConfig.Events> builder = StateMachineBuilder.builder();

            /* (1)
             * ~/.m2/repository/org/springframework/statemachine/spring-statemachine-core/2.0.2.RELEASE/spring-statemachine-core-2.0.2.RELEASE-sources.jar!/org/springframework/statemachine/config/StateMachineBuilder.java:153
             * each new builder.build() calls "new ConcurrentTaskScheduler()"
             */
            builder.configureConfiguration()
                    .withConfiguration()
                    .machineId("Test-" + i)
                    /* (2)
                     * So we MUST configure manually, but we need singleton , so need to inject BEAN
                     */
                    .taskScheduler(scheduler);


            builder.configureStates()
                    .withStates()
                    .initial(StateMachineConfig.States.START)
                    .state(StateMachineConfig.States.START)
                    .state(StateMachineConfig.States.INTERMEDIATE)
                    .state(StateMachineConfig.States.STOP)
                    .end(StateMachineConfig.States.STOP);

            builder.configureTransitions()
                    .withExternal()
                    .source(StateMachineConfig.States.START)
                    .target(StateMachineConfig.States.START)
                    .action(startAction)
                    .timer(10000)

                    .and()
                    .withExternal()
                    .source(StateMachineConfig.States.START)
                    .target(StateMachineConfig.States.INTERMEDIATE)
                    .event(StateMachineConfig.Events.GO1)

                    .and()
                    .withExternal()
                    .source(StateMachineConfig.States.INTERMEDIATE)
                    .target(StateMachineConfig.States.INTERMEDIATE)
                    .timer(30000)
                    .action(intermediateAction)

                    .and()
                    .withExternal()
                    .source(StateMachineConfig.States.INTERMEDIATE)
                    .target(StateMachineConfig.States.STOP)
                    .event(StateMachineConfig.Events.GO2)

                    .and()
                    .withInternal()
                    .source(StateMachineConfig.States.STOP)
                    .action(stopAction);


            StateMachine stateMachine = builder.build();
            stateMachine.start();
        }
    }
}


