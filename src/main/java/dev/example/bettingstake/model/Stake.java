package dev.example.bettingstake.model;

public class Stake {

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStakeAmount() {
        return stakeAmount;
    }

    public void setStakeAmount(Integer stakeAmount) {
        this.stakeAmount = stakeAmount;
    }

    public Integer getBettingOffer() {
        return bettingOffer;
    }

    public void setBettingOffer(Integer bettingOffer) {
        this.bettingOffer = bettingOffer;
    }

    private  Integer customerId;
    private  Integer stakeAmount;
    private  Integer bettingOffer;


}
