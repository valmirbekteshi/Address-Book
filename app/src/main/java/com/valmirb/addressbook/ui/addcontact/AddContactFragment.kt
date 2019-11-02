package com.valmirb.addressbook.ui.addcontact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.valmirb.addressbook.R
import com.valmirb.addressbook.db.entity.Contact
import com.valmirb.addressbook.utlis.Validator
import kotlinx.android.synthetic.main.add_contact_fragment.*

class AddContactFragment : Fragment() {

    private lateinit var addContactViewModel: AddContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_contact_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addContactViewModel = ViewModelProviders.of(this).get(AddContactViewModel::class.java)

        buttonSave.setOnClickListener {
            val firsName = contact_firstname.text.toString()
            val lastName = contact_lastname.text.toString()
            val email = contact_email.text.toString()
            val phone = contact_phonenumber.text.toString()
            val address = contact_address.text.toString()


            if (Validator.isValidName(contact_firstname) && Validator.isValidLastname(
                    contact_lastname
                ) && Validator.isValidEmail(contact_email)
                && Validator.isValidPhone(contact_phonenumber) && Validator.isValidAddress(
                    contact_address
                )
            ) {
                val contact = Contact(firsName, lastName, email, Integer.valueOf(phone), address)
                addContactViewModel.insert(contact)
                findNavController().navigate(R.id.addressBookFragment)
            }


        }

    }


}
