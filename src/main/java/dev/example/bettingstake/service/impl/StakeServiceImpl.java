package dev.example.bettingstake.service.impl;

import dev.example.bettingstake.model.BettingStake;
import dev.example.bettingstake.model.Session;
import dev.example.bettingstake.service.StakeService;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class StakeServiceImpl implements StakeService {

    private ConcurrentSkipListMap<Integer, CopyOnWriteArrayList<BettingStake>> topBettingStakes = new ConcurrentSkipListMap<>();

    private List<BettingStake> bettingStakes = new ArrayList<>();

    @Override
    public void addStake(BettingStake stake) {
        bettingStakes.add(stake);

        if (topBettingStakes.containsKey(stake.getBettingOffer())) {
            CopyOnWriteArrayList<BettingStake> customerBettingStakes = topBettingStakes.get(stake.getBettingOffer());

            List<BettingStake> bettingStakesInTop = customerBettingStakes.stream().filter(s -> s.getCustomerId().equals(stake.getCustomerId())).collect(Collectors.toList());

            if (bettingStakesInTop.isEmpty()) {
                BettingStake minStake = customerBettingStakes.stream().min(Comparator.comparing(BettingStake::getStakeAmount)).get();

                if (customerBettingStakes.size() >= 20 && minStake.getStakeAmount() < stake.getStakeAmount()) {
                    customerBettingStakes.remove(minStake);
                }
                customerBettingStakes.add(stake);
            } else if (bettingStakesInTop.get(0).getStakeAmount() < stake.getStakeAmount()) {
                bettingStakesInTop.get(0).setStakeAmount(stake.getStakeAmount());
            }
        } else {
            CopyOnWriteArrayList<BettingStake> customerBettingStake = new CopyOnWriteArrayList<>();
            customerBettingStake.add(stake);
            topBettingStakes.put(stake.getBettingOffer(), customerBettingStake);
        }

    }

    @Override
    public List<BettingStake> getHighStakes(Integer betOfferId) {

        CopyOnWriteArrayList<BettingStake> customerBettingStakes = topBettingStakes.get(betOfferId);

        return customerBettingStakes.stream()
                .sorted(Comparator.comparing(BettingStake::getStakeAmount).reversed())
                .collect(Collectors.toList());
    }
}
