package com.example.messaging_ui

import Adapter
import Message
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val messages: MutableList<Message> = mutableListOf()
    var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = "test_id_1"

        if (adapter == null) {
            adapter = Adapter(userId!!, messages)
        }

        message_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false )
        message_recycler_view.adapter = adapter


        send_message_button.setOnClickListener {
            val message = text_message_input.text.toString()

            if (message.isEmpty()) {
                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            /**(val fromId: String,
            val toId: String,
            val senderName: String,
            val receiverName:String,
            val message: String,
            val productId:String,
            val time:String,
            val fromDeviceId:String?)*/

            messages.add(Message(   userId
                                 , "receiver_id"
                                 , "sender_name"
                                 , "receiver_name"
                                 ,  message
                                 , "product_id"
                                 , "'2012-11-04T14:51:06.157Z'"
                                 , "from_device_daf87as9f4"))


            text_message_input.text.clear()

            adapter!!.notifyDataSetChanged()
            message_recycler_view.scrollToPosition(messages.count() - 1)

        }
    }
    
}