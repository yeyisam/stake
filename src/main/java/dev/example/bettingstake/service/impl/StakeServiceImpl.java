package dev.example.bettingstake.service.impl;

import dev.example.bettingstake.model.BettingStake;
import dev.example.bettingstake.model.Session;
import dev.example.bettingstake.service.StakeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class StakeServiceImpl implements StakeService {

    private  static ConcurrentSkipListMap<Integer, HashMap<Integer,BettingStake> > topBettingStakes=new ConcurrentSkipListMap<>();

    private static ConcurrentLinkedDeque<BettingStake> bettingStakes=new ConcurrentLinkedDeque<>();

    @Override
    public Void addStake(BettingStake stake) {
         bettingStakes.add(stake);

         HashMap<Integer,BettingStake> customerBettingStakes= topBettingStakes.get(stake.getBettingOffer());

         if(customerBettingStakes==null) {
             HashMap<Integer,BettingStake> customerBettingStake=new HashMap<>();
             customerBettingStake.put(stake.getCustomerId(),stake);
             topBettingStakes.put(stake.getBettingOffer(),customerBettingStake);
         }
         else{
             BettingStake customerBettingStake=customerBettingStakes.get(stake.getCustomerId());
             if(null==customerBettingStake){
                 customerBettingStakes.put(stake.getCustomerId(),stake);
             }
             else if(customerBettingStake.getStakeAmount()<stake.getStakeAmount()) {
                 customerBettingStake.setStakeAmount(stake.getStakeAmount());
             }

         }



         return null;
    }

    @Override
    public List<BettingStake> getHighStakes(Integer betOfferId) {

        HashMap<Integer,BettingStake> customerBettingStakes= topBettingStakes.get(betOfferId);

        return null;
    }
}
