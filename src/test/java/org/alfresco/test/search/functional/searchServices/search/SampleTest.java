/*
 * Copyright 2019 Alfresco Software, Ltd. All rights reserved.
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.test.search.functional.searchServices.search;

import org.alfresco.test.search.functional.AbstractE2EFunctionalTest;
import org.alfresco.utility.constants.UserRole;
import org.alfresco.utility.data.DataContent;
import org.alfresco.utility.data.DataSite;
import org.alfresco.utility.model.FileModel;
import org.alfresco.utility.model.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Purpose of this TestClass is to test that the search range query tests work as expected with CustomModels
 * 
 * @author meenal bhave
 */

public class SampleTest extends AbstractE2EFunctionalTest
{
    @Autowired
    protected DataSite dataSite;

    @Autowired
    protected DataContent dataContent;

    private FileModel fileBanana, fileYellowBanana, fileBigYellowBanana, fileBigBananaBoat, fileYellowBananaBigBoat,
            fileBigYellowBoat;

    @BeforeClass(alwaysRun = true)
    public void dataPreparation() throws Exception
    {
        serverHealth.assertServerIsOnline();

        dataUser.addUserToSite(testUser, testSite, UserRole.SiteContributor);

        // Create files with different phrases
        fileBanana = new FileModel(unique_searchString + "-1.txt", "banana", "phrase searching", FileType.TEXT_PLAIN,
                "banana");
        dataContent.usingUser(testUser).usingSite(testSite).createContent(fileBanana);

        fileYellowBanana = new FileModel(unique_searchString + "-2.txt", "yellow banana", "phrase searching",
                FileType.TEXT_PLAIN, "yellow banana");
        dataContent.usingUser(testUser).usingSite(testSite).createContent(fileYellowBanana);

        fileBigYellowBanana = new FileModel(unique_searchString + "-3.txt", "big yellow banana", "phrase searching",
                FileType.TEXT_PLAIN, "big yellow banana");
        dataContent.usingUser(testUser).usingSite(testSite).createContent(fileBigYellowBanana);

        fileBigBananaBoat = new FileModel(unique_searchString + "-4.txt", "big boat", "phrase searching",
                FileType.TEXT_PLAIN, "big boat");
        dataContent.usingUser(testUser).usingSite(testSite).createContent(fileBigBananaBoat);

        fileYellowBananaBigBoat = new FileModel(unique_searchString + "-5.txt", "yellow banana big boat",
                "phrase searching", FileType.TEXT_PLAIN, "yellow banana big boat");
        dataContent.usingUser(testUser).usingSite(testSite).createContent(fileYellowBananaBigBoat);

        fileBigYellowBoat = new FileModel(unique_searchString + "-6.txt", "big yellow boat", "phrase searching",
                FileType.TEXT_PLAIN, "big yellow boat");
        dataContent.usingUser(testUser).usingSite(testSite).createContent(fileBigYellowBoat);

        waitForContentIndexing(fileBigYellowBoat.getName(), true);

    }

    @Test(priority = 1)
    public void testSample()
    {
        // TODO: Implement a set of tests to test the Search API
        Assert.assertEquals(1, 1);
    }
}
