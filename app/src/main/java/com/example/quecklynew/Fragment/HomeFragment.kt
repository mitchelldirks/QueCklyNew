package com.example.quecklynew.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quecklynew.R
import com.example.quecklynew.ScanActivity

class HomeFragment : Fragment() {
    private lateinit var btnScan: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnScan = view.findViewById(R.id.btnScan)
        btnScan.setOnClickListener {
            val intent = Intent(activity, ScanActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}