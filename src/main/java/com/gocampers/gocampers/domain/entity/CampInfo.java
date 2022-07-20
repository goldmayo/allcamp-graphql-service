package com.gocampers.gocampers.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CampInfo {
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
}

/*
addr1: "경상남도 창원시 의창구 북면 달천길 150 ",주소
addr2: "달천공원오토캠핑장 ",주소상세
trsagntNo: 0,관광사업자번호,
hvofBgnde: 휴무기간 시작일,
hvofEnddle: 휴무기간 종료일,
glampInnerFclty:글램핑내부시설,
caravInnerFclty:카라반 내부시설,
tooltip: 툴팁,
tourEraCl:여행시기,
direction: 오시는길 컨텐츠,
allar: 5170,전체면적,
animalCmgCl: "가능(소형견)",애완동물출입
autoSiteCo: 31,주요시설 자동차야영장
bizrno: "791-20-00628",사업자번호
brazierCl: "개별",화로대
caravAcmpnyAt: "N",개인 카라반 동반 여부(Y:사용, N:미사용)
caravSiteCo: 4,주요시설 카라반
clturEventAt: "N",자체문화행사 여부(Y:사용, N:미사용)
clturEvent:자체문화행사명,
contentId: 704,콘텐츠ID
createdtime: "2021-11-23 15:37:37 ",등록일
doNm: "경상남도",도
eqpmnLendCl: "텐트,화로대,난방기구,식기,침낭",캠핑장비대여
exprnProgrm:"아로마향초만들기,천연염색,캠핑요리"
exprnProgrmAt: "N",체험프로그램 여부(Y:사용, N:미사용)
extshrCo: 50,소화기 개수 (단위 : 개)
facltDivNm: "지자체",사업주체.구분
facltNm: "달천공원오토캠핑장 ",야영장명
featureNm: "천주산의 계곡이 좋고 도심과 가까움",특징
fireSensorCo: 0,화재감지기 개수
firstImageUrl: "https://gocamping.or.kr/upload/camp/704/thumb/thumb_720_6835LN3Y4PG4YQDWJo1y7Vvr.jpg",대표이미지
frprvtSandCo: 0,방화사 개수 (단위 : 개)
frprvtWrppCo: 2,방화수 개수 (단위 : 개)
glampSiteCo: 0,주요시설 글램핑
gnrlSiteCo: 0,주요시설 일반야영장
homepage: "http://camp.changwon.go.kr/",홈페이지
induty: "일반야영장,자동차야영장,카라반",업종
indvdlCaravSiteCo: 0,주요시설 개인 카라반
insrncAt: "Y",영업배상책임보험 가입여부(Y:사용, N:미사용)
intro: "달천공원 오토캠핑장은 창원시 북면 천주산 달천계곡에 위치하고 있다. 자연이 주는 맑은 계곡과 천주산 자락의 기운을 느낄 수 있는 캠핑장이다. 캠핑장 바로 앞으로는 달천계곡이 흐르고 있어 여름철 물놀이 장소로 좋다. 또한 천주산 등산로가 있어 등산하기에도 좋은 곳이다. 봄철 진달래가 만개 할 때는 등산도 하면서 꽃도 보고, 여름에는 계곡에서 시원한 물놀이도 하면서, 즐기는 일석이조의 캠핑장이다. 달천캠핑장은 화장실, 샤워장은 아래쪽 사이트, 취사장은 위쪽 사이트를 이용해야 하므로 무조건 위, 아래로 이동을 해야 한다. 전기 사용에 무선인터넷도 가능하며 화장실, 샤워장이 깨끗하고 온수도 잘 나온다. 샤워실 이용시간(오전8시~10시, 오후7시~9시)이 정해져 있으므로 사전에 확인하고 이용하는 것이 좋다. 개수대에 에어컨이 설치되어 있어 한여름에도 시원하게 설거지를 할 수 있다.",소개
lctCl: "산,숲,계곡,도심",입지구분
lineIntro: "사계절 서로 다른 매력의 캠핑을 즐길 수 있는 곳",한줄소개
manageNmpr: 3,상주관리인원
manageSttus: "운영",운영상태.관리상태
mangeDivNm: "위탁",운영주체.관리주체 (직영, 위탁)
mapX: 128.5998171,경도(X)
mapY: 35.2822939,위도(Y)
mgcDiv: "달천오토캠핑장",운영기관.관리기관
modifiedtime: "2021-11-23 15:37:37 ",수정일
operDeCl: "평일+주말",운영일
operPdCl: "봄,여름,가을,겨울",운영기간
posblFcltyCl: "계곡 물놀이,산책로,강/물놀이",주변이용가능시설
posblFcltyEtc:,주변이용가능시설 기타
prmisnDe: "2015-04-28",인허가일자
resveCl: "온라인실시간예약",예약 구분
resveUrl: "https://camp.changwon.go.kr/sub/03_02.jsp",예약 페이지
sbrsCl: "전기,무선인터넷,장작판매,온수,놀이터,운동시설",부대시설
sbrsEtc: "어란아 놀이기구 소량 에어바운스",부대시설 기타
sigunguNm: "창원시",시군구
siteBottomCl1: 0,잔디
siteBottomCl2: 0,파쇄석
siteBottomCl3: 31,테크
siteBottomCl4: 0,자갈
siteBottomCl5: 0,맨흙
siteMg1Co: 31,사이트 크기1 수량 (단위 : 개)
siteMg1Vrticl: 4,사이트 크기1 세로 (단위 : M)
siteMg1Width: 4,사이트 크기1 가로 (단위 : M)
siteMg2Co: 0,사이트 크기2 세로 (단위 : M)
siteMg2Vrticl: 0,사이트 크기2 세로 (단위 : M)
siteMg2Width: 0,사이트 크기1 가로 (단위 : M)
siteMg3Co: 0,사이트 크기3 수량 (단위 : 개)
siteMg3Vrticl: 0,사이트 크기3 세로 (단위 : M)
siteMg3Width: 0,사이트 크기3 가로 (단위 : M)
sitedStnc: 0,
swrmCo: 1,샤워실 개수 (단위 : 개)
tel: "055-262-0752",
themaEnvrnCl: "일출명소,일몰명소,봄꽃여행,여름물놀이,걷기길",테마환경
toiletCo: 1,화장실 개수 (단위 : 개)
trlerAcmpnyAt: "Y",개인 트레일러 동반 여부(Y:사용, N:미사용)
wtrplCo: 1,개수대 개수 (단위 : 개)
zipcode: 641871우편번호
 */