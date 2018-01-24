package me.ernestzamelczyk.espeotask.ui.main.apps

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_showcase_app.view.*
import me.ernestzamelczyk.espeotask.R
import me.ernestzamelczyk.espeotask.data.model.ShowCaseApp

class AppsListAdapter(private val apps: List<ShowCaseApp>): RecyclerView.Adapter<AppsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_showcase_app, parent, false))
    }

    override fun getItemCount(): Int = apps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apps[position])
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.appName
        private val description: TextView = itemView.description
        private val image: ImageView = itemView.appImage
        private val button: ImageButton = itemView.playButton

        fun bind(app: ShowCaseApp) {
            name.text = app.name
            description.text = app.description
            Picasso.with(itemView.context).load(app.image).into(image)
            button.setOnClickListener(View.OnClickListener {
                val uriUrl = Uri.parse(app.playStoreUrl)
                val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                itemView.context.startActivity(launchBrowser)
            })
        }
    }
}