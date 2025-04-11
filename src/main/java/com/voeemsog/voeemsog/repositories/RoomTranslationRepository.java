package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.RoomTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTranslationRepository extends JpaRepository<RoomTranslation, Long> {
    RoomTranslation getRoomTranslationByIdAndLang(long id, String lang);
}
