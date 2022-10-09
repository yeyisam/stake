package dev.example.bettingstake.service;

import dev.example.bettingstake.model.Stake;

import java.util.List;

public interface StakeService{

      String getSessionKeyByCusomerId(Integer customerId);

      Void addStake(Stake stake);

      List<Stake> getHighStakes();

}
