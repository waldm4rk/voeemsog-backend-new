package com.voeemsog.voeemsog.services.music;

import com.voeemsog.voeemsog.models.Album;
import com.voeemsog.voeemsog.repositories.music.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public List<Album> getAllAlbums(){
        return albumRepository.findAll();
    }
    public Album getAlbumById(UUID id){
        return albumRepository.getAlbumById(id).orElse(null);
    }
    public void postAlbum(Album album){
        Album oldAlbum = albumRepository.getAlbumById(album.getId()).orElse(null);
        if(oldAlbum == null){
            albumRepository.saveAndFlush(album);
            return;
        }
        oldAlbum.setDescription(album.getDescription());
        oldAlbum.setTitle(album.getTitle());
        oldAlbum.setCoverLink(album.getCoverLink());
        oldAlbum.setDescription_ru(album.getDescription_ru());
        albumRepository.save(oldAlbum);
    }
    public void deleteAlbum(UUID albumId){
        albumRepository.deleteAlbumById(albumId);
    }
}
