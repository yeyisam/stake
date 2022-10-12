Use Spring boot to implement the Rest methods
As document requirements, service needs to be able to handle a lot of simultaneous requests, so need appropriate data structure  to guaranteed concurrency.
For there is no persistence, the data stored in memory,so i choose the below data class:

 ConcurrentHashMap<Integer, Session>: store the session list:key->customerId, value->session object 
 ConcurrentSkipListMap<Integer, CopyOnWriteArrayList<BettingStake>>: store top 20 BettingStakes
 List<BettingStake> :store all the stakes(need to optimize)
 
 
class variable as below

public class Session
{
    private String sessionKey;
     private  Integer customerId;
    private Date expireTime;

}

public class BettingStake {
    private  Integer customerId;
    private  Integer stakeAmount;
    private  Integer bettingOffer;
}
