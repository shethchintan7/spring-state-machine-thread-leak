package com.example.distributedlocktest;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DistributedlocktestApplicationTests {

//    private final ExecutorService dedicatedThreadForLock = Executors.newSingleThreadExecutor(
//            new BasicThreadFactory.Builder().namingPattern("testname").build());

//    private final ExecutorService dedicatedThreadForLock = Executors.newFixedThreadPool(5);
//
//    @Test
//    public void mutexTest() throws Exception {
//
//        CuratorFramework client = CuratorFrameworkFactory
//                .builder()
//                .defaultData(new byte[0])
//                .connectString("localhost:2181")
//                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//                .build();
//        client.start();
//
//        dedicatedThreadForLock.submit(() -> {
//            log.info("thread 1");
//            InterProcessMutex lock = new InterProcessMutex(client, "/test");
//            try {
//                log.info("trying to acquire");
//                boolean didAcquire = lock.acquire(30, TimeUnit.SECONDS);
//                log.info("thread 1 lock.isAcquiredInThisProcess() {}", lock.isAcquiredInThisProcess());
//                log.info("didAcquire {}", didAcquire);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(1000);
//        dedicatedThreadForLock.submit(() -> {
//            log.info("thread 2");
//            InterProcessMutex lock = new InterProcessMutex(client, "/test");
//            try {
//                log.info("trying to acquire");
//                boolean didAcquire = lock.acquire(5, TimeUnit.SECONDS);
//                log.info("didAcquire 2 {}", didAcquire);
//                log.info("trying to release");
//                log.info("thread 2 lock.isAcquiredInThisProcess() {}", lock.isAcquiredInThisProcess());
//                lock.release();
//                log.info("release 1 {}");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(50000);
//
//    }
//
//    @Test
//    public void semaphoreTest() throws Exception {
//
//        CuratorFramework client = CuratorFrameworkFactory
//                .builder()
//                .defaultData(new byte[0])
//                .connectString("localhost:2181")
//                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//                .build();
//        client.start();
//
//        dedicatedThreadForLock.submit(() -> {
//            log.info("thread 1");
//            InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, "/semaphorelock");
//            try {
//                log.info("trying to acquire");
//                boolean didAcquire = lock.acquire(30, TimeUnit.SECONDS);
//                log.info("thread 1 lock.isAcquiredInThisProcess() {}", lock.isAcquiredInThisProcess());
//                log.info("didAcquire {}", didAcquire);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(1000);
//        dedicatedThreadForLock.submit(() -> {
//            log.info("thread 2");
//            InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, "/semaphorelock");
//            try {
//                log.info("trying to release");
//                log.info("thread 2 lock.isAcquiredInThisProcess() {}", lock.isAcquiredInThisProcess());
//                lock.release();
//                log.info("release 1 {}");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(10000);
//
//    }
}
