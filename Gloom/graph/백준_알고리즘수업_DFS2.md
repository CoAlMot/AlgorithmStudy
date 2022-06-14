# 문제 구성 📖
> 코딩테스트 사이트 : 백준  
> 난이도 : 실버2     
> 풀이 날짜 : 2022.06.14  
> 사용한 풀이 방법 : DFS

## 문제링크
https://www.acmicpc.net/problem/24480

## 풀이법

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/173478224-25359656-51b9-46a7-a26e-102a1c18ce93.png" width="50%"></p>
내림차순 순서이기에 값이 큰값부터 방문해야된다.

위와 같은 경우 순서가 1 -> 4 -> 3 -> 2 순으로 방문 하게 되고 이에 맞춰 결과 물은 
```
1
4
3
2
0 // 방문하지 않음 
```
## 알고리즘 설계
- 먼저 주어진 텍스트로 된 간선정보를 모아, 배열로 만든다. 
- DFS로 방문하여 풀면서 방문가능하면, 방문순서를 대입해주자
- 이미 방문했던 곳은 표시해주자.
- 
### 예외처리 
- 방문할 수 없는 곳은 `0`으로 처리한다.



```java
//깊이 우선탐색 구현하기, 내림차순으로 방문
// N개의 정점과 M개의 간선으로 구성된 무방향(양쪽다됨) 그래프
// 주어지는 조건
// 첫째줄
// 1= 정점의 수 (최대 10만)
// 2= 간선의 수 (최대 20만)
// 3= 시작 정점

// 2번째 줄부터 간선의 수 만큼
// 1 = 출발 정점
// 2=  도착 정점

// 방문 체크 배열
// 순서 담을 배열 

// 무방향이라 양쪽으로 추가

// 내림차순이기에, 값이 큰것부터 

// 번호부터 순환하여, 시작 정점부터 도착하게되는 순서대로 출력
// 도달할수 있는지를 파악하고, 해당 값을 대입
public static void DFS(int currentNode, ArrayList<Integer>[] nodeArray, boolean[] visited, int[] answer){
        //탈출조건 1. 더이상 갈곳이 없다.
        //       2. 이미 방문한 곳이다.

        answer[currentNode-1] = ++sequence;

        for (int nextNode : nodeArray[currentNode]){  // 애초에 저장할때, 큰 값부터 넣기
        if(visited[nextNode]){continue;} // 이미 방문한 곳이다.
        visited[nextNode] = true;
        DFS(nextNode, nodeArray,visited, answer);
        }

}
```
- 여기서 `sequence` 변수를 DFS메서드에 인자로 넣어주면, 문제를 해결하지 못한다.
- 내가 원하는 값이 있거나, 원하는 값을 재귀함수를 통해 넣어줄때는 `원시타입`은 적절하지 못하다.

## 내가 DFS를 어려워 하는 이유

### 1. 다른 메소드는 서로 영향을 끼치지않는다. 이것은 재귀도 마찬가지이다.

```java
public class test{
  public static void main(String[] args) {
  int a = 4;
  System.out.println("메소드 전용 전 변수 출력 : " + a);
  variableChange(a);
  System.out.println("메소드 전용 후 변수 출력 : " + a);
  }
  public static void variableChange(int a){
    a =3 ;
    System.out.println("메소드 안 변수 출력 : " + a);
  }
}  
```

- 결과
> 메소드 전용 전 변수 출력 : 4   
> 메소드 안 변수 출력 : 3   
> 메소드 전용 후 변수 출력 : 4

- `Main`메소드안에 있는 변수와 그것을 전달 받은 `variableChange`메소드의 변수는 각자 독립적인 변수이다.

<p align="center"><img src ="https://user-images.githubusercontent.com/104331549/173485205-cec3a76f-c6b1-4f76-baed-6aa5bf8f7d3e.png" width="50%"></p>

<br></br>

### 2. 같은 곳을 가리키는 참조타입(배열, Collection들과 같이 주소값을 가지는 타입들)
```java
public class test2{
  public static void main(String[] args) {
  int[] a = {1,2,3,4};
  System.out.println("메소드 전용 전 배열 출력 : " + Arrays.toString(a));
  variableChange(a);
  System.out.println("메소드 전용 후 배열 출력 : " + Arrays.toString(a));
  }
  public static void variableChange(int[] a){ // 여기서 참조타입인 배열 자체를 인자로 넣어줘야한다.
    a[0] = 5 ;
    System.out.println("메소드 안 배열 출력 : " + Arrays.toString(a));
  }
}  
```
- 결과
> 메소드 전용 전 배열 출력 : [1, 2, 3, 4]   
> 메소드 안 배열 출력 : [5, 2, 3, 4]   
> 메소드 전용 후 배열 출력 : [5, 2, 3, 4]

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/173486168-561c7aea-804f-48d5-989c-b4eefbbdba90.png" width="50%"></p>

<br></br>

### 3. 인자로 뭐하러 넘겨줘? 어떤 메서드 상관없이 부를 수 있는 static 클래스변수
```java
public class test3{
  static int gv= 4; // 메소드 밖의 Primitive타입 변수
  public static void main(String[] args) {
  		System.out.println("메소드 적용 전 전역 변수  : "+ga);
      variableChange(); // 인자가 필요없음
      System.out.println("메소드 적용 전 전역 변수  : "+ga);
  }
  public static void variableChange(){ 
    ga = 5 ;
    System.out.println("메소드 안 전역변수 : " + ga);
  }
}
```
- 결과
> 메소드 전용 전 전역 변수 : 4   
> 메소드 안 전역 변수 : 5   
> 메소드 전용 후 전역 변수 : 5

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/173486180-6e61899d-8193-4e77-8aa2-f07fc1e5eda6.png" width="50%"></p>


## 결론
- 경로를 파악하는 DFS를 재귀함수로 구현한다면, `return값은 void`로 순환하게하고,
    - 안에서 **깊이에 따른 원하는 값**을 빼고 싶다면 `참조객체로 빼는 것`이 가장 이상적이다.
- DFS의 재귀함수 안에서, 재귀함수의 깊이에 때라 바뀌는 값이 아니라면 `static 변수`로 처리할 수 있다.

> DFS를 재귀로 구현시, 반환값은 void가 이상적
> 깊이랑 관계있는 변수는 참조타입으로 해주며, 인자에 넣어주기
> 깊이랑 관계없는 변수는 static으로 처리


<p align="center"><img src="https://user-images.githubusercontent.com/104331549/173490328-eafbbd24-d828-4cee-b6fd-45b76ade2489.png" width="70%"></p>
