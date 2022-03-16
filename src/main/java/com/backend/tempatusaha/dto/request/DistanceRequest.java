package com.backend.tempatusaha.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DistanceRequest {
    private String latitude;
    private String longitude;
    private String distance;
}
