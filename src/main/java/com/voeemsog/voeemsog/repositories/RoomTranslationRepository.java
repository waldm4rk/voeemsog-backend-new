package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Room;
import com.voeemsog.voeemsog.models.RoomTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomTranslationRepository extends JpaRepository<RoomTranslation, UUID> {
    RoomTranslation getRoomTranslationByRoomAndLang(Room room, String lang);

    List<RoomTranslation> getRoomTranslationsByNameContainingIgnoreCaseAndLang(String name, String lang);
}
