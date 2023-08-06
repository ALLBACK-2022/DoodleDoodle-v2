package com.doodledoodle.backend.draw.controller;

import com.doodledoodle.backend.draw.dto.request.DrawRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.service.DrawService;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/draws")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DrawController {
    DrawService drawService;

    @PostMapping("games/{gameId}/player-no/{playerNo}")
    public ResponseEntity<DrawResponse> saveDraw(
            @PathVariable Long gameId,
            @PathVariable Integer playerNo,
            MultipartFile file) throws IOException {
        DrawRequest drawRequest = new DrawRequest(gameId, playerNo, file);
        return ResponseEntity.ok(drawService.saveDraw(drawRequest));
    }
}
