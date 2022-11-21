# 올캠핑(AllCamping) Graphql Server

![AllCamping Screen Shot][product-screenshot]

> 한국관광공사 고캠핑 api활용 전국의 캠핑장 정보 조회 토이 프로젝트.

## 기술스택

[![Ubuntu][ubuntu]][ubuntu-url]
[![Java][java]][java-url][![SpringBoot][springboot]][springboot-url][![Maven][maven]][maven-url][![Querydsl][querydsl]][querydsl-url]
[![GraphQL][graphql]][graphql-url][![Relay][relay]][relay-url]
[![MariaDB][mariadb]][mariadb-url]

## 설치 방법

```sh
> git clone https://github.com/your_username_/Project-Name.git
```

- /resource/application-YOUR-API-KEY.properties
  API key를 발급받아 수정하고 파일명을 application-API-KEY.properties으로 변경합니다.

- /resource/application-YOUR-DB.properties
  MariaDB설치 후 DB이름, 사용자, 비밀번호를 수정하고 파일명을 application-DB.properties로 변경합니다.

```sh
> mvn install
> sudo service mariadb start
> mvn spring-boot:run
```

## 사용 예제

```graphql
# Return CampInfo by contentId
findCampById(contentId: Int): CampInfo

# Return Relay Connection
searchCamps(
first: Int
after: String
last: Int
before: String
params: CampSearchParamsDto
): CampInfoConnection
```

```graphql
query searchWithParamsForward($first: Int $params: CampSearchParamsDto) {
  searchCamps(first: $first, params: $params) {
    totalCounts
    edges {
      node {
        contentId
        firstImageUrl
        doNm
        sigunguNm
        facltNm
        lineIntro
        addr1
        tel
        sbrsCl
        #...etc in CampInfo
      }
      cursor
    }
    pageInfo {
      startCursor
      endCursor
      hasNextPage
      hasPreviousPage
    }
  }
}
variable:{
  "params": {
    "doNm": "강원도,경기도",
    "induty": "일반야영장"
  }
}
```

서버 실행 후 "http://localhost:8080/graphiql" 에서 테스트할 수 있습니다.

매일 오전 3시 정각에 한국관광공사 api를 호출하여 DB를 업데이트 합니다.

## 개발 환경 설정

```sh
make install
npm test
```

## 정보

현승재 - dudtod1596@gmail.com

[https://github.com/goldmayo/allcamp-graphql-service](https://github.com/goldmayo/allcamp-graphql-service)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[java]: https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white
[java-url]: https://www.oracle.com/java
[springboot]: https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white
[springboot-url]: https://spring.io/projects/spring-boot
[maven]: https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=Maven&logoColor=white
[maven-url]: https://maven.apache.org/
[querydsl]: https://img.shields.io/badge/Querydsl-0285c9?style=flat-square&logo=Querydsl&logoColor=white
[querydsl-url]: http://querydsl.com/
[graphql]: https://img.shields.io/badge/GraphQL-E10098?style=flat-square&logo=GraphQL&logoColor=white
[graphql-url]: https://graphql.org/
[relay]: https://img.shields.io/badge/Relay-F26B00?style=flat-square&logo=Relay&logoColor=white
[relay-url]: https://relay.dev/
[mariadb]: https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=MariaDB&logoColor=white
[mariadb-url]: https://mariadb.org/
[ubuntu]: https://img.shields.io/badge/Ubuntu-18.04-E95420?style=flat-square&logo=Ubuntu&logoColor=white
[ubuntu-url]: https://ubuntu.com/
[product-screenshot]: image/allcamping_screenshot.PNG
