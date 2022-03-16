package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.request.DistanceRequest;
import com.backend.tempatusaha.dto.request.LapanganRequest;
import com.backend.tempatusaha.service.lapangan.LapanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(lapanganService.getId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LapanganRequest request){
        return ResponseEntity.ok().body(lapanganService.save(request));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id,
                                    @RequestBody LapanganRequest request){
        return ResponseEntity.ok().body(lapanganService.update(id, request));
    }

    @PostMapping("/distance")
    public ResponseEntity<?> distance(@RequestParam(value = "page", required = true) int page,
                                      @RequestParam(value = "size", required = true) int size,
                                      @RequestBody DistanceRequest request){
        return ResponseEntity.ok().body(lapanganService.distance(page, size, request));
    }
}
