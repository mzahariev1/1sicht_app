package edu.kit.pse.a1sicht.ui.shared

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.kit.pse.a1sicht.R
import edu.kit.pse.a1sicht.ui.employee.WaitingScreenActivity
import edu.kit.pse.a1sicht.ui.student.HomeStudentActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Testing class for the [RegisterActivity] class.
 *
 * @author Tihomir Georgiev, Maximilian Ackermann
 */
@RunWith(AndroidJUnit4::class)
class RegisterActivityTest{

    @get:Rule
    var activityScenarioRule = ActivityTestRule(RegisterActivity::class.java)

    /**
     * Test if the additional properties for student are shown only when
     * the radio button for student is checked.
     * @throws Exception on error
     */
    @Test
    @Throws(Exception::class)
    fun showStudentProperties(){
        onView(withId(R.id.empbtn)).perform(click())
        onView(withId(R.id.m_number)).check(matches(not(isDisplayed())))
        onView(withId(R.id.m_number_text)).check(matches(not(isDisplayed())))
        onView(withId(R.id.stdbtn)).perform(click())
        onView(withId(R.id.m_number)).check(matches(isDisplayed()))
        onView(withId(R.id.m_number_text)).check(matches(isDisplayed()))
    }

    /**
     * Test registration as an employee.
     * @throws Exception on error
     */
    @Test
    @Throws(Exception::class)
    fun employeeRegister() {
        Intents.init()

        onView(withId(R.id.empbtn)).perform(click())
        onView(withId(R.id.confirm_button)).perform(click())

        intended(hasComponent(WaitingScreenActivity::class.java.canonicalName))
        Intents.release()
    }

    /**
     * Test registration as a student.
     * @throws Exception on error
     */
    @Test
    @Throws(Exception::class)
    fun studentRegister() {
        Intents.init()

        onView(withId(R.id.stdbtn)).perform(click())
        onView(withId(R.id.confirm_button)).perform(click())

        onView(withId(R.id.title)).check(doesNotExist())
        onView(withId(R.id.m_number)).perform(typeText("123456"),closeSoftKeyboard())
        onView(withId(R.id.confirm_button)).perform(click())

        onView(withId(R.id.title)).check(matches(isDisplayed()))
        intended(hasComponent(HomeStudentActivity::class.java.canonicalName))
        Intents.release()
    }

}