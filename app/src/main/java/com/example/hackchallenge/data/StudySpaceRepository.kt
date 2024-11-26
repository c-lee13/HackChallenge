package com.example.hackchallenge.data

import javax.inject.Singleton


@Singleton
class StudySpaceRepository {
    suspend fun getStudySpace(): StudySpace {
        return StudySpace("RPCC", "Moderate", null, null) //change later
    }
}