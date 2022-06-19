package br.com.notification.service;

import br.com.notification.request.NotificationRequest;
import br.com.notification.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    void saveNotification(NotificationRequest notificationRequest);
    List<NotificationResponse> getNotification(Integer pageNumber, Integer size, String sortBy);
    NotificationResponse getNotificationByCpf(String cpf);
}
