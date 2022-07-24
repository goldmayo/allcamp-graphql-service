package com.gocampers.gocampers.repository;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.domain.paging.ConnectionQuery;

public interface CustomQueryRepository {
    ConnectionQuery<CampInfo> searchCampsQuery(int first, CampSearchParamsDto params);
    ConnectionQuery<CampInfo> searchCampsQueryAfterCursor(int first, int after, CampSearchParamsDto params);
}