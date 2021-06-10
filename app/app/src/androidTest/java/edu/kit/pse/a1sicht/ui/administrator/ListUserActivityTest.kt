package edu.kit.pse.a1sicht.ui.administrator

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
 *Class for testing ListUserActivity
 *
 */
@RunWith(AndroidJUnit4::class)
class ListUserActivityTest{

    @get:Rule
    var activityScenarioRule = ActivityTestRule(ListUserActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun testUsersSearch() {
        Intents.init()

        Intents.release()
    }
}