package com.valmirb.addressbook.ui.addressbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.valmirb.addressbook.R
import com.valmirb.addressbook.db.entity.Contact
import com.valmirb.addressbook.ui.ShareViewModel
import com.valmirb.addressbook.ui.adapter.ContactAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.address_book_fragment.*
import kotlinx.android.synthetic.main.empty_view.*
import java.util.concurrent.TimeUnit

class AddressBookFragment : Fragment(), ContactAdapter.Listener {

    companion object {
        fun newInstance() = AddressBookFragment()
    }

    private lateinit var viewModel: AddressBookViewModel
    private lateinit var shareViewModel: ShareViewModel
    private val disposable: CompositeDisposable = CompositeDisposable()


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
        shareViewModel = ViewModelProviders.of(activity!!).get(ShareViewModel::class.java)

        viewModel.getAllContacts().observe(this, object : Observer<List<Contact>> {
            override fun onChanged(list: List<Contact>?) {

                if (list.isNullOrEmpty()) {
                    emptyView.visibility = View.VISIBLE
                } else {
                    emptyView.visibility = View.GONE
                }

                list.let {
                    adapter.clear()
                    adapter.addContacts(it!!)
                }

            }

        })

        shareViewModel.onSearchLiveData.observe(this, Observer {


            disposable.add(viewModel.searchByContact(it)
                .subscribeOn(Schedulers.newThread())
                .debounce (300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.clear()
                    adapter.addContacts(it)
                }
            )
        })


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyView.visibility = View.VISIBLE
        setupView()
        setupRecyclerView()
    }


    fun setupView() {
    }

    fun setupRecyclerView() {
        val recyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ContactAdapter(context!!, this)
        recyclerView.adapter = adapter

    }


    override fun onClickItem(model: Contact) {
        val action =
            AddressBookFragmentDirections.actionAddressBookFragmentToContactDetailFragment()
        action.contactId = model.id
        action.contactName = model.name
        findNavController().navigate(action)
    }


}
