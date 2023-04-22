package com.bobfriend.bobfriends.repository;

import com.bobfriend.bobfriends.domain.recruit.PickupImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupImageRepository extends JpaRepository<PickupImage, Long> {
}
