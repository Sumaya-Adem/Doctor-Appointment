package com.example.doctorappointment.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorappointment.Adapter.DoctorsAdapter
import com.example.doctorappointment.Fragment.ProfileFragment
import com.example.doctorappointment.R
import com.example.doctorappointment.ViewModel.MainViewModel
import com.example.doctorappointment.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        InitNearByDoctor()

//        binding.bottomBar.setOnItemSelectedListener { item ->
//            when (item) {
//                R.id.profile -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, ProfileFragment())
//                        .commit()
//                    true
//                }
//                else -> false
//            }
//        }
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)  // Ensures replacement, NOT stacking
            .commit()
    }

    private fun InitNearByDoctor() {
        binding.apply {
            progreeBar.visibility = View.VISIBLE
            viewModel.loadDoctors().observe(this@MainActivity, Observer {
//                Log.d("LiveDataCheck", "Doctors in the list: ${recycleView.size}")
            recycleView.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                recycleView.adapter = DoctorsAdapter(it)
                progreeBar.visibility = View.GONE
            })
        }
    }

}


