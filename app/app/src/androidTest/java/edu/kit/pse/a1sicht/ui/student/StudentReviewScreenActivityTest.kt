package edu.kit.pse.a1sicht.ui.student

import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import edu.kit.pse.a1sicht.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import androidx.test.espresso.intent.Intents
import org.hamcrest.Matchers


/**
 * Example test class for [StudentReviewScreenActivity] class.
 *
 *@author Maximilian Ackermann
 */
@RunWith(AndroidJUnit4::class)
class StudentReviewScreenActivityTest{

    @get:Rule
    var activityScenarioRule = ActivityTestRule(StudentReviewScreenActivity::class.java)

    /**
     * Tests the TimeSlotSpinner
     */
    @Test
    @Throws(Exception::class)
    fun testLoginButton() {
        Intents.init()
        onView(withId(R.id.timeslotSpinner))
            .perform(click())
        Espresso.onData(Matchers.hasToString(Matchers.startsWith("1")))
            .perform(click())

        onView(withId(R.id.timeslotSpinner))
            .check(matches(withSpinnerText(Matchers.containsString("1"))));


        Intents.release()
    }
}