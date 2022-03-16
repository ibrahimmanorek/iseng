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
    private String latitude;
    private String longitude;
    private Long kategoriId;
    private Long propinsiId;
    private Long kotaId;
    private Long kecamatanId;
    private Long kelurahanId;
}
