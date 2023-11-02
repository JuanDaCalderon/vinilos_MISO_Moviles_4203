package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.vinilos.broker.VolleyBroker
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import java.util.Arrays


class MainActivity : AppCompatActivity() {

    lateinit var viewPagerFragmentAdapter: VPAdapter
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2

    lateinit var volleyBroker: VolleyBroker
    lateinit var recyclerView: RecyclerView
    lateinit var adapter : AdaptadorAlbum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabMenu)
        viewPagerFragmentAdapter = VPAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = viewPagerFragmentAdapter

        /*
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        */

        volleyBroker = VolleyBroker(this.applicationContext)

        recyclerView = findViewById(R.id.recyclerViewAlbum)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        volleyBroker.instance.add(VolleyBroker.getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = ArrayList<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val listTracks = Array(item.getJSONArray("tracks").length()) {
                        item.getJSONArray("tracks").getString(it)
                    }

                    val listPerformers = Array(item.getJSONArray("performers").length()) {
                        item.getJSONArray("performers").getString(it)
                    }

                    val listComments = Array(item.getJSONArray("comments").length()) {
                        item.getJSONArray("comments").getString(it)
                    }

                    list.add(
                        Album(
                            item.getInt("id"),
                            item.getString("name"),
                            item.getString("cover"),
                            item.getString("releaseDate"),
                            item.getString("description"),
                            item.getString("genre"),
                            item.getString("recordLabel"),
                            listTracks,
                            listPerformers,
                            listComments,
                        )
                    )
                }
                adapter = AdaptadorAlbum(list,this@MainActivity)
                recyclerView.adapter = adapter
            },
            {
                Log.d("TAG", it.toString())
            }
        ))
    }

}