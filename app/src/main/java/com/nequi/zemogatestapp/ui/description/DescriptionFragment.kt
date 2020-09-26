package com.nequi.zemogatestapp.ui.description

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nequi.zemogatestapp.R

class DescriptionFragment : Fragment() {

    companion object {
        fun newInstance() =
            DescriptionFragment()
    }

    private lateinit var viewModel: DescriptionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.description_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DescriptionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}