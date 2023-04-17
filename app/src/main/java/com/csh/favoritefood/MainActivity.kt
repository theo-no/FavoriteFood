package com.csh.favoritefood

import android.content.Intent
import android.graphics.pdf.PdfDocument.Page
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.csh.favoritefood.databinding.ActivityMainBinding
import com.csh.favoritefood.fragment.FavoriteFragment
import com.csh.favoritefood.fragment.HomeFragment
import com.csh.favoritefood.fragment.SettingFragment

private const val TAG = "MainActivity 로그"
class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            viewPager.adapter = PagerAdapter(this@MainActivity)
            //스와이프로 바꿨을 때 -> 바텀에 있는 것도 갱신되어야 함
            viewPager.registerOnPageChangeCallback(PageChangeCallback())
            //바텀에 있는 걸로 선택 -> viewpage에 변경
            bottomNavigationView.setOnItemSelectedListener  {
                navigationSelected(it)
            }
            addBtn.setOnClickListener{
                val intent = Intent(this@MainActivity,EditActivity::class.java)
                startActivity(intent)
                //나중에 DB 연결할 때는 launch로 호출해서 데이터 받아와서 처리해야함
            }

            toolbar.setTitle("F.F")
            setSupportActionBar(toolbar)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        val searchItem = menu!!.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        return true
    }

    private inner class PagerAdapter(activity: MainActivity): FragmentStateAdapter(activity) {
        override fun getItemCount() = 3
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> FavoriteFragment()
                2 -> SettingFragment()
                else -> error("no such position: $position")
            }
        }
    }

    private fun navigationSelected(item: MenuItem): Boolean {
        val checked = item.setChecked(true)
        when (checked.itemId) {
            R.id.homeFragment -> {
                binding.viewPager.currentItem = 0
                binding.addBtn.visibility = View.VISIBLE
                return true
            }
            R.id.favoriteFragment -> {
                binding.viewPager.currentItem = 1
                binding.addBtn.visibility = View.VISIBLE
                return true
            }
            R.id.settingFragment -> {
                binding.viewPager.currentItem = 2
                binding.addBtn.visibility = View.GONE
                return true
            }
        }
        return false
    }

    private inner class PageChangeCallback: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bottomNavigationView.selectedItemId = when (position) {
                0 -> R.id.homeFragment
                1 -> R.id.favoriteFragment
                2 -> R.id.settingFragment
                else -> error("no such position: $position")
            }
            when(binding.bottomNavigationView.selectedItemId){
                R.id.homeFragment -> binding.addBtn.visibility = View.VISIBLE
                R.id.favoriteFragment -> binding.addBtn.visibility = View.VISIBLE
                R.id.settingFragment -> binding.addBtn.visibility = View.GONE
                else -> error("no such position: $position")
            }
        }
    }
    //뒤로가기 -> 첫 번째 화면으로 이동시킨 다음 종료
    override fun onBackPressed() {
        if(binding.viewPager.currentItem==0){
            super.onBackPressed()
        }else{
            binding.viewPager.currentItem = 0
        }
    }
}