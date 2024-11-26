package com.example.hackchallenge.data

import com.example.hackchallenge.data.remote.MyApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StudySpaceRepository @Inject constructor(
    private val myApi: MyApi,
) {
    suspend fun getStudySpace(): StudySpace {
        return StudySpace("RPCC", "Moderate", null, null) //change later
    }
}