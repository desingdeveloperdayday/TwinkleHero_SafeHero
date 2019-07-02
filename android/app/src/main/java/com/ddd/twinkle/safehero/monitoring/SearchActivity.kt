package com.ddd.twinkle.safehero.monitoring

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import kotlinx.android.synthetic.main.app_bar.*


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.options_menu)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true

        /*mSearch = menu.findItem(R.id.search)
        //menu Item Click
        mSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                txtstatus.setText("현재 상태 : 확장됨")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                txtstatus.setText("현재 상태 :축소 됨")
                return true
            }
        })

        //menuItem을 이용해서 SearchView 변수 생성
        val sv = mSearch.actionView as SearchView
        //확인버튼 활성화
        sv.setSubmitButtonEnabled(true)

        //SearchView의 검색 이벤트
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //검색 버튼 눌렸을 경우
            override fun onQueryTextSubmit(query: String?): Boolean {
                txtresult.text = query + "를 검색합니다."
                return true
            }

            //텍스트가 바뀔때마다 호출
            override fun onQueryTextChange(newText: String?): Boolean {
                txtsearch.text = "검색식 : $newText"
                return true
            }
        })*/
    }



   /* // 검색 확장,축소를 버튼으로 생성
    fun mOnClick(v: View) {
        when (v.id) {
            R.id.btnexpand  -> mSearch.expandActionView()
            R.id.btncollapse -> mSearch.collapseActionView()
        }
    }*/

}

