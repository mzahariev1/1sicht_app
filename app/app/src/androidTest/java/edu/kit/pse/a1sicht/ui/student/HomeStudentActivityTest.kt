package edu.kit.pse.a1sicht.ui.student

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


/**
 *TESTS HomeStudentActivity
 *
 *@author Maximilian Ackermann
 */
@RunWith(AndroidJUnit4::class)
class HomeStudentActivityTest{

    @get:Rule
    var activityScenarioRule = ActivityTestRule(HomeStudentActivity::class.java)

    /**
     * Tests if the Sign up button brings the user to StudentReviewActivity
     */
    @Test
    @Throws(Exception::class)
    fun testDeleteButton() {
        Intents.init()

        onView(withId(R.id.review_sign_up_btn)).perform(click())
        onView(withId(R.id.reviewName)).check(matches(isDisplayed()))

        Intents.release()
    }
}