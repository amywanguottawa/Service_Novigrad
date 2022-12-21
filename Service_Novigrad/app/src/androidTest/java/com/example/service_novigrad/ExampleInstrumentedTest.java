package com.example.service_novigrad;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final boolean FAKE_BOOLEAN = true;

    Context mMockContext;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.service_novigrad", appContext.getPackageName());
    }

/*    @Test
    public void validateEmailAddress(){
        Register myEmailUnderTest = new Register(mMockContext);
        boolean result = myEmailUnderTest.validateEmailAddress("name@gmail.com");
        assertThat(result, is(FAKE_BOOLEAN)); //should return true if email is valid
    }*/

    //@Test

}