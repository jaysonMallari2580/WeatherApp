package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import com.example.weatherapp.databinding.MainFragmentBinding

class MainFragment: Fragment() {

    lateinit var binding:MainFragmentBinding


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.mainInfo.observe(viewLifecycleOwner, Observer {
            binding.tempTextview.text = it.toString()

        })

        viewModel.cityNameInfo.observe(viewLifecycleOwner, Observer {
            binding.loactionTextview.text = it.toString()
        })

        viewModel.weatherHint.observe(viewLifecycleOwner, Observer {
            binding.weatherTextview.text = it.toString()
        })

        viewModel.icon.observe(viewLifecycleOwner, Observer {
            var iconCode = it.toString()
            var iconUrl = "https://openweathermap.org/img/w/$iconCode.png";
            Glide.with(requireView())
                .load(iconUrl)
                .into(binding.weatherIcon)
        })

        binding.searchIcon.setOnClickListener(View.OnClickListener {

            var cityName = binding.searchEdittext.editText
            binding.test.text = cityName?.text
            viewModel.passMeTheCityName(cityName?.text.toString())
        })
    }
}