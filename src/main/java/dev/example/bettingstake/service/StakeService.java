package dev.example.bettingstake.service;

import dev.example.bettingstake.model.BettingStake;

import java.util.List;

public interface StakeService{



      Void addStake(BettingStake stake);

      List<BettingStake> getHighStakes(Integer betOfferId);

}
