package edu.kit.pse.a1sicht.ui.student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.kit.pse.a1sicht.database.LocalDatabase
import edu.kit.pse.a1sicht.database.entities.Review
import edu.kit.pse.a1sicht.database.entities.Student
import edu.kit.pse.a1sicht.networking.RetrofitClient
import edu.kit.pse.a1sicht.networking.ReviewService
import edu.kit.pse.a1sicht.networking.StudentService
import edu.kit.pse.a1sicht.repository.ReviewRepository
import edu.kit.pse.a1sicht.repository.StudentRepository
import edu.kit.pse.a1sicht.repository.TimeslotRepository

/**
 * The View Model responsible for the information that is being
 * displayed on the home screen for a student
 *
 * @author Hristo Klechorov
 */
class HomeStudentViewModel(application: Application) : AndroidViewModel(application) {

    private var studentRepository: StudentRepository = StudentRepository(
        LocalDatabase.getInstance(application),
        RetrofitClient.getInstance()!!.create(StudentService::class.java)
    )

    private var timeslotRepository: TimeslotRepository = TimeslotRepository(
        LocalDatabase.getInstance(application),
        RetrofitClient.getInstance()!!.create(ReviewService::class.java),
        RetrofitClient.getInstance()!!.create(StudentService::class.java)
    )

    private var reviewRepository: ReviewRepository = ReviewRepository(
        LocalDatabase.getInstance(application),
        RetrofitClient.getInstance()!!.create(ReviewService::class.java),
        RetrofitClient.getInstance()!!.create(StudentService::class.java)
    )

    private var _student: LiveData<Student> = studentRepository.getStudent()
    private var _studentReviews: MutableLiveData<List<Review>> = MutableLiveData()

    /**
     * Gets the current student that uses the app     *
     * @return The student that uses the app
     */
    fun getStudent(): LiveData<Student>{
        _student = studentRepository.getStudent()
        return _student
    }

    /**
     * Signs out a student from the given review     *
     * @param review The review, from which the student signs out
     */
    fun signOut(review: Review) {
        val user = studentRepository.getStudent()
        timeslotRepository.getForStudentInReview(user.value!!.id!!, review.id!!)

    }

    /**
     * Gets the reviews for a given student
     *
     * @param studentId The id of the student whose reviews are being fetched
     * @return The reviews of the student
     */
    fun getStudentReviews(studentId: Int): LiveData<List<Review>> {
        _studentReviews.value = reviewRepository.getReviewsForStudent(studentId)
        return _studentReviews
    }
}