package com.bignerdranch.android.cinemaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class NavigationHomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_navigation_home, container, false)

        val mainImageView = view.findViewById<ImageView>(R.id.mainImageView)
        mainImageView.setOnClickListener {
            Toast.makeText(requireContext(), "Captain Marvel", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
