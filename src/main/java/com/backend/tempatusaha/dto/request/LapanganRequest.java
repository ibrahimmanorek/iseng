package com.backend.tempatusaha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LapanganRequest {
    private String namaLapangan;
    private String namaPic;
    private String phoneNumber;
    private String email;
    private String address;
    private Long latitude;
    private Long longitude;
    private Long kategoriId;
}
