package com.valmirb.addressbook.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valmirb.addressbook.R
import com.valmirb.addressbook.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(val context: Context) : RecyclerView.Adapter<ContactAdapter.ContactHolder>(){

    val contactList : List<Contact> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false)
        return ContactHolder(itemView)
    }

    override fun getItemCount(): Int {
       return contactList.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = contactList[position]
        ContactHolder(holder.itemView).bind(contact)
    }


    inner class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(contact: Contact){
            itemView.contactName.text = contact.name
        }
    }

}