package pro.sky.telegrambot.timer;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.service.NotificationService;


import java.util.concurrent.TimeUnit;

@Component
public class NotificationTimer {
    private NotificationService notificationService;
    private final TelegramBot telegramBot;

    public NotificationTimer(NotificationService notificationService, TelegramBot telegramBot) {
        this.notificationService = notificationService;
        this.telegramBot = telegramBot;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void checkNotifications(){
       notificationService.findCurrentNotifications().forEach(notificationTask -> {
            telegramBot.execute(
                    new SendMessage(notificationTask.getChatId(), "Напоминание о задаче "+notificationTask.getMessage()));
            notificationService.delete(notificationTask);
        });
    }
}
