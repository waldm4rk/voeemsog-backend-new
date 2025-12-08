package com.voeemsog.voeemsog.services.music;

import com.voeemsog.voeemsog.models.Album;
import com.voeemsog.voeemsog.models.Track;
import com.voeemsog.voeemsog.repositories.music.AlbumRepository;
import com.voeemsog.voeemsog.repositories.music.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;
    private final AlbumRepository albumRepository;

    public List<Track> getTracksByAlbum(UUID albumId){
        Album album = albumRepository.getAlbumById(albumId).orElseThrow(() -> new IllegalStateException("No album with that id"));
        return trackRepository.getTracksByAlbum(album);
    }
    public void createTrack(Track track){
        trackRepository.saveAndFlush(track);
    }
    public void deleteTrack(UUID albumId, int trackOrder){
        Album album = albumRepository.getAlbumById(albumId).orElseThrow(() -> new IllegalStateException("No album with that id"));
        trackRepository.deleteTrackByAlbumAndOrder(album, trackOrder);
    }
}
