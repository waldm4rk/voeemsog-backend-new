package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.models.Album;
import com.voeemsog.voeemsog.models.Track;
import com.voeemsog.voeemsog.services.music.AlbumService;
import com.voeemsog.voeemsog.services.music.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class MusicController {
    private final TrackService trackService;
    private final AlbumService albumService;

    @GetMapping("/albums")
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }
    @GetMapping("/tracks/{albumId}")
    public List<Track> getTracksByAlbum(@PathVariable UUID albumId){
        return trackService.getTracksByAlbum(albumId);
    }
    @GetMapping("/album/{albumId}")
    public Album getAlbum(@PathVariable UUID albumId){
        return albumService.getAlbumById(albumId);
    }
}
