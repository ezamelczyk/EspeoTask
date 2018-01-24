package me.ernestzamelczyk.espeotask.ui.main.experience

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_experience.view.*
import me.ernestzamelczyk.espeotask.R
import me.ernestzamelczyk.espeotask.data.model.Experience

class ExperienceListAdapter(private val experiences: List<Experience>): RecyclerView.Adapter<ExperienceListAdapter.ExperienceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        return ExperienceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_experience, parent, false))
    }

    override fun getItemCount(): Int = experiences.size

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        holder.bind(experiences[position])
    }


    class ExperienceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val companyName: TextView = itemView.companyName
        private val logo: ImageView = itemView.logo
        private val startDate: TextView = itemView.startDate
        private val endDate: TextView = itemView.endDate
        private val description: TextView = itemView.description

        fun bind(experience: Experience) {
            companyName.text = experience.companyName
            startDate.text = experience.startDate
            endDate.text = experience.endDate
            description.text = experience.description
            Picasso.with(itemView.context).load(experience.logo).into(logo)
        }
    }
}