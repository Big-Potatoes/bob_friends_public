package com.bobfriend.bobfriends.repository;

import com.bobfriend.bobfriends.domain.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
}
