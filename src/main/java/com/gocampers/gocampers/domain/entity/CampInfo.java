package com.gocampers.gocampers.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Entity
@Data
public class CampInfo implements Persistable<Integer>{
    @Id
    private int contentId;
    private String addr1;
    private String addr2;
    private String trsagntNo;
    private int allar;
    private String animalCmgCl;
    private int autoSiteCo;
    private String bizrno;
    private String brazierCl;
    private String caravAcmpnyAt;
    private int caravSiteCo;
    private String clturEventAt;
    private String createdtime;
    private String doNm;
    private String eqpmnLendCl;
    private String exprnProgrm;
    private String exprnProgrmAt;
    private int extshrCo;
    private String facltDivNm;
    private String facltNm;
    private String featureNm;
    private int fireSensorCo;
    private String firstImageUrl;
    private int frprvtSandCo;
    private int frprvtWrppCo;
    private int glampSiteCo;
    private int gnrlSiteCo;
    private String homepage;
    private String induty;
    private int indvdlCaravSiteCo;
    private String insrncAt;
    private String intro;
    private String lctCl;
    private String lineIntro;
    private int manageNmpr;
    private String manageSttus;
    private String mangeDivNm;
    private Double mapX;
    private Double mapY;
    private String mgcDiv;
    private String modifiedtime;
    private String operDeCl;
    private String operPdCl;
    private String posblFcltyCl;
    private String posblFcltyEtc;
    private String prmisnDe;
    private String resveCl;
    private String resveUrl;
    private String sbrsCl;
    private String sbrsEtc;
    private String sigunguNm;
    private int siteBottomCl1;
    private int siteBottomCl2;
    private int siteBottomCl3;
    private int siteBottomCl4;
    private int siteBottomCl5;
    private int siteMg1Co;
    private int siteMg1Vrticl;
    private int siteMg1Width;
    private int siteMg2Co;
    private int siteMg2Vrticl;
    private int siteMg2Width;
    private int siteMg3Co;
    private int siteMg3Vrticl;
    private int siteMg3Width;
    private int sitedStnc;
    private int swrmCo;
    private String tel;
    private String themaEnvrnCl;
    private int toiletCo;
    private String trlerAcmpnyAt;
    private int wtrplCo;
    private String zipcode;
    private String hvofBgnde;
    private String hvofEnddle;
    private String direction;
    private String tooltip;
    private String glampInnerFclty;
    private String caravInnerFclty;
    private String clturEvent;
    private String tourEraCl;
    @Override
    public Integer getId() {
        return contentId;
    }

    @Override
    public boolean isNew() {
        return getId() == null; 
    }
    public interface ProjectContentIdAndModifiedDate {
        int getContentId();
        String getModifiedtime();
    }
}
