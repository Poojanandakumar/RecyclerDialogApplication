package com.codelab.recyclerdialogapplication

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.codelab.recyclerdialogapplication.YesOrNo.yesorno

internal class MoviesAdapter(private var moviesList: ArrayList<MovieModel>,
                             private var support: FragmentManager) :
        RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val genre: TextView = view.findViewById(R.id.genre)
        val popupMenu: PopupMenu = PopupMenu(view.context, view)

        init {
            popupMenu.menu.add(Menu.NONE, 0, 0, "copy")
            popupMenu.menu.add(Menu.NONE, 1, 1, "Delete")
            popupMenu.menu.add(Menu.NONE, 2, 2, "Rename")
            (view as CardView).setOnLongClickListener {
                popupMenu.show()
                true
            }
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    1 -> {
                        val position = this.adapterPosition

                        val dialog = YesOrNo(object : yesorno {
                            override fun yes() {
                                moviesList.removeAt(position)
                                notifyItemRemoved(position)
                            }

                            override fun no() {
                                //
                            }

                        })
                        dialog.show(support, "unique")
                    }
                }

                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.title.text = movie.getTitle()
        holder.genre.text = movie.getGenre()
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}
