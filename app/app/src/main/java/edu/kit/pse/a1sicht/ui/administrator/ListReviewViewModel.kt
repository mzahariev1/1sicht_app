package edu.kit.pse.a1sicht.ui.administrator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.kit.pse.a1sicht.database.LocalDatabase
import edu.kit.pse.a1sicht.database.entities.Review
import edu.kit.pse.a1sicht.networking.RetrofitClient
import edu.kit.pse.a1sicht.networking.ReviewService
import edu.kit.pse.a1sicht.networking.StudentService
import edu.kit.pse.a1sicht.repository.ReviewRepository
import edu.kit.pse.a1sicht.repository.TimeslotRepository

/**
 * The ReviewModel responsible for all the information being displayed in the ListReviewActivity.
 *
 * @author Tihomir Georgiev
 */
class ListReviewViewModel(application: Application): AndroidViewModel(application) {

    // An instance of the reviewRepository
    private var reviewRepository: ReviewRepository = ReviewRepository(
        LocalDatabase.getInstance(application),
        RetrofitClient.getInstance()!!.create(ReviewService::class.java),
        RetrofitClient.getInstance()!!.create(StudentService::class.java)
    )

    // An instance of the timeslotRepository
    private var timeslotRepository = TimeslotRepository(
        LocalDatabase.getInstance(application),
        RetrofitClient.getInstance()!!.create(ReviewService::class.java),
        RetrofitClient.getInstance()!!.create(StudentService::class.java)
    )

    // All reviews as MutableLiveData
    private var _allReviews: MutableLiveData<List<Review>> = MutableLiveData()

    /**
     * Gets all reviews
     * @return All the reviews
     */
    fun getAllReviews(): LiveData<List<Review>> {
        _allReviews.value = reviewRepository.getAllReviews()
        return _allReviews
    }

    /**
     * Deletes a review by it's id
     * @param id The id of the review that is to be deleted
     */
    fun deleteReviewById(id: Int){
        timeslotRepository.getAllForReview(id)!!.forEach {
            timeslotRepository.deleteById(id, it.id!!)
        }
        reviewRepository.deleteReviewById(id)
    }
}