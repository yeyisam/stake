package dev.example.bettingstake.service.impl;

import dev.example.bettingstake.model.BettingStake;
import dev.example.bettingstake.service.StakeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StakeServiceImpl implements StakeService {

    @Override
    public Void addStake(BettingStake stake) {
        return null;
    }

    @Override
    public List<BettingStake> getHighStakes(Integer betOfferId) {
        return null;
    }
}
