package com.demo.app.capsule.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.app.capsule.R

class EmptyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return  LayoutInflater.from(requireContext()).inflate(R.layout.empty_fragment, container, false)
    }
}