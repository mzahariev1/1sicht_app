package edu.kit.pse.a1sicht.ui.student

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import edu.kit.pse.a1sicht.R
import edu.kit.pse.a1sicht.database.entities.Review
import edu.kit.pse.a1sicht.ui.administrator.ListReviewViewModel
import edu.kit.pse.a1sicht.ui.shared.SettingsActivity
import edu.kit.pse.a1sicht.ui.utils.BaseActivity
import edu.kit.pse.a1sicht.ui.utils.ReviewAdapter
import kotlinx.android.synthetic.main.activity_list_reviews_student.*
import java.util.ArrayList

/**
 * The activity for the screen where a student can see all the reviews and choose for which
 * he wats to sign up
 * @author Hristo Klechorov
 */
class ListReviewsActivity :BaseActivity(){

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ReviewAdapter
    private lateinit var listReviewViewModel: ListReviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_reviews_student)
        listReviewViewModel = ViewModelProviders.of(this).get(ListReviewViewModel::class.java)
        buildRecyclerView()

        setUpBtns()
    }

    /**
     * Sets up the list with all the reviews
     */
    private fun buildRecyclerView() {
        //Initialise the linear layout manager and assign it to our recycle view
        linearLayoutManager = LinearLayoutManager(this)
        listReview.layoutManager = linearLayoutManager

        getAll()
    }

    /**
     * Populates the reviews ArrayList with all reviews from the database
     */
    private fun getAll() {
        val reviews: ArrayList<Review> = ArrayList()
        listReviewViewModel.getAllReviews().observe(this, Observer<List<Review>> { list ->
            list.forEach {
                reviews.add(it)
            }
            createAdapterForReviews(reviews)
        })
    }

    /**
     * Initializes the adapter and implements the onClick methods
     */
    private fun createAdapterForReviews(reviews: ArrayList<Review>) {
        adapter = ReviewAdapter(reviews, false)
        listReview.adapter = adapter

        val mListener = object: ReviewAdapter.OnItemClickListener{
            override fun onDeleteClick(position: Int) {
                // Does nothing, because there is no delete button
            }

            override fun onItemClick(position:Int ,v: View) {
                val context = v.context

                val showReviewIntent = Intent(context, StudentReviewScreenActivity::class.java)
                showReviewIntent.putExtra("reviewID" ,reviews[position].id)
                context.startActivity(showReviewIntent)
            }
        }

        adapter.setOnItemClickListener(mListener)
    }

    /**
     * Sets up the button onClicks
     */
    private fun setUpBtns() {
        // Start SettingsActivity when the button is pressed
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}