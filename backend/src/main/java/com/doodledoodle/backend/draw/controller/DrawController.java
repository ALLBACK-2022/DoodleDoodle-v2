package com.doodledoodle.backend.draw.controller;

import com.doodledoodle.backend.draw.dto.request.NewDrawRequset;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/draws")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DrawController {

  DrawService drawService;

//  @PostMapping
//  public ResponseEntity<DrawResponse> saveDraw(
//      @Valid @RequestPart DrawRequest drawRequest,
//      @Valid @RequestPart(value="image", required = false) MultipartFile fileName) {
//    return ResponseEntity.ok(drawService.saveDraw(drawRequest, fileName));
//  }
  @PostMapping
  public ResponseEntity<DrawResponse> saveDraw(
      @ModelAttribute NewDrawRequset newDrawRequset) {
    return ResponseEntity.ok(drawService.saveDraw(newDrawRequset));
  }
  @PostMapping("/123")
  public String test(
      @ModelAttribute NewDrawRequset newDrawRequset) {
    System.out.println("DrawNo" + newDrawRequset.getDrawNo());
    System.out.println("GameId" + newDrawRequset.getGameId());
    System.out.println("file" + newDrawRequset.getFileName());
    return "test";
  }
}