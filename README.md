# bluemarble-project
## a. 프로젝트 소개

블루마블은 국내 최초의 보드게임으로, 이탈리아의 한 지방에서 부르아 에테니스와 사라센 이크리마블이라는 농부가 [주사위](https://terms.naver.com/entry.nhn?docId=1164688&ref=y) 모양의 짚단을 갖고 [땅빼앗기](https://terms.naver.com/entry.nhn?docId=1086387&ref=y) 놀이를 하던 데에서 유래했다고 한다.

주사위를 던져 나온 숫자의 합만큼 게임판 위의 말을 움직이고, 그 칸에 해당하는 행동을 취하는 형태로 게임이 진행된다. 게임의 목표는 자신의 타일에서 걷히는 임대료 수입을 통해 [수익](https://terms.naver.com/entry.nhn?docId=1115709&ref=y)을 얻어 파산하지 않고 버티는 것으로,  끝까지 버틴 최후의 1명이 승자가 된다.



이번 프로젝트는 구현의 루즈함을 빼기 위해 최초 파산자가 나오면 그 때 보유금액이 가장 많은 사람이 승리를 취하게되고, 게임이 종료되는것으로 리메이크 했다. 

게임은 턴을 휴식하는 무인도, 돈을 지불해야하는 병원, 당첨금을 얻을 수 있는 복권명당과 같은 비주권지역과 황금열쇠라는 액션 카드가 존재하여 다양한 전략과 룰이 활용될 수 있는 형태를 취하고 있다. 





## b. 전체적인 게임 설계

![부루마블 설계](C:\Users\shien\Desktop\부루마블 설계.jpg)



### <게임 시작 >

1. 순서가 되면 주사위를 굴려 나온 수 만큼 앞으로 움직인다.

2. 도착한 곳의 타일의 주인이 없으면 타일을 구매할지 선택한다.

3. 도착한 곳의 타일의 주인이 있으면 타일 주인에게 통행료를 지불한다.

4. 게임판 한바퀴를 돌아 출발지를 지날 때는 은행에서 월급 20만원을 받는다.

   

### < 타일을 밟았을때 >

1. 타일의 주인이 없는 경우

   -> 타일을 구매하고 소유할 수  있다.

2. 타일의 주인이 있는 경우

   -> 타일의 주인에게 통행료를 지불한다.

   

### < 통행료 >

1. 도착한 타일에 주인이 있는 경우 통행료를 지불한다.
2. 통행료는 타일값  이다.



### < 특수 지역 >

1. 시작점

   - 게임의 시작점으로 모든 플레이어는 이 지점에서 게임을 시작한다.

   - 시작점을 지날 때마다 월급 20만원을 받는다.

2. 무인도
   - 플레이어가 무인도에 도착하면 다음턴에는 이동할 수 없다.

3. 복권 명당
   - 복권 당첨금 10만원을 받는다.

4. 병원

   - 건강검진 비용 20만원을 지불한다.

     

### < 황금 열쇠 미션 >

1. 용돈 20만원을 받습니다.

2. 과속운전 벌금

   - 과속운전을 하였습니다. 

     (범칙금 10만원을 은행에 납부합니다.)

3. 출발지로 이동합니다.

4. 무인도

   -  무인도로 이동하세요

     (시작점을 지나도 월급을 받을 수 없습니다.)

5. 복권 명당으로 가세요

   - 복권 명당으로 이동후 복권 당첨금을 받는다. 

6. 병원으로 이동합니다.

   - 병원에서 건강검진을 받은 후 비용을 지불합니다.

7.  신촌으로 이동합니다!

8. 이사 : 

   - 뒤로 두칸 이동해주세요

9. 생일 축하

   - 모두에게 생일 축하를 받으세요

     (다른 플레이어들에게 5만원씩 받습니다.)

   



### < 게임 종료 >

1. 한 플레이어가 파산하면 게임은 끝납니다
2. 파산하지 않은 플레이어이 소유금을 계산하여 순위를 매깁니다.
3. 남아있는 금액이 제일 많은 플레이어가 승자 입니다.







![처음화면](C:\Users\shien\Desktop\새 폴더\사진\초기화면.PNG)

초기 화면에서 함께 게임할 인원을 선택할 수 있다.





![게임화면](C:\Users\shien\Desktop\새 폴더\사진\게임화면.PNG)

초기 화면에서 인원을 선택했으면, 그 인원수를 받아 플레이어 객체와 상태창을 생성하여 나타낸다.









![게임화면](C:\Users\shien\Desktop\새 폴더\사진\게임화면.PNG)


자신의 턴에서 "던지기" 버튼을 누르면 주사위가 굴러가고, 그 주사위 값 만큼 이동하게 된다.



![타일 구매 여부](C:\Users\shien\Desktop\새 폴더\사진\타일 구매 여부.png)


주인이 없는 타일에 도착했을때, 타일을 구매할 수 있다.





![돈업승ㅁ](C:\Users\shien\Desktop\새 폴더\사진\돈업승ㅁ.PNG)

주인이 없는 타일에 도착했을때 보유현금이 없으면 구매할 수 없다.





![통행료2배](C:\Users\shien\Desktop\새 폴더\사진\통행료2배.PNG)

자신의 땅을 밟으면 통행료가 2배가 된다.





밟은 타일의 주인이 다른사람이라면  주인에게 통행료를 지불해야한다.







![무인도에 갇힘](C:\Users\shien\Desktop\새 폴더\사진\무인도에 갇힘.PNG)

![이번판 쉼](C:\Users\shien\Desktop\새 폴더\사진\이번판 쉼.PNG)



무인도에 도착하면 다음턴에는 주사위를 굴릴 수 없다.





![12번째](C:\Users\shien\Desktop\새 폴더\사진\12번째.PNG)

복권명당에 도착하면 당첨금을 받을 수 있다.





![18번째칸](C:\Users\shien\Desktop\새 폴더\사진\18번째칸.PNG)

병원에 도착하면 건강검진 비용 20만원을 지불해야한다.





![월급](C:\Users\shien\Desktop\새 폴더\사진\월급.PNG)

출발지에 도착하면 월급을 받는다.





![파삱](C:\Users\shien\Desktop\새 폴더\사진\파삱.PNG)



![순위](C:\Users\shien\Desktop\새 폴더\사진\순위.PNG)

한 플레이어가 파산하면, 파산하지 않은 플레이어이 소유금을 계산하여 순위를 매기고, 남아있는 금액이 제일 많은 플레이어가 승리를 취한다.

## c. 사용한 api, ide 버전 

​			JDK 1.8
​			IDE : 이클립스



## d. 잘한 점 

 - 첫 화면에서 플레이어 인원을 받아서 게임창으로 넘어올때 선택한 플레이어 인원 만큼만의 객체와 말, 상태창 레이아웃 생성을 바꾸는게 가능하다.
   - 처음에는 switch-case 문을 사용하여 받은 인원만큼만의 객체와 레이아웃을 하나씩 다 썼는데,  배열로 묶고, for문을 사용하니 코드가 간결해졌다.
- 처음엔 게임판 이미지 위에 라벨을 붙여서 라벨별로 말이 이동하는것으로 하려했는데 플레이어가 움직일때, 끊기게 움직이는것이 아닌 자연스럽게 움직이는 애니메이션을 사용하고싶었다.
  - 라벨을 올리는것이 아닌 창의 좌표값을 얻어서 플레이어의 말을 올렸다.
  - Timer Thread를 이용하여 플레이어가 위치한 좌표값과 이동해야할 좌표값을 비교하여 이미지의 위치를 바꿔주니 앞으로 이동하였다.
- 주사위를 던졌을때, 스탑을 누르기 전까지 이미지를 Timer Thread를 이용하여 빠르게 바꾸니 주사위가 굴러가는듯한 느낌을 받을 수 있다.
- 사운드를 추가하니 제법 게임의 느낌이 난다.



## e. 힘들었던 점 

- 특정 타일을 밟았을때 이벤트를 발생시키고 싶었는데,  처음엔 좌표값을 설정하지 않고 라벨로 하려니 이벤트발생이 잘 되지 않았다. 이동하는것을 라벨이 아닌 좌표로 바꾸고 플레이어가 있는 타일의 번호를  받는 변수를 생성하여 플레이어가 있는 타일의 번호와 특수 타일을 비교하여 판별하니 이벤트를 발생시킬 수 있었다.
- 처음에 플레이어의 말이 움직일 때 부드럽게 움직일 수 있게 애니메이션을 사용하고 싶었는데, Thread 사용이 미숙하여 다시 공부하고 시도해보니 가능했다.
  - Timer Thread를 이용하여 플레이어가 이동해야할 좌표값과 플레이어가 현재 위치해있는 좌표를 비교하여 x,y 좌표값을 +- 하면서 이동시켰다.
- 플레이어가 입출금을 할때 마다 플레이어 상태창에 보유현금이 업데이트 되었으면 좋겠는데, 보유현금이 떠있는 라벨은 플레이어 이름 라벨과 같이 묶여 있고, 또 플레이어 말 이미지와 묶여있고, 그 상태창이 배열안에 담겨있는 형태라서 어떻게 다가가야 할지 애먹었는데, ComponentEvent 를 사용하여 접근이 가능하게 해결했다.



- 그래서 이 부분을 ~~~~ 하여 해결했다.

- 그래서 이 부분을 ~~~~ 하여 해결했다.

- 하려고 했는데 ~~~ 한 이유로 쉽게 구현할 수 없었다.

  







## f. 부족한 점 (개선해야 할 점) 

- 다른 사람과 통신으로 함께하는 기능을 추가하려했으나 시간상의 이유로 구현하지 못했다. 

  각자 다른 플레이어가 서버와 연결을 하는거까지는 했지만, 서로의 정보를 실시간으로 업데이트 하는 부분까지는 시간상 여건이 되지 않아 어쩔수 없이 통신을 포기하고 플레이어 인원을 받아서 적용하는것으로 바꿨다. 추후에 추가하고싶다.

- 오리지널 블루마블이 가지고있는 룰을 제대로 구현해보고싶다.

- 주사위를 던지고 나온 값만큼 이동할때 시작 좌표에서 도착 좌표로 맵에 상관없이 이동하는데, 맵을 따라 이동하는 방법도 좋을 것 같다.
- GUI에 신경을 써서 더 보기 좋게 만들면 좋을 것 같다.





## g. 더 넣고 싶은 멘트가 있으면 추가

## h. 동영상 링크(유튜브 외부 링크) 및 섬네일 

<div>
	<a href="https://www.youtube.com/watch?v=Eo4YASzyXxA" target="_blank"><image src = "https://img.youtube.com/vi/Eo4YASzyXxA/mqdefault.jpg"></a>	
</div>


## i.  Javadoc

[Javado보러가기]()
