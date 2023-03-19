package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void save(NotificationTask notificationTask) {
        notificationRepository.save(notificationTask);
    }

    public void delete(NotificationTask notificationTask) {
        notificationRepository.delete(notificationTask);
    }

    public List<NotificationTask> findCurrentNotifications() {
        return notificationRepository.findAllByNotificationDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    }




}
