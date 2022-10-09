package dev.example.bettingstake.controller;

import dev.example.bettingstake.model.BettingStake;
import dev.example.bettingstake.service.SessionService;
import dev.example.bettingstake.service.StakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StakeController {

    @Autowired
    private StakeService stakeService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value="/{customerId}/session",method= RequestMethod.GET)
    @ResponseBody
    public  String getSessionKey(@PathVariable Integer customerId){

        return sessionService.getSessionKeyByCustomerId(customerId);

    }

    @PostMapping(value="/{betOfferId}/stake")
    @ResponseBody
    public  void addStake(Integer betOfferId,String sessionkey,@RequestBody Integer stake){

        BettingStake bettingStake=new BettingStake();
        bettingStake.setStakeAmount(stake);
        bettingStake.setCustomerId(11);
        bettingStake.setBettingOffer(betOfferId);

        stakeService.addStake(bettingStake);
    }

    @GetMapping (value="/{betOfferId}/highStakes")
    @ResponseBody
    public  String getHighStakes(@PathVariable Integer betOfferId){

        List<BettingStake> stakes= stakeService.getHighStakes(betOfferId);
        return  "aa";

    }




}
