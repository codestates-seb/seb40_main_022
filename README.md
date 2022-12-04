# 🏋️ FIT CHALLENGE

<img src="https://user-images.githubusercontent.com/89185550/205304667-e328f320-22a9-4926-81ae-9c63e3664b98.png" width="700px"/>

### FitChallenge, 더 강력한 나를 만드는 도전. 

### 배포 링크 : [[Fit Challenge]](http://fit-challenge-22.s3-website.ap-northeast-2.amazonaws.com)


## 🏆 프로젝트 소개

#### Fit Challenge 는 피트니스 운동에 동기부여가 필요한 분들을 위한 서비스입니다.


## 📋 프로젝트 문서

### 사용자 요구사항 명세서 [[바로가기]](https://valuable-elm-71b.notion.site/8499af76cc3945f4948eede8d691878b)
### Wireframe [[바로가기]](https://valuable-elm-71b.notion.site/dc446edbfb4645448d0ad4da221026db)
### API 문서 [[바로가기]](https://valuable-elm-71b.notion.site/API-93a7f5d91f1b4770aef6717da55c1b2f)
### ERD 문서 [[바로가기]](https://valuable-elm-71b.notion.site/ERD-9248523898934c1f9db1b36c0b6df250)


## 👩‍💻 Used Stacks
<img src="https://user-images.githubusercontent.com/107971188/205494255-8aaf2ca5-6787-473e-ab7d-cbcdafed4129.png" width="60%" height="40%" alt="tech stack"></img>


### Front-End
<img src = "https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/CSS3-00A7E2?style=for-the-badge&logo=css3&logoColor=white"/> <img src = "https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src = "https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src = "https://img.shields.io/badge/styled--components-DB7093?style=for-the-badge&logo=styled-components&logoColor=white"> <img src = "https://img.shields.io/badge/Axios-181717?style=for-the-badge&logo=Axios&logoColor=white"> <img src = "https://img.shields.io/badge/Redux-764ABC?style=for-the-badge&logo=Redux&logoColor=white"> <img src="https://img.shields.io/badge/AmazonS3-339933?style=for-the-badge&logo=AmazonS3&logoColor=white">

### Back-End
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src = "https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src = "https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src = "https://img.shields.io/badge/spring data JPA-6DB33F?style=for-the-badge&logo=spring data JPA&logoColor=white"> <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens"/> <img src="https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=for-the-badge&logo=Spring-Security&logoColor=white" /> 
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">
<img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white">
<img src="https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=Elasticsearch&logoColor=white">
<img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white">
<img src="https://img.shields.io/badge/AmazonEC2-232F3E?style=for-the-badge&logo=AmazonEC2&logoColor=white">



### COMMON
<img src = "https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/discord-4d377b?style=for-the-badge&logo=discord&logoColor=white">

## 🖥 Primary Function

</br>

<details>
<summary>회원가입</summary>
<div markdown="1">


-각 요소별 유효성 검사  
![회원가입](https://user-images.githubusercontent.com/107971188/205498035-b43156b0-2be1-4dd9-92a9-562ff73f774b.gif)


-회원가입 성공  
![유효성검사없이 회원가입](https://user-images.githubusercontent.com/107971188/205498039-b5fa9d46-4ead-4b6c-88a5-66676ef43724.gif)


</div>
</details>




<details>
<summary>로그인</summary>
<div markdown="1">   

-일반 로그인  
![일반로그인 (1)](https://user-images.githubusercontent.com/107971188/205498048-756b1d25-1a1d-403e-9d16-38142c904981.gif)


-로그인시 로컬스토리지에 토큰 저장  
![로그인 후 토큰발급](https://user-images.githubusercontent.com/107971188/205498096-8e9f7061-a68d-4362-a5ed-e594dcb9e8d8.gif)


-일정시간마다 토큰 재발급  
![토큰재발급](https://user-images.githubusercontent.com/107971188/205498063-da1fd13b-b6bf-42f3-90b0-ec82e92f8e76.gif)


-새로고침시 토큰 사라지지 않게 하기  
![새로고침 토큰](https://user-images.githubusercontent.com/107971188/205498068-91eed785-4ecc-472e-9d7a-e5e5414c307d.gif)


-Oauth2.0 로그인  
![카카오 로그인](https://user-images.githubusercontent.com/107971188/205498085-bf7fdfa9-f557-4150-8993-789a86f02bcb.gif)


</div>
</details>




<details>
<summary>마이 페이지</summary>
<div markdown="1">      

-특정 유저 정보 페이지, 무한 스크롤   
![유저페이지 및 마이페이지 무한스크롤](https://user-images.githubusercontent.com/107971188/205498116-8dd8dcf1-ddf5-42a1-aecd-55acd7fbd594.gif)


-프로필 수정 기능  
![마이페이지 수정](https://user-images.githubusercontent.com/107971188/205498102-564731f5-b242-4727-ade1-cb1e98f5dd18.gif)


-회원 탈퇴 기능  
![회원탈퇴](https://user-images.githubusercontent.com/107971188/205498108-510353f2-e845-4548-b708-9bad131262f6.gif)


</div>
</details>



<details>
<summary>메인페이지</summary>
<div markdown="1">

-메인 포스트 작성 / 최근 게시글을 업로드한 유저 확인  
![메인 포스트 작성 및 최근게시물작성 유저확인](https://user-images.githubusercontent.com/107971188/205497454-26f746c4-1c23-4351-a90e-e64a8cc3d83a.gif)

-이미지 캐러셀  
![메인 캐러셀](https://user-images.githubusercontent.com/107971188/205497438-aac93ac3-20b8-4def-8d12-5dabcf2da1b3.gif)

-메인 포스트 삭제  
![메인 게시물포스트 삭제](https://user-images.githubusercontent.com/107971188/205497410-6547a1a7-6584-4747-999f-86bd2bc3d8dc.gif)

-메인 화면 무한 스크롤  
![메인 무한스크롤](https://user-images.githubusercontent.com/107971188/205497470-a66af67e-bc12-480e-b3ce-253c2e4a1969.gif)

-댓글 작성 / 댓글 더보기  
![메인 댓글 기능](https://user-images.githubusercontent.com/107971188/205497456-236c706c-1f3e-40f4-97dd-a465cb93b676.gif)

</div>
</details>




<details>
<summary>랭킹 페이지</summary>
<div markdown="1">     

-랭킹 Search  
![랭킹 검색기능](https://user-images.githubusercontent.com/107971188/205497695-a3c6aa38-66d6-45ca-a864-9d3ec24c9caa.gif)

</div>
</details>




<details>
<summary>알림 전송</summary>
<div markdown="1">

-상대방 수락 및 거절  
![알림수락](https://user-images.githubusercontent.com/107971188/205497708-cd3b0cc2-0f55-4d6c-ac9b-bf1c2cc5c45c.gif)

-상대방 중단  
![대결중단](https://user-images.githubusercontent.com/107971188/205497714-c7eaccae-9bef-4daa-83ab-b8343134b700.gif)

</div>
</details>




<details>
<summary>Q&A 게시판</summary>
<div markdown="1">

-QnA 검색기능  
![QnA 검색](https://user-images.githubusercontent.com/107971188/205497888-7f23e754-cd78-4092-abb8-5a1d38b57402.gif)

-글 작성/디테일/작성한 글 수정기능  
![QnA 작성 및 수정](https://user-images.githubusercontent.com/107971188/205497893-a1849f95-83f1-413d-92f9-6518a1a043c9.gif)

-댓글 기능  
![QnA 댓글 작성 삭제](https://user-images.githubusercontent.com/107971188/205497905-b1a40789-f5cc-48af-801c-ec737a784183.gif)

-페이지네이션  
![QnA페이지네이션](https://user-images.githubusercontent.com/107971188/205497912-65d1ea9b-d3a9-4496-8cc2-0a45fc47f214.gif)

</div>
</details>




<details>
<summary>캘린더 페이지</summary>
<div markdown="1">  

-실시간 FullCalendar업데이트  
![FullCalendar](https://user-images.githubusercontent.com/107971188/205497920-82c9df6b-d6a0-4627-abec-c28f25b7524d.gif)

-상대방 운동 등록시 화면에 나옴 + 본인도  
![캘린더 승부 페이지](https://user-images.githubusercontent.com/107971188/205497926-f25de407-ff23-49ca-a406-9b72b58f7cb2.gif)

</div>
</details>


</br>

## 💪 Team Member
|최윤우(팀장)      |한승진(FE팀장)|고정훈|고하나|
|---------------|----|----|----|
|<img src="https://user-images.githubusercontent.com/107971188/205416485-5b7f3402-cf4c-4cf5-b229-0612744524f6.png" width="150px"/>|<img src="https://user-images.githubusercontent.com/89185550/200539888-90098221-47b0-446e-a2a5-9be1d926d8ea.png" width="150px"/>|<img src="https://user-images.githubusercontent.com/107971188/205416593-c9a03f99-9e8e-4fd0-8601-fdb86048a9d6.png" width="150px"/>|<img src="https://user-images.githubusercontent.com/107971188/205416550-07670d1d-ec28-4db3-8d6e-2d53a8fa3ec5.png" width="150px" />|
|[@Helperbot5](https://github.com/Helperbot5)|[@likeprograming1](https://github.com/likeprograming1)|[@e5lina](https://github.com/e5lina)|[@ko9612](https://github.com/ko9612)|

|김신재(BE팀장)|방인석|조현식|
|----|----|----|
|<img src="https://user-images.githubusercontent.com/89185550/200539753-c273f7d7-43e5-4e3f-ba1f-3fa13617c616.jpeg" width="150px" />|<img src="https://user-images.githubusercontent.com/89185550/200539186-d7c54fae-514d-448f-b53d-ef1d87101c7d.png" width="150px"/>|<img src="https://user-images.githubusercontent.com/89185550/200539734-ad2ec920-aa02-4bbd-9ce6-3885661fc912.jpg" width="150px" />|
|[@shinjaekim](https://github.com/shinjaekim)|[@Inssg](https://github.com/Inssg)|[@abcodefg](https://github.com/abcodefg)|


### 👨‍👩‍👧‍👦 Team Culture

- 매일 오전 10시에 모여서 각자 진행 사항 및 이슈 논의
- 프로젝트 기간 내 오전 10 ~ 12 / 오후 2 ~ 4시까지는 항시 접속 및 응답
- PR할 경우 2명 이상의 승인이 되어야 Merge가 가능
- 작업 중 발생한 에러는, 팀원끼리 같이 해결
- 프로젝트 진행 중 배려와 존중 / 커뮤니케이션 상시 ! 


## 📚 Git
### Branch
- `main` : 서비스 운영 브랜치입니다.
- `dev` : 개발 환경 브랜치입니다. 개별적으로 작업했던 내용을 합치고 검토합니다.
- `feat/fe(or be)/...` : 프론트(백엔드) 세부 브랜치입니다.
### Commit & Pull-Request Message
|이슈|내용|
|---|---|
|🚀API|API 관련|
|🤬BugFix|버그 발견|
|🎨CSS|스타일링 관련|
|🖥Deploy|배포 관련|
|📓Docs|문서 작성 관련|
|🌟Feat|기능 개발|
|❗Issue|이슈 관련|
|❓Question|개발 질문|
|🏭Refac|코드 리팩토링|
|⚙Set|개발 환경 세팅|
|✅Test|테스트 관련|
