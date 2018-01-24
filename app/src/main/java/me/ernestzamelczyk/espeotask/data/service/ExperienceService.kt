package me.ernestzamelczyk.espeotask.data.service

import me.ernestzamelczyk.espeotask.R
import me.ernestzamelczyk.espeotask.data.model.*

class ExperienceService {

    fun getApps(): List<ShowCaseApp> {
        val list = ArrayList<ShowCaseApp>()
        list.add(ShowCaseApp(
                "La Bonne Semence",
                "La Bonne Semence was one of several apps I developed for my client as a freelance developer in 2016",
                "https://play.google.com/store/apps/details?id=me.ernestzamelczyk.labonnesemence",
                R.drawable.labonne
        ))

        list.add(ShowCaseApp(
                "Party Crowd",
                "We build Party Crowd with a team at ITGenerator. It was the first time I worked on an app with a team bigger than 3 devs.",
                "https://play.google.com/store/apps/details?id=pl.itgenerator.quovadis",
                R.drawable.party_crowd
        ))

        list.add(ShowCaseApp(
                "Hospitals",
                "Another app we build at ITGenerator. I was leading Android part of this project and learned a lot from that experience.",
        "https://play.google.com/store/apps/details?id=com.itgenerator.hospitals",
                R.drawable.hospitals
        ))

        return list
    }

    fun getExperiences(): List<Experience> {
        val list = ArrayList<Experience>()
        list.add(Experience(
                "Abi Health Group",
                "10.2017",
                "01.2018",
                "With Abi I worked in a span of 3 months on a project written in pure Javascript running on a Serverless instance in AWS. It was a gateway responsible for passing messages between multiple different messaging applications.",
                R.drawable.abi_logo))

        list.add(Experience(
                "Alliance Technology Polska",
                "09.2016",
                "09.2017",
                "In ATP I worked with a team of 10 developers on many projects where I was responsible for design and development of applications written in Java for Android or sometimes in Angular for Ionic.",
                R.drawable.atp_logo))

        list.add(Experience(
                "ITGenerator",
                "07.2015",
                "01.2016",
                "With ITGenerator I worked on development of native Android applications.",
                R.drawable.itgenerator_logo))

        list.add(Experience(
                "MoodUp Labs",
                "03.2015",
                "05.2015",
                "I did my first Android development with MoodUp where I had an internship for 2 months. I learned a lot with that team and it helped me gain initial boost to the world of making apps.",
                R.drawable.moodup_logo
        ))

        return list
    }

    fun getSkills(): List<Skill> {
        val list = ArrayList<Skill>()
        list.add(Skill("Java", 8))
        list.add(Skill("Kotlin", 7))
        list.add(Skill("Android", 8))
        list.add(Skill("REST", 9))
        list.add(Skill("SQLite", 6))
        list.add(Skill("Javascript", 5))
        list.add(Skill("Git", 7))
        return list
    }

    fun getLanguages(): List<Language> {
        val list = ArrayList<Language>()
        list.add(Language("English", "Advanced"))
        list.add(Language("Polish", "Native"))
        list.add(Language("Spanish", "Beginner"))
        return list
    }

    fun getEducation(): List<Education> {
        val list = ArrayList<Education>()
        list.add(Education("Poznan University of Technology", "Engineer", "Computer Science", "10.2014", "incomplete", R.drawable.put_logo))
        list.add(Education("Highschool of Oskar Kolberg in Koscian", "", "", "2011", "2014"))
        return list
    }

    fun getAllEducationItems(): List<AbstractEducationItem> {
        val list = ArrayList<AbstractEducationItem>()
        list.add(EducationCategoryTitle("Education"))
        list.addAll(getEducation())
        list.add(EducationCategoryTitle("Skills"))
        list.addAll(getSkills())
        list.add(EducationCategoryTitle("Languages"))
        list.addAll(getLanguages())
        return list
    }

}