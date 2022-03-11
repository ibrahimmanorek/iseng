package com.backend.tempatusaha.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private long totalAllData;
    private long currentPage;
    private long totalPage;
    private T details;
}
