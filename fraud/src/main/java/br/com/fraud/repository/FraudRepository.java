package br.com.fraud.repository;

import br.com.fraud.model.FraudEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends PagingAndSortingRepository<FraudEntity, Long> {

    FraudEntity findByCostumerId(Long costumerId);
    FraudEntity findByCpf(String cpf);


}
