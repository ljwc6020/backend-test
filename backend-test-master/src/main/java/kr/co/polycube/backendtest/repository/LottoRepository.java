package kr.co.polycube.backendtest.repository;

import kr.co.polycube.backendtest.domain.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Long> {
    List<Lotto> findByUserId(Long userId);

}