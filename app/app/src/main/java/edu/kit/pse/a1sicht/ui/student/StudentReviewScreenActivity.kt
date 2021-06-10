package edu.kit.pse.a1sicht.ui.student

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import edu.kit.pse.a1sicht.R
import edu.kit.pse.a1sicht.ui.shared.SettingsActivity
import edu.kit.pse.a1sicht.ui.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_home_admin.*

/**
 * The activity for the review screen that appears to the student
 * @author Hristo Klechorov
 */
class StudentReviewScreenActivity: BaseActivity() {

    // Array with possible choices
    private val items = arrayOf("5", "10", "15", "20", "25", "30")
    // when repository is ready must be implemented
    private var isRegistered = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_student_review_screen)
        isRegistered()

        val timeslotSpinner = findViewById<Spinner>(R.id.timeslotSpinner)

        // Adapter for the drop-down menu with the options.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeslotSpinner!!.setAdapter(adapter)

        setUpBtns()
    }

    /**
     * Checks if the student has already registered himself for the review
     * If that is the case an alternative hidden part of the layout becomes visible
     */
    private fun isRegistered() {
        if (isRegistered) {
        val tv_timeslot = findViewById<TextView>(R.id.timeslot_tv)
        val tv_showTimselot = findViewById<TextView>(R.id.tv_showTimeslot)
        val btn_changeTimeslot = findViewById<Button>(R.id.btn_changeTimeslot)
        val btn_deregister = findViewById<Button>(R.id.btn_deregister)

        val timeslotUnsigned = findViewById<TextView>(R.id.timeslot)
        val spn_timeslot = findViewById<Spinner>(R.id.timeslotSpinner)
        val btn_signUp = findViewById<Button>(R.id.review_sign_up_btn)


            tv_timeslot.visibility = View.VISIBLE
            tv_showTimselot.visibility = View.VISIBLE
            btn_changeTimeslot.visibility = View.VISIBLE
            btn_deregister.visibility = View.VISIBLE
            timeslotUnsigned.visibility = View.GONE
            spn_timeslot.visibility = View.GONE
            btn_signUp.visibility = View.GONE
        }
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