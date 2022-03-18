package com.backend.tempatusaha.controller.admin;

import com.backend.tempatusaha.dto.request.DistanceRequest;
import com.backend.tempatusaha.dto.request.LapanganRequest;
import com.backend.tempatusaha.service.admin.lapangan.LapanganAdminService;
import com.backend.tempatusaha.service.lapangan.LapanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/lapangan")
public class LapanganAdminController {

    @Autowired
    private LapanganAdminService lapanganAdminService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", required = true) int page,
                                    @RequestParam(value = "size", required = true) int size){
        return ResponseEntity.ok().body(lapanganAdminService.getAll(page, size));
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(lapanganAdminService.getId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LapanganRequest request){
        return ResponseEntity.ok().body(lapanganAdminService.save(request));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id,
                                    @RequestBody LapanganRequest request){
        return ResponseEntity.ok().body(lapanganAdminService.update(id, request));
    }
}
