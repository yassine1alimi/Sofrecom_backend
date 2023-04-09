package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
