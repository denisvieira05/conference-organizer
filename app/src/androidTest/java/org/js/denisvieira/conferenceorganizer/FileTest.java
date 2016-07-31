package org.js.denisvieira.conferenceorganizer;

import android.content.Context;

import junit.framework.Assert;

import org.js.denisvieira.conferenceorganizer.utils.FileUtils;
import org.junit.Test;

import java.util.ArrayList;

import android.support.test.InstrumentationRegistry;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


/**
 * Created by denisvieira on 26/07/16.
 */
public class FileTest{

    FileUtils fileUtils = new FileUtils();

    @Test
    public void verifyExtensionOfFileWithSuccess(){
        Boolean equalFormatWithDefinedExtension = fileUtils.fileIsCorrect("proposals.txt","txt");
        assertTrue(equalFormatWithDefinedExtension);

        Boolean differentExtensionWithDefinedFormat = fileUtils.fileIsCorrect("proposals.txt","doc");
        assertFalse(differentExtensionWithDefinedFormat);
//
//        Boolean fileHasNoExtension = fileUtils.fileIsCorrect("proposals","txt");
//        assertFalse(fileHasNoExtension);
    }

    @Test
    public void generateArrayofFileCorrectly(){
        ArrayList<String> lectures;
        Context appContext = InstrumentationRegistry.getTargetContext();

        lectures = fileUtils.getLecturesStrings("proposals.txt", appContext);
        boolean arraySizeEqualToTheFileLineNumber = lectures.size() == 19;
        assertTrue(arraySizeEqualToTheFileLineNumber);

    }





}
