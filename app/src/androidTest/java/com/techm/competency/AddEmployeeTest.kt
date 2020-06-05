package com.techm.competency

import android.R
import android.content.Intent
import android.service.autofill.Validators.not
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.techm.competency.view.AddEmployeeActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches


@RunWith(AndroidJUnit4::class)
class AddEmployeeTest {
    @get: Rule
    val activityRule: ActivityTestRule<AddEmployeeActivity> =
        ActivityTestRule(AddEmployeeActivity::class.java, false, false)
    private val intent = Intent()

    @Before
    fun setUp() {
        activityRule.launchActivity(intent)
    }

    /**
     * check if app launch successfully
     */
    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(AddEmployeeActivity::class.java)
    }

   /* @Test
    @Throws(Exception::class)
    fun checkTextView_isDisplayed_and_notEmpty() {
        onView(withId(R.id.button1)).perform(ViewActions.click());

        // passes if the textView does not match the empty string
        onView(withId(R.)).check(matches(not(withText(""))))
    }*/
}