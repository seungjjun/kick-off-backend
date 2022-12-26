package com.junstudio.kickoff.repositories;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SseEmitterRepositoryTest {
//    @Test
//    void save() {
//        SseEmitterRepository sseEmitterRepository = new SseEmitterRepository();
//
//        String id = "jel1y";
//        SseEmitter sseEmitter = new SseEmitter();
//
//        for (int i = 0; i < 5000; i += 1) {
//            sseEmitterRepository.save(id, sseEmitter);
//        }
//    }

    @Test
    void hashMap() throws InterruptedException {
        HashMap<String, String> hashMap = new HashMap<>();

        String key = "key";
        String value = "value";

        int numberOfThreads = 3;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i += 1) {
            executor.submit(() -> {
                hashMap.put(key, value);
                System.out.println(hashMap.get(key));
                System.out.println("1: " + Thread.currentThread().getName());

                latch.countDown();
            });
        }

        latch.await();

//        for (int i = 0; i < 5; i += 1) {
//            executor.submit(() -> {
//                hashMap.put(key, value);
//                System.out.println(hashMap.get(key));
//                System.out.println("1: " + Thread.currentThread().getName());
//            });
//        }

//        executor.submit(() -> {
//            hashMap.put(key, value);
//            System.out.println(hashMap.get(key));
//            System.out.println("1: " + Thread.currentThread().getName());
//        });
//
//        executor.submit(() -> {
//            hashMap.put(key, value);
//            System.out.println(hashMap.get(key));
//            System.out.println("2: " + Thread.currentThread().getName());
//        });
//
//        executor.submit(() -> {
//            hashMap.put(key, value);
//            System.out.println(hashMap.get(key));
//            System.out.println("3: " + Thread.currentThread().getName());
//        });
    }

    @Test
    void concurrentHashMap() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        String key = "key";
        String value = "value";

        int numberOfThreads = 3;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

//        for (int i = 1; i <= numberOfThreads; i += 1) {
//            String value = String.valueOf(i);
//            executor.submit(() -> {
//                System.out.println(concurrentHashMap.put(key, value));
////                System.out.println(concurrentHashMap.get(key));
////                System.out.println(Thread.currentThread().getName());
//            });
//        }
//
        executor.submit(() -> {
            System.out.println(concurrentHashMap.put(key, value));

//            concurrentHashMap.put(key, value);
//            System.out.println(concurrentHashMap.get(key));
//            System.out.println("1: " + Thread.currentThread().getName());
        });

        executor.submit(() -> {
            System.out.println(concurrentHashMap.put(key, value));

//            concurrentHashMap.put(key, value);
//            System.out.println(concurrentHashMap.get(key));
//            System.out.println("2: " + Thread.currentThread().getName());
        });

        executor.submit(() -> {
            System.out.println(concurrentHashMap.put(key, value));
//            concurrentHashMap.put(key, value);
//            System.out.println(concurrentHashMap.get(key));
//            System.out.println("3: " + Thread.currentThread().getName());
        });
    }
}
