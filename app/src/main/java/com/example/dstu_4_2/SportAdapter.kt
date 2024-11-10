package com.example.dstu_4_2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.dstu_4_2.R
import com.example.dstu_4_2.models.Sport

class SportAdapter(
    private var sports: MutableList<Sport>,
    private val onDeleteClick: (Sport) -> Unit,
    private val onEditClick: (Sport) -> Unit
) : RecyclerView.Adapter<SportAdapter.SportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sport_item, parent, false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = sports[position]
        holder.nameTextView.text = sport.name
        holder.editButton.setOnClickListener {
            onEditClick(sport)
        }
        holder.deleteButton.setOnClickListener {
            onDeleteClick(sport)
        }
    }

    override fun getItemCount(): Int = sports.size

    fun updateData(newSports: List<Sport>) {
        sports.clear()
        sports.addAll(newSports)
        notifyDataSetChanged()
    }

    inner class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val editButton: Button = itemView.findViewById(R.id.editButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}
