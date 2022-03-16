package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.service.wilayah.WilayahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wilayah")
public class WilayahController {

    @Autowired
    private WilayahService wilayahService;

    @GetMapping("/propinsi/getAll")
    public ResponseEntity<?> getPropinsiAll(@RequestParam(value = "page", required = true) int page,
                                    @RequestParam(value = "size", required = true) int size){
        return ResponseEntity.ok().body(wilayahService.getPropinsiAll(page, size));
    }

    @GetMapping("/propinsi/getId/{id}")
    public ResponseEntity<?> getPropinsiId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(wilayahService.getPropinsiId(id));
    }

    @GetMapping("/kota/getId/{id}")
    public ResponseEntity<?> getKotaId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(wilayahService.getKotaId(id));
    }

    @GetMapping("/kecamatan/getId/{id}")
    public ResponseEntity<?> getKecamatanId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(wilayahService.getKecamatanId(id));
    }

    @GetMapping("/kelurahan/getId/{id}")
    public ResponseEntity<?> getKelurahanId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(wilayahService.getKelurahanId(id));
    }

}
