package com.voeemsog.voeemsog.repositories.music;

import com.voeemsog.voeemsog.models.Album;
import com.voeemsog.voeemsog.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrackRepository extends JpaRepository<Track, UUID> {
    List<Track> getTracksByAlbumOrderByOrder(Album album);
    void deleteTrackByAlbumAndOrder(Album album, int order);
}
