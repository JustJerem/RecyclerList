package com.jeremieguillot.recyclerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ConversationAdapterListener {

    private val adapter = ConversationAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        populateRecycler()
    }

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.adapter = adapter
    }

    private fun populateRecycler() {
        val contacts = getRandomList()
        adapter.setData(contacts)
    }

    private fun getRandomList(): ArrayList<Contact> {
        val contacts = arrayListOf<Contact>()
        val userPicture = "https://picsum.photos/200"

        for (i in 0..100) {
            val name = "${getName()} ${getName()}"
            val phone = "0${List(8) { ('0'..'9').random() }.joinToString("")}"
            contacts.add(Contact(name, phone, userPicture))
        }
        return contacts
    }

    private fun getName() = List(6) { ('a'..'z').random() }.joinToString("")

    override fun onUserClicked(contact: Contact) {
        Toast.makeText(this, "You cliked on : ${contact.name}", Toast.LENGTH_LONG).show()
    }
}