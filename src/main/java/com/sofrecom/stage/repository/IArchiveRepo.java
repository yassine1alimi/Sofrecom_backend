package com.sofrecom.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofrecom.stage.models.Archive;


@Repository
public interface IArchiveRepo extends JpaRepository<Archive, Long>{

}
