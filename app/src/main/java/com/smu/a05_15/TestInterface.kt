package com.smu.a05_15

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface TestInterface{

    //Observable 사용
    // 장점 : CallBack을 신경쓰지 않아도 된다. && 비즈니스로직과 프로그래밍을 분리
    //Observable사용시 코드가 짧아짐.
    @GET("/")   //여기서 /는 TestActivity에 URL들어 있음.
    fun getGithub() : Observable<Github>


    //Call 방법 사용
    //코드가 길어짐.
    @GET("/user/{users}")   //내가 입력하는 user라는 변수값
    fun loadChange(@Query("user") status:String): //위에 {}안에 여기서 입력한 user의 내용이 들어감.  //user라는 애를 보내면  user에 대한 데이터의 내용들이 돌아옴. //아까의 경우에는 user_url": "https://api.github.com/users/{SeonYoungKim98}"같은거임.
            Call<List<Github>>      //얘 때문에 callBack을 신경써야 되는 것이다.
}
