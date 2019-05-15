package com.smu.a05_15

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        funGit()
    }

    fun funGit(){
        //retrofit 변수선언
        val retrofit = Retrofit.Builder()
            //받은 응답을 옵저버블 형태로 변환해준다.
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()) //Gson 바꿔준다.
                //서버에서 온 json 형식의 data를 파싱해서 받아옴
            .baseUrl("https://api.github.com/")
            .build()


        val api = retrofit.create(TestInterface::class.java)
        api.getGithub()
            //subscribeOn : 옵저버블의 스케쥴러를 바꾸는 것.
            //스케쥴러는 RxJAVA 코드를 어느 쓰레드에서 실행해야 할 지 지정해줌.
            //subscribeOn과 observeOn함수를 지정해주면?-->데이터 흐름이 발생하는 스레드와 io (출력) 쓰레드를 분리할 수 있다.
            .subscribeOn(Schedulers.io())   //사용자가 보는 것에서 입출력을 진행.
            //이후 실행할 코드를 mainThread에서 실행할 수 있다.
            .observeOn(AndroidSchedulers.mainThread())  //실행할 스레드를 main스레드로 선택해준 것임. 명령같은 것임.
            .subscribe({ result ->              //올바른 실행시에는!
                tvEmail.text = "이메일 부분 ${result.emails_url}"
                tvEmotion.text = "emotion ${result.emojis_url}"
                tvEvent.text = result.events_url
            }, {error ->            //에러났을 때!
                    error.printStackTrace()
            },{
                //작업이 정상적으로 처리되지 않았을 때
                Log.e("에러났다..","complete")

            })
    }
}
