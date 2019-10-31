package com.valmirb.addressbook.ui.addressbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.valmirb.addressbook.R

class AddressBookFragment : Fragment() {

    companion object {
        fun newInstance() = AddressBookFragment()
    }

    private lateinit var viewModel: AddressBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstancxeState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.address_book_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddressBookViewModel::class.java)
        // TODO: Use the ViewModel
    }


}
