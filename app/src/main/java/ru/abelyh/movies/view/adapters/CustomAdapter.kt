package ru.abelyh.movies.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.abelyh.movies.R
import ru.abelyh.movies.data.Result

class CustomAdapter(private val mList: List<Result?>,
                    val mItemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList?.get(position)

        // sets the image to the imageview from our itemHolder class
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + itemsViewModel?.poster_path)
            .into(holder.imageView)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return if (mList.isNullOrEmpty()) 0 else mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        init {
            ItemView.setOnClickListener{
                mList?.get(position)?.id?.let { it->mItemClickListener.onItemClick(it) }
            }
        }
    }
}