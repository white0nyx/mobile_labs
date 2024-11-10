package com.example.dstu_4_2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dstu_4_2.R
import com.example.dstu_4_2.models.Participant

class ParticipantAdapter(private var participants: MutableList<Participant>) :
    RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.participant_item, parent, false)
        return ParticipantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = participants[position]
        holder.nameTextView.text = participant.name
        holder.ageTextView.text = participant.age.toString()
        holder.sportTextView.text = participant.sport
    }

    override fun getItemCount(): Int = participants.size

    fun updateData(newParticipants: List<Participant>) {
        participants.clear()
        participants.addAll(newParticipants)
        notifyDataSetChanged()
    }

    inner class ParticipantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val ageTextView: TextView = itemView.findViewById(R.id.ageTextView)
        val sportTextView: TextView = itemView.findViewById(R.id.sportTextView)
    }
}