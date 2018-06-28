package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.action.ViewActions.replaceText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.containsString;


import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 * Referenced this Stackoverflow article to see how to check if a an item is at a certain position
 * in a list. https://stackoverflow.com/questions/34630600/espresso-check-if-the-textview-exists-
 * in-the-listview
 *
 * Referenced this Stackoverflow article for ordering the tests https://stackoverflow.com/questions
 * /25308301/test-order-with-espresso
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EspressoTest {

    String businessNumberText = "123456789";
    String nameText = "Sample Name";
    String primaryBusinessText = "Fisher";
    String addressText = "123 Sample Street";
    String provinceText = "NB";

    String businessNumberTextUpdate = "111111111";
    String nameTextUpdate = "Sample SecondName";
    String primaryBusinessTextUpdate = "Fish Monger";
    String addressTextUpdate = "111 SampleStreet";
    String provinceTextUpdate = "QC";


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void A_ViewList() throws Exception {
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText(startsWith("Samp"))));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).check(matches(withText(startsWith("gg"))));
    }

    @Test
    public void B_CreateContact() throws Exception {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.businessNumber)).perform(typeText(businessNumberText),
                closeSoftKeyboard());
        onView(withId(R.id.name)).perform(typeText(nameText), closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(typeText(primaryBusinessText),
                closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText(addressText), closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText(provinceText),
                closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).check(matches(withText(startsWith("Sample Name"))));
    }

    @Test
    public void C_ReadContact() throws Exception {
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).check(matches(withText(startsWith("Sample Name"))));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());
        onView(withId(R.id.businessNumber)).check(matches(withText(containsString("123456789"))));
        onView(withId(R.id.name)).check(matches(withText(containsString("Sample Name"))));
        onView(withId(R.id.primaryBusiness)).check(matches(withText(containsString("Fisher"))));
        onView(withId(R.id.address)).check(matches(withText(containsString("123 Sample Street"))));
        onView(withId(R.id.province)).check(matches(withText(containsString("NB"))));
    }

    @Test
    public void D_UpdateContact() throws Exception {
        Thread.sleep(2000);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).check(matches(withText(startsWith("Sample Name"))));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());

        onView(withId(R.id.businessNumber)).perform(replaceText(businessNumberTextUpdate),
                closeSoftKeyboard());
        onView(withId(R.id.name)).perform(replaceText(nameTextUpdate), closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(replaceText(primaryBusinessTextUpdate),
                closeSoftKeyboard());
        onView(withId(R.id.address)).perform(replaceText(addressTextUpdate), closeSoftKeyboard());
        onView(withId(R.id.province)).perform(replaceText(provinceTextUpdate),
                closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());

        Espresso.pressBack();

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).check(matches(withText(startsWith("Sample SecondName"))));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());

        onView(withId(R.id.businessNumber)).check(matches(withText(containsString(businessNumberTextUpdate))));
        onView(withId(R.id.name)).check(matches(withText(containsString(nameTextUpdate))));
        onView(withId(R.id.primaryBusiness)).check(matches(withText(containsString(primaryBusinessTextUpdate))));
        onView(withId(R.id.address)).check(matches(withText(containsString(addressTextUpdate))));
        onView(withId(R.id.province)).check(matches(withText(containsString(provinceTextUpdate))));
    }

}