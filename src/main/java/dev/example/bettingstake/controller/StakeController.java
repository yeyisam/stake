package dev.example.bettingstake.controller;

import dev.example.bettingstake.model.BettingStake;
import dev.example.bettingstake.model.Session;
import dev.example.bettingstake.service.SessionService;
import dev.example.bettingstake.service.StakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class StakeController {

    @Autowired
    private StakeService stakeService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/{customerId}/session", method = RequestMethod.GET)
    @ResponseBody
    public String getSessionKey(@PathVariable Integer customerId) {

        return sessionService.getSessionKeyByCustomerId(customerId);
    }

    @PostMapping(value = "/{betOfferId}/stake")
    @ResponseBody
    public void addStake(@PathVariable Integer betOfferId, String sessionKey, @RequestBody Integer stake) throws Exception {

        Session session = sessionService.getSessionBySessionKey(sessionKey);
        if (session == null) {
            throw new Exception("The sessionKey is not valid.");
        } else if (session.getExpireTime() < new Date().getTime()) {
            throw new Exception("The sessionKey is expired");
        }


        BettingStake bettingStake = new BettingStake();
        bettingStake.setStakeAmount(stake);
        bettingStake.setCustomerId(session.getCustomerId());
        bettingStake.setBettingOffer(betOfferId);

        stakeService.addStake(bettingStake);
    }

    @GetMapping(value = "/{betOfferId}/highStakes")
    @ResponseBody
    public String getHighStakes(@PathVariable Integer betOfferId) {

        List<BettingStake> stakes = stakeService.getHighStakes(betOfferId);
        if (stakes.isEmpty()) {
            return "";
        }

        return stakes.stream().map(x -> (x.getCustomerId() + "=" + x.getStakeAmount())).collect(Collectors.joining(","));

    }


}
