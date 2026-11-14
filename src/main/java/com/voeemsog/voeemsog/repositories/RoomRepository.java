package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> getRoomById(UUID id);

    List<Room> getRoomsByCodeContainingIgnoreCase(String code);
    List<Room> getRoomsByDangerType(String dangerType);

    List<Room> getAllByOrderByDateDesc(Pageable pageable);

    List<Room> getRoomsBySize(String size);

    Optional<Room> deleteRoomById(UUID id);

    @NativeQuery("SELECT id FROM rooms WHERE code = ?1")
    Optional<UUID> getIdByCode(String code);
    @Query("""
    select distinct r from Room r
    join r.translations t
    where t.lang = :lang
    order by r.date desc, r.id desc
    """)
    List<Room> getRoomsByLang(@Param("lang") String lang, Pageable pageable);

    @Query("""
    select count(r) from Room r
    join r.translations t
    where t.lang = :lang
""")
    long countRoomsByLang(@Param("lang") String lang);

    void deleteRoomByCode(String code);

    Room getRoomByCode(String code);
}
