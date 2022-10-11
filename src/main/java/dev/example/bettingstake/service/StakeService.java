package dev.example.bettingstake.service;

import dev.example.bettingstake.model.BettingStake;

import java.util.List;

public interface StakeService{



      void addStake(BettingStake stake);

      List<BettingStake> getHighStakes(Integer betOfferId);

}
