import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.messaging_ui.R
import com.example.messaging_ui.utils.Utils
import kotlinx.android.synthetic.main.incoming_message.view.*

data class Message(val fromId: String,
                   val toId: String,
                   val senderName: String,
                   val receiverName:String,
                   val message: String,
                   val productId:String,
                   val time:String,
                   val fromDeviceId:String?)

class MyViewHolder(v: View): RecyclerView.ViewHolder(v)

class Adapter (private val userId:String, private val elements: MutableList<Message>): RecyclerView.Adapter<MyViewHolder>() {

    private val viewTypeMessageSent = 1
    private val viewTypeMessageReceived = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater: LayoutInflater
        var cellForRow: View? = null

        if( viewType == viewTypeMessageSent ) {
            layoutInflater = LayoutInflater.from(parent.context)
            cellForRow =
                layoutInflater.inflate(R.layout.outgoing_message, parent, false)
        }
        else if ( viewType == viewTypeMessageReceived ){
            layoutInflater = LayoutInflater.from(parent.context)
            cellForRow =
                layoutInflater.inflate(R.layout.incoming_message, parent, false)
        }

        return MyViewHolder(cellForRow!!)

    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun getItemViewType(position: Int): Int {

        val message = elements[position]

        return if( message.fromId == userId ) viewTypeMessageSent
        else viewTypeMessageReceived

    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {

        val parsedTime = Utils().parseTimestamp(elements[pos].time)

        holder.itemView.text_message_body.text = elements[pos].message
        holder.itemView.text_message_time.text = parsedTime[1]

        if( holder.itemViewType == viewTypeMessageReceived){

            holder.itemView.text_message_name.text = elements[pos].senderName

            val imgView = holder.itemView.image_message_profile

            val mContext = holder.itemView.context

            holder.itemView.setOnClickListener {

                Toast.makeText(mContext, "To Implement", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

