package com.valmirb.addressbook.ui.addcontact

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.valmirb.addressbook.R

class AddContactFragment : Fragment() {

    companion object {
        fun newInstance() = AddContactFragment()
    }

    private lateinit var viewModel: AddContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_contact_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddContactViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
