package me.ernestzamelczyk.espeotask.ui.main.education

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_education.view.*
import kotlinx.android.synthetic.main.item_language.view.*
import kotlinx.android.synthetic.main.item_skill.view.*
import me.ernestzamelczyk.espeotask.R
import me.ernestzamelczyk.espeotask.data.model.*
import me.ernestzamelczyk.espeotask.ui.utils.SkillLevelView

class EducationListAdapter(private val list: List<AbstractEducationItem>): RecyclerView.Adapter<EducationListAdapter.AbstractViewHolder<AbstractEducationItem>>() {

    companion object {
        private const val EDUCATION_ITEM_TYPE = 1
        private const val SKILL_ITEM_TYPE = 2
        private const val LANGUAGE_ITEM_TYPE = 3
        private const val TITLE_ITEM_TYPE = 4
    }

    /**
     * this is terrible but as of right now I don't know how to do generic casting properly in kotlin
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationListAdapter.AbstractViewHolder<AbstractEducationItem> {
        return when(viewType) {
            EDUCATION_ITEM_TYPE -> EducationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_education, parent, false)) as AbstractViewHolder<AbstractEducationItem>
            SKILL_ITEM_TYPE -> SkillViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)) as AbstractViewHolder<AbstractEducationItem>
            LANGUAGE_ITEM_TYPE -> LanguageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_language, parent, false)) as AbstractViewHolder<AbstractEducationItem>
            TITLE_ITEM_TYPE -> TitleViewHolder(TextView(parent.context)) as AbstractViewHolder<AbstractEducationItem>
            else -> throw RuntimeException("Unknown viewType $viewType")
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AbstractViewHolder<AbstractEducationItem>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return when(item){
            is Education -> EDUCATION_ITEM_TYPE
            is Skill -> SKILL_ITEM_TYPE
            is Language -> LANGUAGE_ITEM_TYPE
            is EducationCategoryTitle -> TITLE_ITEM_TYPE
            else -> 0
        }
    }

    class EducationViewHolder(itemView: View): EducationListAdapter.AbstractViewHolder<Education>(itemView) {
        private val schoolName: TextView = itemView.schoolName
        private val schoolLogo: ImageView = itemView.schoolLogo
        private val degree: TextView = itemView.degree
        private val field: TextView = itemView.field
        private val startDate: TextView = itemView.startDate
        private val endDate: TextView = itemView.endDate

        override fun bind(item: Education) {
            schoolName.text = item.schoolName
            if(!item.degree.isEmpty()) {
                degree.visibility = View.VISIBLE
                degree.text = item.degree
            } else {
                degree.visibility = View.GONE
            }
            if(!item.field.isEmpty()) {
                field.visibility = View.VISIBLE
                field.text = item.field
            } else {
                field.visibility = View.GONE
            }
            startDate.text = item.startDate
            endDate.text = item.endDate
            if(item.logo != null) {
                Picasso.with(itemView.context).load(item.logo).into(schoolLogo)
            }
        }


    }

    class SkillViewHolder(itemView: View): EducationListAdapter.AbstractViewHolder<Skill>(itemView) {

        private val skillName: TextView = itemView.skillName
        private val skillLevel: SkillLevelView = itemView.skillLevel

        override fun bind(item: Skill) {
            skillName.text = item.name
            skillLevel.currentLevel = item.level
        }

    }

    class LanguageViewHolder(itemView: View): EducationListAdapter.AbstractViewHolder<Language>(itemView) {

        private val languageName: TextView = itemView.languageName
        private val languageLevel: TextView = itemView.languageLevel

        override fun bind(item: Language) {
            languageName.text = item.name
            languageLevel.text = item.level
        }

    }

    class TitleViewHolder(itemView: View): EducationListAdapter.AbstractViewHolder<EducationCategoryTitle>(itemView) {

        override fun bind(item: EducationCategoryTitle) {
            if(itemView is TextView) {
                itemView.text = item.title
            }
        }

    }

    abstract class AbstractViewHolder<in T: AbstractEducationItem>(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }


}