package com.gocampers.gocampers.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Sort;
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
    public ConnectionQuery<CampInfo> searchCampsQuery(int first, CampSearchParamsDto params){
        JPAQuery<CampInfo> results = getCampInfoByCondition(params);
        int totalCount = results.fetch().size();
        List<CampInfo> queryResults = results.limit(first).fetch();
        return new ConnectionQuery<CampInfo>(totalCount,queryResults);
    }

    @Override
    public ConnectionQuery<CampInfo> searchCampsQueryAfterCursor(int first, int after, CampSearchParamsDto params){
        JPAQuery<CampInfo> results = getCampInfoByCondition(params);
        int totalCount = results.fetch().size();
        List<CampInfo> queryResults = results.offset(after).limit(first).fetch();
        return new ConnectionQuery<CampInfo>(totalCount,queryResults);
    }

    @Override
    public List<CampInfo> searchCamps(int first, CampSearchParamsDto params){
        return getCampInfoByCondition(params).limit(first).fetch();
    }

    @Override
    public List<CampInfo> searchCampsAfterCursor(int first, int after, CampSearchParamsDto params){
        return getCampInfoByCondition(params).offset(after).limit(first).fetch();
    }

    // @Override
    // public List<CampInfo> searchCampsAfterCursor(int first, int after, CampSearchParamsDto params, Sort orderOption){
    //     return getCampInfoByCondition(params).limit(first).fetch();
    // }

    private JPAQuery<CampInfo> getCampInfoByCondition(CampSearchParamsDto params) {
        String[] siteBottom = {params.getSiteBottomCl1(),params.getSiteBottomCl2(),params.getSiteBottomCl3(),params.getSiteBottomCl4(),params.getSiteBottomCl5()};
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
                neSiteBottomCl(siteBottom),                
                containsSbrsCl(params.getSbrsCl()),
                eqTrlerAcmpnyAt(params.getTrlerAcmpnyAt()),
                eqCaravAcmpnyAt(params.getCaravAcmpnyAt()),
                containsAnimalCmgCl(params.getAnimalCmgCl())
            )
            .orderBy(qCampInfo.contentId.desc());
    }

    private BooleanExpression containsFacltNm(String facltNm){
        return facltNm == null ? null : qCampInfo.facltNm.contains(facltNm);
    }
    private BooleanExpression inDoNm(String doNm){
        return doNm == null ? null : qCampInfo.doNm.in(doNm.split(","));
        // return doNm == null ? null : qCampInfo.doNm.eq(doNm);
    }
    private BooleanExpression eqSigunguNm(String sigunguNm){
        return sigunguNm == null ? null : qCampInfo.sigunguNm.eq(sigunguNm);
    }
    private BooleanExpression inThemaEnvrnCl(String themaEnvrnCl){
        // return themaEnvrnCl == null ? null : qCampInfo.themaEnvrnCl.contains(themaEnvrnCl);
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

    private BooleanExpression neSiteBottomCl(String[] siteBottomCl){
        return siteBottomCl == null ? null : Expressions.anyOf(
            neSiteBottomCl1(siteBottomCl[0]),
            neSiteBottomCl2(siteBottomCl[1]),
            neSiteBottomCl3(siteBottomCl[2]),
            neSiteBottomCl4(siteBottomCl[3]),
            neSiteBottomCl5(siteBottomCl[4])
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
        return animalCmgCl == null ? null : qCampInfo.animalCmgCl.contains("가능");
    }
}
