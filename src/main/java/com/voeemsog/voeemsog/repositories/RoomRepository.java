package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
