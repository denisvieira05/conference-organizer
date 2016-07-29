package org.js.denisvieira.conferenceorganizer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.js.denisvieira.conferenceorganizer.activitys.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by denisvieira on 28/07/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

//    @Test
//    public void clickFileImageOpenFileExplorer() throws Exception{
//
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("text/plain");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//
//        Intents.init();
//        Context targetContext = InstrumentationRegistry.getTargetContext();
//        AssetManager assetManager = targetContext.getResources().getAssets();
//        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.createChooser(intent, "Select txt file"), hasData(Intent.ACTION_CHOOSER)));
//        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));
//        Espresso.onView(ViewMatchers.withId(R.id.iv_IconFile))
//                .perform(ViewActions.click());
//        intended(expectedIntent);
//        Intents.release();
////
//    }
}
