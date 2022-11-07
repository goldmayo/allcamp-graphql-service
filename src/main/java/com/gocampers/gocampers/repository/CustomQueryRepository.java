package com.gocampers.gocampers.repository;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.domain.paging.ConnectionQuery;

public interface CustomQueryRepository {
    ConnectionQuery<CampInfo> searchCampsQueryForward(int first, CampSearchParamsDto params);
    ConnectionQuery<CampInfo> searchCampsQueryBackward(int last, CampSearchParamsDto params);
    ConnectionQuery<CampInfo> searchCampsQueryAfterCursor(int first, int after, CampSearchParamsDto params);
    ConnectionQuery<CampInfo> searchCampsQueryBeforeCursor(int last, int before, CampSearchParamsDto params);
}