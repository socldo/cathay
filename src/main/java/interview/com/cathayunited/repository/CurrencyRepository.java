package interview.com.cathayunited.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import interview.com.cathayunited.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findOneByCode(String code);

}
