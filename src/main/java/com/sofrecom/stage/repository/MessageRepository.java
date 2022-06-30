package com.sofrecom.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Message;



@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	@Query("select m from Message m order by m.date_message ")
	public List<Message> showMessageRepository();
}
