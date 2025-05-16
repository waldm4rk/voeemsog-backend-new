package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> getRoomById(UUID id);

    List<Room> getRoomsByCodeContainingIgnoreCase(String code);
    List<Room> getRoomsByDangerType(String dangerType);

    List<Room> getRoomsBySize(String size);
}
