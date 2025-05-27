import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(
    private val personList: List<Person>,
    private val onItemClick: (Person) -> Unit
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.personName)
        val detail: TextView = itemView.findViewById(R.id.personDetail)
        val image: ImageView = itemView.findViewById(R.id.personImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.name.text = person.name
        holder.detail.text = person.detail
        holder.image.setImageResource(person.imageResId)

        holder.itemView.setOnClickListener {
            onItemClick(person)
        }
    }

    override fun getItemCount() = personList.size
}

data class Person(
    val name: String,
    val detail: String,
    val imageResId: Int,
    val targetActivity: Class<*>
)