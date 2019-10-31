package com.valmirb.addressbook.ui.addressbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.valmirb.addressbook.R
import com.valmirb.addressbook.ui.adapter.ContactAdapter
import kotlinx.android.synthetic.main.address_book_fragment.*
import kotlinx.android.synthetic.main.empty_view.*

class AddressBookFragment : Fragment() {

    companion object {
        fun newInstance() = AddressBookFragment()
    }

    private lateinit var viewModel: AddressBookViewModel
    lateinit var adapter: ContactAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyView.visibility = View.VISIBLE
        setupRecyclerView()
    }


    fun setupRecyclerView() {
        val recyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ContactAdapter(context!!)
        recyclerView.adapter = adapter
    }


}
