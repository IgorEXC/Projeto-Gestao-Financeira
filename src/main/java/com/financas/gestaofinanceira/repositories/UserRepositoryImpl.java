package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepositoryImpl extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Page<User> findAll(Pageable pageable);
}
