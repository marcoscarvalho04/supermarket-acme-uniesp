package br.com.notification.repository;

import br.com.notification.model.NotificationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<NotificationEntity, Long>  {

    public NotificationEntity getNotificationByCpfCustomer(String cpf);
}
