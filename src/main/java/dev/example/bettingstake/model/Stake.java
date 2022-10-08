package dev.example.bettingstake.model;

public class Stake {

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStake() {
        return stake;
    }

    public void setStake(Integer stake) {
        this.stake = stake;
    }

    public Integer getBettingOffer() {
        return bettingOffer;
    }

    public void setBettingOffer(Integer bettingOffer) {
        this.bettingOffer = bettingOffer;
    }

    private  Integer customerId;
    private  Integer stake;
    private  Integer bettingOffer;


}
