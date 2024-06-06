package com.example.views_example.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.views_example.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SampleBottomSheetDailogFrag : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_sample_bottom_sheet_dailog, container, false)


        return v
    }


}