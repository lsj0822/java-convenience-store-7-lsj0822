# java-convenience-store-precourse

## 4주차 - 편의점

* * *

##### 어떤 프로그램인가?

이 프로그램의 목적은 사용자가 편의점의 물건을 구매하는 상황을 재현한 프로그램이다. 다만 GUI 형식이 아닌 CLI 형식으로 구매하는 형식을 재현했기에 다음과 같은 제약 사항이 따른다.
- 물건을 구매하는 형식은 [물건명-개수]이고, 각 물건을 구매하는 구분자는 쉼표(,)만 가능하다.
   
- 물건을 구매할때는 정확한 이름을 입력해야하고, 목록에 없는 물건은 구매할 수 없다. 
    - 재고 : 오리온정초코파이 - 7개 보유
    - [오리온정초코파이-5] (O) 
    - [초코파이-5] (X)
    - [오리온정초코파이-3개] (X)
    - [오리온정초코파이-10] (X)
- 프로모션의 경우 1+1, 2+1와 같이 특정 물건을 특정 개수 이상 구매했을 시 1개를 무료로 증정한다.
- 프로모션으로 무료로 물건을 구매할 수 있는 경우 물건 증정을 제안한다. 단, 사용자가 거절할 수 있다.
- 단, 프로모션 재고가 프로모션 할인을 적용할만큼 충분하지 않다면 할인이 적용되지 않는다.
    - 2+1인 프로모션 재고가 5개 남았을 때, 6개를 구매시 : 할인은 1개만 적용
- 또한 남은 프로모션 재고와 구매하려는 수량이 일치하고 프로모션 할인이 동시에 적용 가능할때는 별도의 메시지를 출력하지 않는다
    - 2+1인 프로모션 재고가 5개 남았고 사용자가 5개를 구매시 프로모션 제안 메시지 및 재고 부족 메시지지를 출력하지 않는다.
    - 이는 사용자가 구매하고 싶은 수량인 5개를 온전히 구매할 수 있도록 하기 위함이다.
        - 만약 무료 재고 메시지를 띄운다면 사용자는 6개의 수량을 구매해야하고, 이러기 위해서는 재고 부족 메시지를 추가로 출력해야한다. 또한 이렇게 프로모션 제안을 했다면 이후에 구매 가능한 분량은 3개 또는 6개가 되어 사용자가 원하지 않는 물품을 추가로 구매하는 꼴이 된다.
        - 만약 재고 부족 메시지를 프로모션이 불가능한 분량인 2개만큼 띄운다면, 이는 프로모션이 가능한 상황인데 재고가 부족하여 정가로 결제를 해야할 때 띄우는 메시지의 목적에 위배된다.
- 멤버십 할인은 프로모션으로 구매한 재고를 제외하고 적용한다.
    - 예를 들어 물건을 구매했을 때 일부는 프로모션, 일부는 일반 재고로 구매했다면 멤버십 할인은 일반 재고에 한정하여 적용된다.
    - 모든 구매 물품이 멤버십 할인이 적용이 불가능한 프로모션 재고인 경우에도 멤버십 할인 적용이 가능하다.
- 구매가 끝났다면 영수증을 출력한다.
    - 영수증 출력 형식으로는 상품명, 수량, 금액 순으로 출력한다.
        - 이때 각 수량은 왼쪽 정렬로 한다.
        - 상품명은 분량상 최대 6글자만 표시하고, 뒤는 생략한다.
        - 수량은 최대 3자리까지만 표시한다.
        - 금액의 상한선은 없으며, 물품의 총 금액을 천단위를 구분하여 출력한다.
        - 글자 수와 수량의 제한은 구현 이후에 조정될 수 있다.
* * *
##### 어떻게 구현할 것인가?
- 이번의 구현은 물건의 상태가 프로모션과 일반으로 나누어져 있고, 구매한 물품의 상태도 프로모션으로 구매와 일반으로 구매로 나뉜다. 그렇기에 이것은 인터페이스와 그것을 상속받는 클래스들로 구현할 수 있다.
- 프로모션 기간동안의 프로모션 재고는 '특별한 상품'을 파는 것으로 해석한다. 예를 들어 특정 기간에만 '뉴진스 에디션 코카콜라'를 판다고 가정하자. 이 뉴진스 콜라는 2개를 사면 1개는 공짜로 증정한다. 그런데 만약 뉴진스 콜라를 2개를 구매한 사람이 1개 증정을 거부한다고해서 갑자기 뉴진스 콜라를 회수하고 일반 콜라를 2개를 쥐어줄 필요는 없는 것이다. 즉, 프로모션 재고는 우선으로 구매가 가능한 수량이다.
- products.md의 경우 프로모션 재고와 일반 재고가 연달아서 나오는 경우가 많은데, 이와 같은 경우는 클래스를 프로모션 클래스와 일반 클래스로 나누어 생성하기 때문에 반드시 위와 같은 순서를 지켜야한다. 
   
- 중복한 값이 들어온 경우 어떻게 처리할 것인가? 이를 증빙하기 위해 특정 변수가 같은지를 비교해야한다. 일반의 경우 제품의 이름과 가격이 같아야 같은 물건, 프로모션 재고는 이름과 가격, 그리고 프로모션 이름이 같아야한다. 만약 프로모션 재고에 일반 재고가 추가로 들어오는 경우는 이름과 가격만 비교하면 된다. 일반 재고가 완전히 같은 경우는 재고의 수량을 추가하고, 재고의 이름이 같으나 가격이 다르면 예외처리한다. 프로모션 재고도 마찬가지로 완전히 같은 경우는 수량 추가, 다른 경우는 예외처리이다.

- 물건의 구매는 Cart라는 모델을 사용할 예정이다. 이는 하나의 프로그램에서 생성 및 삭제가 된다. 물건을 구매할 때 카트에 있는 멤버 배열에 이를 넣는다. 추가로 증정 상품의 경우 이것 또한 별도의 재고로 관리한다. Cart의 경우 반복문을 통한 여러 번의 생성에서 생성이 될때마다 기존의 Cart 객체는 garbagecollector에 의해서 삭제가 되지만, 이것을 명시적으로 삭제하기 위해 로직의 맨 끝에 nullpointer를 할당하므로써 더 이상 해당 객체를 사용하지 않음을 알릴 예정이다.

- 다양한 이유로 프로모션 적용이 안되는 경우를 어떻게 표현할 수 있을 것인가?(멤버십에 들어갈 값을 어떻게 구분할 것인가?) 이는 카트에서 구매한 비용을 2개의 변수로 나누면 해결될 것으로 보인다. 또는 카트에 들어갈 객체가 프로모션으로 구매한 것과 그렇지 않은 것을 가지고 있다면? 3주차의 공통 피드백처럼 굳이 Cart라는 모델에 두개의 변수를 추가할 이유가 없어진다.
   
* * *

##### 어떤 기능을 만들까?
- [ ] 값을 입력받는 클래스
    - [ ] products.md, promotions.md를 읽는 기능
    - [ ] 구매할 상품과 수량을 입력받는 기능
    - [ ] 사용자에게 질문을 하는 기능
    - [ ] 각 메서드의 문구는 enum 객체로 관리
   
   
- [ ] 값을 출력하는 클래스
    - [ ] 환영 문구를 출력하는 기능
    - [ ] 질문한 내용을 출력하는 기능
    - [ ] 물품 목록을 출력하는 기능
        - [ ] 각각의 물품을 출력하는 기능
    - [ ] 영수증을 출력하는 기능
        - [ ] 계산 없이 단순 문구를 출력하는 기능
        - [ ] 구매한 상품들을 출력하는 기능
            - [ ] 개별 상품을 출력하는 기능
        - [ ] 증정 상품들을 출력하는 기능
            - [ ] 개별 증정 상품을 출력하는 기능(상품 출력으로 통일 가능성 있음)
        - [ ] 계산한 금액들을 출력하는 기능
   
   
- [ ] 값을 보관하는 클래스
    - [ ] 재고를 가지고 있는 클래스
        - [ ] 재고의 상태를 문자열로 만들어 전달하는 기능
        - [ ] 재고의 상태를 갱신하는 기능
    - [ ] 재고의 상태를 표현하는 클래스
        - [ ] 재고의 상태를 문자열로 만들어 전달하는 기능
        - [ ] 재고의 상태를 갱신하는 기능
    - [ ] 구매한 물품을 관리하는 클래스
        - [ ] 구매한 물품의 이름, 상태별 수량, 가격을 저장하는 배열
        - [ ] 총 금액 관련 정보는 저장하지 않음
    
   
- [ ] 프로그램의 흐름을 관리하는 클래스(Controller)
    - [ ] 프로그램의 주축을 담당하는 클래스 
    - [ ] 재고의 상황에 따라 다른 메세지를 뷰에 전달하는 기능
    - [ ] 갱신할 값을 각각의 객체에 알리는 기능

- [ ] 검증을 위한 클래스(Validator)
    - [ ] 문자열이 잘 나눠졌는가?
    - [ ] 입력한 상품이 있는 상품인가?
    - [ ] 입력한 상품의 재고가 충분한가?
    - [ ] 사용자가 맞게 입력하였는가?
   
   
- [ ] 편의를 제공하는 클래스(Util)
    - [ ] 문자열을 배열로 분리하는 기능
    - [ ] 프로모션 기간인지 확인하는 기능