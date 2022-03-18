package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.request.KategoriRequest;
import com.backend.tempatusaha.service.kategori.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kategori")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", required = true) int page,
                                    @RequestParam(value = "size", required = true) int size){
        return ResponseEntity.ok().body(kategoriService.getAll(page, size));
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(kategoriService.getId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody KategoriRequest request){
        return ResponseEntity.ok().body(kategoriService.save(request));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id,
                                    @RequestBody KategoriRequest request){
        return ResponseEntity.ok().body(kategoriService.update(id, request));
    }
}
