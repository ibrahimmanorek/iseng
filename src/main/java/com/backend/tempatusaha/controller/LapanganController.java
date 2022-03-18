package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.request.DistanceRequest;
import com.backend.tempatusaha.service.lapangan.LapanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lapangan")
public class LapanganController {

    @Autowired
    private LapanganService lapanganService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", required = true) int page,
                                    @RequestParam(value = "size", required = true) int size){
        return ResponseEntity.ok().body(lapanganService.getAll(page, size));
    }

    @PostMapping("/distance")
    public ResponseEntity<?> distance(Authentication authentication,
                                      @RequestParam(value = "page", required = true) int page,
                                      @RequestParam(value = "size", required = true) int size){
        return ResponseEntity.ok().body(lapanganService.distance(authentication, page, size));
    }
}
