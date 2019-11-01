package com.valmirb.addressbook.ui.contactdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.valmirb.addressbook.R
import com.valmirb.addressbook.db.entity.Contact
import kotlinx.android.synthetic.main.contact_detail_fragment.*

class ContactDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ContactDetailFragment()
    }

    private lateinit var viewModel: ContactDetailViewModel
    private lateinit var contact: LiveData<Contact>
    private var contactId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let { bundle ->
            ContactDetailFragmentArgs.fromBundle(bundle).contactId.let {
                contactId = it
            }
        }
        viewModel = ViewModelProviders.of(this).get(ContactDetailViewModel::class.java)

        if (contactId != -1) {
            contact = viewModel.getContactDetail(contactId)
        }


        contact.observe(this,
            Observer<Contact> { contact ->
                contact_name_detail.text = contact?.name
                contact_lastname_detail.text = contact?.lastname
                contact_name_detail.text = contact?.name
                contact_email_detail.text = contact?.email
                contact_phonenumber_detail.text = contact?.phoneNumber.toString()
                contact_address_detail.text = contact?.address
            })
    }

}
