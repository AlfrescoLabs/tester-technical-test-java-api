/*
 * Copyright 2018 Alfresco Software, Ltd. All rights reserved.
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
import org.alfresco.utility.model.FolderModel;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Purpose of this Test is to test that the dependencies have been wired correctly and work well
 * 
 * @author meenal bhave
 */
public class SetupTest extends AbstractE2EFunctionalTest
{
    @Autowired
    protected DataSite dataSite;

    @Autowired
    protected DataContent dataContent;

    private FolderModel testFolder;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception
    {
        dataContent.usingSite(testSite).createFolder(FolderModel.getRandomFolderModel());

        dataUser.addUserToSite(testUser, testSite, UserRole.SiteContributor);

        testFolder = dataContent.usingSite(testSite).usingUser(testUser).createFolder();
    }
    
    // Test Custom Model: Finance can be used
    @Test(priority = 1)
    public void testModelFinanceCanBeUsed() throws Exception
    {
        // Create document of custom type

        FileModel customFile = FileModel.getRandomFileModel(FileType.TEXT_PLAIN, "searchContent-finance");
        Map<String, Object> properties = new HashMap<>();
        properties.put(PropertyIds.OBJECT_TYPE_ID, "D:finance:Receipt");
        properties.put(PropertyIds.NAME, customFile.getName());
        properties.put("finance:ReceiptNo", 1);
        properties.put("finance:ReceiptValue", 30);

        cmisApi.authenticateUser(testUser).usingSite(testSite)
                .usingResource(testFolder)
                .createFile(customFile, properties, VersioningState.MAJOR)
                .assertThat().existsInRepo();

        Assert.assertTrue(waitForIndexing("cm:name:" + customFile.getName(),true),"New content could not be found");
    }
}
