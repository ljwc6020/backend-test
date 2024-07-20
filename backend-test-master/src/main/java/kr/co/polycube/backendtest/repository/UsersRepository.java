package kr.co.polycube.backendtest.repository;

import kr.co.polycube.backendtest.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


}