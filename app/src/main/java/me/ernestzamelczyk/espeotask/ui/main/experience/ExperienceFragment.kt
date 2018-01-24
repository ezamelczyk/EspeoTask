package me.ernestzamelczyk.espeotask.ui.main.experience

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import me.ernestzamelczyk.espeotask.R
import me.ernestzamelczyk.espeotask.data.service.ExperienceService
import me.ernestzamelczyk.espeotask.ui.utils.SpaceItemDecoration
import javax.inject.Inject

class ExperienceFragment: Fragment() {

    @Inject
    lateinit var experienceService: ExperienceService

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ExperienceListAdapter(experienceService.getExperiences())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(SpaceItemDecoration(context, R.dimen.listMargin))
    }

    companion object {
        fun create(): ExperienceFragment = ExperienceFragment()
    }

}