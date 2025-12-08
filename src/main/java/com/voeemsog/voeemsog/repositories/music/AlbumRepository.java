package com.voeemsog.voeemsog.repositories.music;

import com.voeemsog.voeemsog.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> getAlbumById(UUID id);
    void deleteAlbumById(UUID id);
}
