package com.gocampers.gocampers.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gocampers.gocampers.domain.dto.CampSearchParamsDto;
import com.gocampers.gocampers.domain.entity.CampInfo;
import com.gocampers.gocampers.domain.entity.QCampInfo;
import com.gocampers.gocampers.domain.paging.ConnectionQuery;
import com.gocampers.gocampers.repository.CustomQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CustomQueryRepositoryImpl implements CustomQueryRepository{
    @Autowired
    private  JPAQueryFactory jpaQueryFactory;
    private final QCampInfo qCampInfo = QCampInfo.campInfo;
    
    @Override
    public ConnectionQuery<CampInfo> searchCampsQueryForward(int first, CampSearchParamsDto params){
        JPAQuery<CampInfo> results = getCampInfoByCondition(params,true);
        int totalCount = results.fetch().size();
        List<CampInfo> queryResults = results.limit(first).fetch();
        return new ConnectionQuery<CampInfo>(totalCount,queryResults);
    }
    @Override
    public ConnectionQuery<CampInfo> searchCampsQueryBackward(int last, CampSearchParamsDto params){
        JPAQuery<CampInfo> results = getCampInfoByCondition(params,false);
        int totalCount = results.fetch().size();
        List<CampInfo> queryResults = results.limit(last).fetch();
        return new ConnectionQuery<CampInfo>(totalCount,queryResults);
    }

    @Override
    public ConnectionQuery<CampInfo> searchCampsQueryAfterCursor(int first, int after, CampSearchParamsDto params){
        JPAQuery<CampInfo> results = getCampInfoByConditionCursor(params,after,true);
        int totalCount = getCampInfoByCondition(params,true).fetch().size();
        List<CampInfo> queryResults = results.limit(first).fetch();
        return new ConnectionQuery<CampInfo>(totalCount,queryResults);
    }

    public ConnectionQuery<CampInfo> searchCampsQueryBeforeCursor(int last, int before, CampSearchParamsDto params){
        JPAQuery<CampInfo> results = getCampInfoByConditionCursor(params,before,false);
        int totalCount = getCampInfoByCondition(params,false).fetch().size();
        List<CampInfo> queryResults = results.limit(last).fetch();
        Collections.sort(queryResults, (c1, c2) ->c2.getContentId()- c1.getContentId());
        return new ConnectionQuery<CampInfo>(totalCount,queryResults);
       
    }

    private JPAQuery<CampInfo> getCampInfoByCondition(CampSearchParamsDto params, boolean descending) {
        ArrayList<String> siteBottoms = new ArrayList<>();
        siteBottoms.add(params.getSiteBottomCl1());
        siteBottoms.add(params.getSiteBottomCl2());
        siteBottoms.add(params.getSiteBottomCl3());
        siteBottoms.add(params.getSiteBottomCl4());
        siteBottoms.add(params.getSiteBottomCl5());
        return jpaQueryFactory
            .selectFrom(qCampInfo)
            .where(
                containsFacltNm(params.getFacltNm()),
                inDoNm(params.getDoNm()),
                eqSigunguNm(params.getSigunguNm()),
                inFacltDivNm(params.getFacltDivNm()),
                inThemaEnvrnCl(params.getThemaEnvrnCl()),
                inLctCl(params.getLctCl()),
                inInduty(params.getInduty()),             
                neSiteBottomCl(siteBottoms),                
                containsSbrsCl(params.getSbrsCl()),
                eqTrlerAcmpnyAt(params.getTrlerAcmpnyAt()),
                eqCaravAcmpnyAt(params.getCaravAcmpnyAt()),
                containsAnimalCmgCl(params.getAnimalCmgCl())
            )
            .orderBy(qCampInfo.contentId.desc());
    }

    private JPAQuery<CampInfo> getCampInfoByConditionCursor(CampSearchParamsDto params,int cursor, boolean forward) {
            ArrayList<String> siteBottoms = new ArrayList<>();
                siteBottoms.add(params.getSiteBottomCl1());
                siteBottoms.add(params.getSiteBottomCl2());
                siteBottoms.add(params.getSiteBottomCl3());
                siteBottoms.add(params.getSiteBottomCl4());
                siteBottoms.add(params.getSiteBottomCl5());
                return jpaQueryFactory
                    .selectFrom(qCampInfo)
                    .where(
                        forward ? lowerThanContentId(cursor) : greaterThanContentId(cursor),
                        containsFacltNm(params.getFacltNm()),
                        inDoNm(params.getDoNm()),
                        eqSigunguNm(params.getSigunguNm()),
                        inFacltDivNm(params.getFacltDivNm()),
                        inThemaEnvrnCl(params.getThemaEnvrnCl()),
                        inLctCl(params.getLctCl()),
                        inInduty(params.getInduty()),             
                        neSiteBottomCl(siteBottoms),                
                        containsSbrsCl(params.getSbrsCl()),
                        eqTrlerAcmpnyAt(params.getTrlerAcmpnyAt()),
                        eqCaravAcmpnyAt(params.getCaravAcmpnyAt()),
                        containsAnimalCmgCl(params.getAnimalCmgCl())
                    )
                    .orderBy(forward ? qCampInfo.contentId.desc() : qCampInfo.contentId.asc());
    }

    private BooleanExpression greaterThanContentId(int contentId){
        return qCampInfo.contentId.gt(contentId);
    }
    private BooleanExpression lowerThanContentId(int contentId){
        return qCampInfo.contentId.lt(contentId);
    }
    private BooleanExpression containsFacltNm(String facltNm){
        return facltNm == null ? null : qCampInfo.facltNm.contains(facltNm);
    }
    private BooleanExpression inDoNm(String doNm){
        return doNm == null ? null : qCampInfo.doNm.in(doNm.split(","));
    }
    private BooleanExpression eqSigunguNm(String sigunguNm){
        return sigunguNm == null ? null : qCampInfo.sigunguNm.eq(sigunguNm);
    }
    private BooleanExpression inThemaEnvrnCl(String themaEnvrnCl){
        return themaEnvrnCl == null ? null : qCampInfo.themaEnvrnCl.in(themaEnvrnCl.split(","));
    }
    private BooleanExpression inFacltDivNm(String facltDivNm){
        return facltDivNm == null ? null : qCampInfo.facltDivNm.in(facltDivNm.split(","));
    }
    private BooleanExpression inLctCl(String lctCl){
        return lctCl == null ? null : qCampInfo.lctCl.in(lctCl.split(","));
    }
    private BooleanExpression inInduty(String induty){
        return induty == null ? null : qCampInfo.induty.in(induty.split(","));
    }
    private BooleanExpression neSiteBottomCl1(String siteBottomCl1){
        return siteBottomCl1 == null ? null : qCampInfo.siteBottomCl1.ne(0);
    }
    private BooleanExpression neSiteBottomCl2(String siteBottomCl2){
        return siteBottomCl2 == null ? null : qCampInfo.siteBottomCl2.ne(0);
    }
    private BooleanExpression neSiteBottomCl3(String siteBottomCl3){
        return siteBottomCl3 == null ? null : qCampInfo.siteBottomCl3.ne(0);
    }
    private BooleanExpression neSiteBottomCl4(String siteBottomCl4){
        return siteBottomCl4 == null ? null : qCampInfo.siteBottomCl4.ne(0);
    }
    private BooleanExpression neSiteBottomCl5(String siteBottomCl5){
        return siteBottomCl5 == null ? null : qCampInfo.siteBottomCl5.ne(0);
    }
    private BooleanExpression neSiteBottomCl(ArrayList<String> siteBottomCl){
        return siteBottomCl == null ? null : Expressions.allOf(
            neSiteBottomCl1(siteBottomCl.get(0)),
            neSiteBottomCl2(siteBottomCl.get(1)),
            neSiteBottomCl3(siteBottomCl.get(2)),
            neSiteBottomCl4(siteBottomCl.get(3)),
            neSiteBottomCl5(siteBottomCl.get(4))
        );
    }
    private BooleanExpression containsSbrsCl(String sbrsCl){
        return sbrsCl == null ? null : qCampInfo.sbrsCl.contains(sbrsCl);
    }
    private BooleanExpression eqTrlerAcmpnyAt(String trlerAcmpnyAt){
        return trlerAcmpnyAt == null ? null : qCampInfo.trlerAcmpnyAt.eq("Y");
    }
    private BooleanExpression eqCaravAcmpnyAt(String caravAcmpnyAt){
        return caravAcmpnyAt == null ? null : qCampInfo.caravAcmpnyAt.eq("Y");
    }
    private BooleanExpression containsAnimalCmgCl(String animalCmgCl){
        return animalCmgCl == null ? null : qCampInfo.animalCmgCl.startsWith("불").not();
    }
}
