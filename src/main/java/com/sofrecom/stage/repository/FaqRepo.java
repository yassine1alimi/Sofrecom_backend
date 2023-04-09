package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Faq;
@Repository
public interface FaqRepo extends JpaRepository<Faq, Long> {

}
