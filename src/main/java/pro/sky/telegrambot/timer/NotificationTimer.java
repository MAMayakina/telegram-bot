package pro.sky.telegrambot.timer;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.repository.NotificationRepository;
import pro.sky.telegrambot.service.NotificationService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationTimer {
    private final NotificationRepository notificationRepository;
    private final TelegramBot telegramBot;

    public NotificationTimer(NotificationRepository notificationRepository, TelegramBot telegramBot) {
        this.notificationRepository = notificationRepository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void checkNotifications(){
        notificationRepository.findAllByNotificationDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        ).forEach(notificationTask -> {
            telegramBot.execute(
                    new SendMessage(notificationTask.getChatId(), "Напоминание о задаче "+notificationTask.getMessage()));
            notificationRepository.delete(notificationTask);
        });
    }
}
