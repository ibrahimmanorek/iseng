package com.backend.tempatusaha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WilayahRequest {
    private long propinsiId;
    private long kotaId;
    private long kecamatanId;
    private long kelurahanId;
}
