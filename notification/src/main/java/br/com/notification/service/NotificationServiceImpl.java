package br.com.notification.service;

import br.com.notification.config.ConvertUtils;
import br.com.notification.exception.CpfExistsException;
import br.com.notification.model.NotificationEntity;
import br.com.notification.repository.NotificationRepository;
import br.com.notification.request.NotificationRequest;
import br.com.notification.response.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{


    private NotificationRepository notificationRepository;
    private ConvertUtils convertUtils;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   ConvertUtils convertUtils) {
        this.notificationRepository = notificationRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public void saveNotification(NotificationRequest notificationRequest) {
        NotificationEntity entity = (NotificationEntity)  this.convertUtils.convertRequestToEntity(notificationRequest, NotificationEntity.class);
        this.notificationRepository.save(entity);
    }


    @Override
    public List<NotificationResponse> getNotification(Integer pageNumber, Integer size, String sortBy) {

        Pageable paging = PageRequest.of(pageNumber,size, Sort.by(sortBy));
        Page<NotificationEntity> pagedResult = this.notificationRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return this.convertUtils.convertToListResponse(pagedResult.getContent(), NotificationResponse.class);
        }
        return null;

    }

    @Override
    public NotificationResponse getNotificationByCpf(String cpf) {
        NotificationEntity entity = this.notificationRepository.getNotificationByCpfCustomer(cpf);
        if (entity != null) {
            return (NotificationResponse) this.convertUtils.convertEntityToRequest(entity, NotificationResponse.class);
        }
        return null;


    }
}
