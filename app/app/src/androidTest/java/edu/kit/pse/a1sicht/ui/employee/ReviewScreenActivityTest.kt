package edu.kit.pse.a1sicht.ui.employee

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
 *Testing class for the ReviewScreenActivity
 *
 *@author Maximilian Ackermann
 */
@RunWith(AndroidJUnit4::class)
class ReviewScreenActivityTest{

    @get:Rule
    var activityScenarioRule = ActivityTestRule(ReviewScreenActivity::class.java)

    /**
     * Tests if the DeleteButton brings user back to HomeEmployeeActivity
     */
    @Test
    @Throws(Exception::class)
    fun testDeleteButton() {
        Intents.init()

        onView(withId(R.id.delete_btn)).perform(click())
        intended(hasComponent(HomeEmployeeActivity::class.java.canonicalName))
        onView(withId(R.id.btn_create_review)).check(matches(isDisplayed()))

        Intents.release()
    }
}