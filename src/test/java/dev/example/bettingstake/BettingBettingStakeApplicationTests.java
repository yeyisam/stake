package dev.example.bettingstake;

import dev.example.bettingstake.model.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import dev.example.bettingstake.model.BettingStake;

@SpringBootTest
class BettingBettingStakeApplicationTests {

    @Test
    void contextLoads() {
        test1();
    }

    void test1()
    {
        BettingStake bettingStake1=new BettingStake();
        bettingStake1.setBettingOffer(1);
        bettingStake1.setCustomerId(1);
        bettingStake1.setStakeAmount(1);

        BettingStake bettingStake2=new BettingStake();
        bettingStake2.setBettingOffer(2);
        bettingStake2.setCustomerId(2);
        bettingStake2.setStakeAmount(2);

        ConcurrentSkipListMap<Integer,BettingStake> cslMap = new ConcurrentSkipListMap<>();
        cslMap.put(1,bettingStake1);
        cslMap.put(1,bettingStake2);
        ConcurrentLinkedDeque<BettingStake> cslMap2 = new ConcurrentLinkedDeque<>();
        cslMap2.add(bettingStake1);
        cslMap2.add(bettingStake1);
        cslMap2.add(bettingStake2);

        ConcurrentHashMap<Integer,BettingStake> cslMap3 = new ConcurrentHashMap<>();
        cslMap3.put(1,bettingStake1);
        cslMap3.put(1,bettingStake2);
        cslMap3.put(2,bettingStake1);
        cslMap3.replace(2,bettingStake2);



        for (Map.Entry<Integer, BettingStake> entry : cslMap.entrySet()) { //删除后的集合
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }


    }

}
