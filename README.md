# DoodleDoodle _Version.2_

> 사용자의 그림을 AI가 맞추고 분석해주는 서비스

[배포 완료 후 링크 반영 예정](http://)

<div align=center>

## 서비스 사용 예시

<img src="https://user-images.githubusercontent.com/95288297/184758633-21789c94-b510-402e-bf72-3825322e1461.gif">

## PC Version

|메인 화면|서비스 정보 화면|
|:----:|:----:|
|<img src="https://user-images.githubusercontent.com/87285536/183126857-1a6d4f4d-5eca-4642-8905-de17b916f11b.png" width="400" height="200">|<img src="https://user-images.githubusercontent.com/87285536/183129494-d02d6fb0-0ea1-42a6-a4ad-cea33bcd4f66.png" width="400" height="200">|
|그림 그리기 화면|개인용 결과 화면|
|<img src="https://user-images.githubusercontent.com/87285536/183129750-2f08b0d8-fb9a-4bc9-a902-ab1efae2b9ae.png" width="400" height="200">|<img src="https://user-images.githubusercontent.com/87285536/183129775-743a7551-e917-4f3e-a510-a666c10c7804.png" width="400" height="200">|
|다인용 결과 화면|프로젝트 정보 화면|
|<img src="https://user-images.githubusercontent.com/87285536/183130430-df1ab55d-d9e3-4ed8-a139-e3109106546c.png" width="400" height="200">|<img src="https://user-images.githubusercontent.com/87285536/183130463-c879b3d7-e7f4-475a-89f5-45ecdc3bdbc0.png" width="400" height="200">|

## Mobile Version

<img src = "https://user-images.githubusercontent.com/87285536/183131511-08ccb2c4-c37c-4096-a6fe-f2af08271e00.png" width="33%"><img src = "https://user-images.githubusercontent.com/87285536/183131545-e2eb7f06-54ae-42d5-8b59-dcf14e514687.png" width="33%"><img src = "https://user-images.githubusercontent.com/87285536/183131582-3d3c8381-1446-461d-9aef-94f8562299cf.png" width="33%">
<img src = "https://user-images.githubusercontent.com/87285536/183131685-79b8cf05-bd44-44d9-b5ae-7273dc3a718c.png" width="33%"><img src = "https://user-images.githubusercontent.com/87285536/183131838-a5a7d534-486c-4e84-87c4-4d922587e997.png" width="33%"><img src = "https://user-images.githubusercontent.com/87285536/183131866-839a8c25-5c4b-47eb-a022-c709c66f7181.png" width="33%">

## Result Sharing
<img src="https://github.com/ALLBACK-2022/.github/assets/89020004/308141aa-003c-46ab-808e-540ae5e2f34d" width="200">

</div>

## System Architecture
![image](https://github.com/ALLBACK-2022/DoodleDoodle/assets/89020004/7d6e60bf-3d43-4bd5-8db6-c0240afb4145)

### 기술적 특징
- Service, Repository를 포함한 다양한 추상화
- Fetch Join을 활용한 N+1 문제 해결
- 쿼리 성능을 위한 인덱스 사용
- Kafka를 활용한 비동기 처리로 AI 로직 흐름 분리
- Faust를 통한 멀티 프로세싱
- Jib를 통한 백엔드 서버 도커라이징
- Prometheus & Grafana를 통한 모니터링
- Github Actions를 통한 CI/CD
- Spring Rest Docs를 통한 API 문서 작성

## Docs
[API Rest Docs](https://allback-2022.github.io/API-Docs-v2/)

## 개발 멤버
|정윤호|정훈희|김승진|
|:----:|:----:|:----:|
|Backend, DevOps, Deploy|Frontend|Backend, DevOps|
|<a href="https://github.com/yunhobb">@yunhobb</a>|<a href="https://github.com/JeongHunHui">@JeongHunHui</a>|<a href="https://github.com/ohksj77">@ohksj77</a>|
