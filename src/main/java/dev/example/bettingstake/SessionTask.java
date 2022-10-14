package dev.example.bettingstake;

import dev.example.bettingstake.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SessionTask {

    @Autowired
    private SessionService sessionService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void clearExpiredSession() {
        sessionService.clearExpiredSession();
    }
}
