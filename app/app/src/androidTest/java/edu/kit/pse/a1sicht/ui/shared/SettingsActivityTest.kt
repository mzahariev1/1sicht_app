package edu.kit.pse.a1sicht.ui.shared


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
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
 * Example test class for [SettingsActivity] class.
 *
 * @author Tihomir Georgiev, Maximilian Ackermann
 */
@RunWith(AndroidJUnit4::class)
class SettingsActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityTestRule(SettingsActivity::class.java)

    /**
     * Test language change on buttons clicked
     * @throws Exception on error
     */
    @Test
    @Throws(Exception::class)
    fun testLanguageChange() {
        onView(withId(R.id.de_btn)).perform(click())
        onView(withId(R.id.save_btn)).check(matches(withText("Speichern")))
        onView(withId(R.id.gb_btn)).perform(click())
        onView(withId(R.id.save_btn)).check(matches(withText("Save")))
    }

    /**
     * Test sign out button
     * @throws Exception on error
     */
    @Test
    @Throws(Exception::class)
    fun testSignOut() {
        Intents.init()
        onView(withId(R.id.sign_out_real_btn)).perform(click())
        intended(hasComponent(LogInActivity::class.java.canonicalName))
        onView(withId(R.id.signInBtn)).check(matches(isDisplayed()))
        Intents.release()
    }

    /**
     * Test Name/Last name Textfields
     */
    @Test
    @Throws(Exception::class)
    fun testNames() {
        Intents.init()

        onView(withId(R.id.nameET))
            .perform(ViewActions.typeText("name"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.last_name_edit_text))
            .perform(ViewActions.typeText("lastName"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.name_edit_text))
            .check(matches(withText(Matchers.containsString("1"))))
        onView(withId(R.id.last_name_edit_text))
            .check(matches(withText(Matchers.containsString("info"))))

        Intents.release()
    }
}