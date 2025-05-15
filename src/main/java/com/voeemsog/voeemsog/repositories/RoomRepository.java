package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Room getRoomById(UUID id);

    List<Room> getRoomsByCodeContainingIgnoreCase(String code);
    List<Room> getRoomsByDangerType(String dangerType);

    List<Room> getRoomsBySize(String size);
}
