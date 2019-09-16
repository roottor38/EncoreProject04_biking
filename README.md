biking
=============
encore mini project

## 프로젝트 소개
기존에 있는 지역 자전거 대여 서비스 따릉이를 커스터 마이징 하여 서비스 시스템을 구축

## 요구사항
가입된 사용자가 자전거 대여소에서 자전거를 대여 할 수 있어야 함
admin은 자전거를 추가/삭제 할 수 있어야 함
사용자가 대여/반납이 원활한 서비스여야 함
## 정책
### admin 정책
admin은 자전거를 등록/삭제 할 수 있음
admin은 실제 유자가 아니므로 자전거 대여가 안됨

### user 정책
user는 자전거 대여소에서 자전거를 대여/반납 할 수 있음
자전거 대여시 user의 1시간 동안 사용 할 수 있음
user가 자전거 대여중이면 다른 자전거를 대여 할 수 없음
user가 1시간 이상 사용시 자동으로 사용시간이 1시간 늘어남

### 대여소 정책
user가 대여소에서 자전거 대여시 보유수 -1
user가 대여소에서 자전거 반납시 보유수 +1

### bike 정책
한 자전거는 대여 중 다른 사람이 대여 할 수 없음

### 사용한 기술
 * Backend
    - java
    - servlet & jsp
    - sql & plsql
    - jdbc
    - kakaoAPI (map)
    
 * DB
    - oracleDB
    
 * Frontend
    - html
    - css
    - javascript
 
 * template
    - bootstrap

 * pattern
    - mvc
    - command
    - DAO
    - DTO
