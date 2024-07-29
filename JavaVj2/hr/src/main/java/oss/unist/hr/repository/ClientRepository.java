package oss.unist.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import oss.unist.hr.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByName(String name);
}
