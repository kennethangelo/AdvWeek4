package id.ac.ubaya.informatika.advweek4.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    //To fix mismatch name with the JSON field name
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val dob:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String?
)
