package com.doodledoodle.backend.draw.controller;

import com.doodledoodle.backend.draw.dto.request.DrawRequest;
import com.doodledoodle.backend.draw.dto.response.DrawResponse;
import com.doodledoodle.backend.draw.service.DrawService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/draws")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DrawController {
    DrawService drawService;

    @PostMapping
    public ResponseEntity<DrawResponse> saveDraw(
            @ModelAttribute DrawRequest request) throws IOException {
        return ResponseEntity.ok(drawService.saveDraw(request));
    }
}
