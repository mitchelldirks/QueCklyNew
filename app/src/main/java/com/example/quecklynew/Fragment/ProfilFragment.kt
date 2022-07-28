package com.example.quecklynew.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quecklynew.CreateEvent
import com.example.quecklynew.R


class ProfilFragment : Fragment() {
    private lateinit var btnCreateEv: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCreateEv = view.findViewById(R.id.btnCreateEvent)
        btnCreateEv.setOnClickListener {
            val intent = Intent(activity, CreateEvent::class.java)
            activity?.startActivity(intent)
        }

    }


}