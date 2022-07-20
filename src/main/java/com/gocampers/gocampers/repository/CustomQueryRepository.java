package com.gocampers.gocampers.repository;

import java.util.List;

// import org.springframework.data.domain.Sort;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;

public interface CustomQueryRepository {
    List<CampInfo> searchCamps(int first, CampSearchParamsDto params);
    List<CampInfo> searchCampsAfterCursor(int first, int after, CampSearchParamsDto params);
    // List<CampInfo> searchCampsAfterCursor(int first, int after, CampSearchParamsDto params, Sort orderOption);
}