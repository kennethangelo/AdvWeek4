package id.ac.ubaya.informatika.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.navigation.Navigation
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.informatika.advweek4.model.Student
import id.ac.ubaya.informatika.advweek4.util.loadImage
import id.ac.ubaya.informatika.advweek4.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.txtName
import java.util.*

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener, ButtonCreateNotificationClickListener {
    private lateinit var viewModel: DetailViewModel
    private val studentListAdapter  = StudentListAdapter(arrayListOf())
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    //Set actions for observer about how to handle the emitted data
    fun observeViewModel() {
        //Any program under the Observer function will be executed if needed (i.e. activity state changed, configuration changed)
        //Students LiveData (observable) attached to this fragment (observer)
        //Everytime observer changes state -> observable emit the data
        //Used to "observe" Students data
        viewModel.studentLD.observe(viewLifecycleOwner) {
            dataBinding.student = it
        }
//            var student = it
//            txtId.setText(it.id)
//            txtName.setText(it.name)
//            txtBod.setText(it.dob)
//            txtPhone.setText(it.phone)
//            imageView2.loadImage(it.photoUrl, progressBarDetail)
//            btnCreateNotif.setOnClickListener {
//                io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_all_inclusive_24)
//                    }
//
//            }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentID)
            observeViewModel()
        }

        dataBinding.createNotification = this
        dataBinding.update = this
    }

    override fun onButtonUpdateClick(v: View, s: Student) {
        Toast.makeText(v.context, "Student #${s.id} has been updated.", Toast.LENGTH_SHORT).show()
    }

    override fun onButtonCreateNotificationClick(v: View, s:Student) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                MainActivity.showNotification(s.name.toString(), "Notification created", R.drawable.ic_baseline_all_inclusive_24)
            }
    }
}