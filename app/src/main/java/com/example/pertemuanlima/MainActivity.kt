package com.example.pertemuanlima

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuanlima.model.DataItem
import com.example.pertemuanlima.model.ResponseUser
import com.example.pertemuanlima.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var rvUsers: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvUsers = findViewById(R.id.rv_users)
        adapter = UserAdapter(mutableListOf())

        rvUsers.setHasFixedSize(true)
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        getUser()
    }
    private fun getUser(){
        val client = ApiConfig.getApiService().getListUsers("1")

        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful){
                    val dataArray = response.body()?.data as List<DataItem>
                    for (data in dataArray) {
                        adapter.addUser(data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()

            }
        })
    }
}