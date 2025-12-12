package com.voeemsog.voeemsog.controllers;

import com.sun.net.httpserver.HttpsServer;
import com.voeemsog.voeemsog.dto.GoalRequest;
import com.voeemsog.voeemsog.dto.RoomDTO;
import com.voeemsog.voeemsog.models.*;
import com.voeemsog.voeemsog.repositories.DonationGoalRepository;
import com.voeemsog.voeemsog.repositories.RoomRepository;
import com.voeemsog.voeemsog.repositories.RoomTranslationRepository;
import com.voeemsog.voeemsog.repositories.StoryRepository;
import com.voeemsog.voeemsog.repositories.music.AlbumRepository;
import com.voeemsog.voeemsog.services.DonationGoalService;
import com.voeemsog.voeemsog.services.PostService;
import com.voeemsog.voeemsog.services.RoomService;
import com.voeemsog.voeemsog.services.StoryService;
import com.voeemsog.voeemsog.services.music.AlbumService;
import com.voeemsog.voeemsog.services.music.TrackService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AdminController {
    private final RoomService roomService;
    private final RoomRepository roomRepository;
    private final RoomTranslationRepository roomTranslationRepository;
    private final PostService postService;
    private final StoryService storyService;
    private final StoryRepository storyRepository;
    private final DonationGoalService donationGoalService;
    private final AlbumService albumService;
    private final TrackService trackService;
    // Null room crashes server
    // FIXED?
    @PostMapping("/room")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        if(room == null) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(roomRepository.saveAndFlush(room), HttpStatusCode.valueOf(201));
    }
    @PutMapping("/room")
    public ResponseEntity<Room> upsertRoom(@RequestBody Room room){
        UUID id = roomService.getIdByCode(room.getCode());
        if(id != null) room.setId(id);;
        roomRepository.saveAndFlush(room);
        return new ResponseEntity<>(room, HttpStatusCode.valueOf(201));
    }

    @PostMapping("/room/translation")
    public ResponseEntity<?> createTranslation(@RequestBody RoomTranslation translation, @RequestParam UUID roomId){
        Room room = roomService.getRoomById(roomId).orElse(null);
        if(room == null){
            return new ResponseEntity<>(false, HttpStatusCode.valueOf(204));
        }
        translation.setRoom(room);
        roomTranslationRepository.saveAndFlush(translation);
        return new ResponseEntity<>(true, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/room/translation")
    public ResponseEntity<?> upsertTranslation(@RequestBody RoomTranslation translation, @RequestParam UUID roomId){
        Room room = roomService.getRoomById(roomId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found")
        );
        RoomTranslation translation1 = roomTranslationRepository.getRoomTranslationByRoomAndLang(roomService.getRoomById(roomId).orElse(null), translation.getLang());
        if(translation1 != null){
            translation1.setDescription(translation.getDescription());
            translation1.setName(translation.getName());

            roomTranslationRepository.saveAndFlush(translation1);
            return new ResponseEntity<>(true, HttpStatusCode.valueOf(201));
        }
        translation.setRoom(room);
        roomTranslationRepository.saveAndFlush(translation);
        return new ResponseEntity<>(true, HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable UUID id){
        roomService.deleteRoom(id);
        return new ResponseEntity<>(false, HttpStatusCode.valueOf(204));
    }

    @DeleteMapping("/room/code/{code}")
    public ResponseEntity<?> deleteRoomByCode(@PathVariable String code){
        roomService.deleteRoomsAndTranslations(code);
        return ResponseEntity.ok(null);
    }

    // Posts
    @PostMapping("/post")
    public void createPost(@RequestBody Post post){
        postService.createPost(post);
    }
    @Transactional
    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable UUID postId){
        postService.deletePost(postId);
    }
    // Stories
    @PostMapping("/story")
    public void upsertStory(@RequestBody Story story){
        storyService.createStory(story);
    }
    @DeleteMapping("/story/{storyId}")
    public void deleteStory(@PathVariable UUID storyId){
        storyService.deleteStory(storyId);
    }

    // Donations
    @PostMapping("/donations/upsert/{title}")
    public ResponseEntity<?> createDonationGoal(@PathVariable String title, @RequestParam String amount){
        float _amount = Float.parseFloat(amount);
        boolean activeGoalExists = donationGoalService.getCurrentGoal().isPresent();

        GoalRequest req = new GoalRequest();
        req.setTitle(title);
        req.setTargetAmount(_amount);
        if(activeGoalExists){
            donationGoalService.updateGoal(req);
            return ResponseEntity.ok(null);
        }
        donationGoalService.createGoal(req);
        return ResponseEntity.ok(null);
    }

    // Music
    @PostMapping("/music/album")
    public ResponseEntity<?> upsertAlbum(@RequestBody Album album){
        albumService.postAlbum(album);
        return ResponseEntity.ok(album);
    }
    @DeleteMapping("/music/album/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable UUID albumId){
        albumService.deleteAlbum(albumId);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/music/track")
    public ResponseEntity<?> addTrack(@RequestBody Track track){
        try {
            trackService.createTrack(track);
        }catch(IllegalStateException e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
        return ResponseEntity.ok(track);
    }
    @DeleteMapping("/music/track")
    public ResponseEntity<?> deleteTrack(@RequestParam UUID albumId, @RequestParam int trackOrder){
        try {
            trackService.deleteTrack(albumId, trackOrder);
        } catch(IllegalStateException e){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
        return ResponseEntity.ok(null);
    }
}
