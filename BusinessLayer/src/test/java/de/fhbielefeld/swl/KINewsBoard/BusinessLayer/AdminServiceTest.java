package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.GroupSet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;


/**
 * Created by cem on 28.03.17.
 */
@RunWith(Arquillian.class)
public class AdminServiceTest {


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(AdminService.class)
                .addPackage(Analyzer.class.getPackage())
                .addAsManifestResource("test-persistence.xml", "persistence.xml");
    }

    @EJB
    private AdminService adminService;


    @Test
    @UsingDataSet("analyzer.yml")
    public void getAllAnalyzer_should_return3Analyzer() {
        Assert.assertEquals("should be 3", 3, adminService.getAllAnalyzer().size());
    }

    @Test
    @UsingDataSet("analyzer.yml")
    public void getAnalyzer_should_returnAnalyzer1() {
        Analyzer analyzer = adminService.getAnalyzer(1);
        Assert.assertEquals("should match name", "Analyzer #1", analyzer.getName());
        Assert.assertEquals("should match token", "3052541e03642874a071492753417402", analyzer.getToken());
    }

    @Test
    @UsingDataSet({"analyzer.yml", "groupset.yml", "groupset_analyzer.yml"})
    public void getAnalyzer_should_returnAnalyzer1With2Groups() {
        Analyzer analyzer = adminService.getAnalyzer(1);
        Assert.assertEquals("should match name", "Analyzer #1", analyzer.getName());
        Assert.assertEquals("should be in 2 groups", 2, analyzer.getGroupSets().size());
    }


    @Test
    @UsingDataSet("analyzer.yml")
    public void getAnalyzer_should_returnNull_when_getNotExistingId() {
        Analyzer analyzer = adminService.getAnalyzer(4);
        Assert.assertEquals("should be null", null, analyzer);
    }

    @Test
    public void createAnalyzer_should_createAnalyzer() {
        Analyzer analyzer = new Analyzer();
        analyzer.setName("Test Name");
        analyzer.setToken("1d93a0ed080f4074925674c3028e614e");

        Assert.assertEquals("should be empty", 0, adminService.getAllAnalyzer().size());
        adminService.createAnalyzer(analyzer);
        Assert.assertEquals("should be 1", 1, adminService.getAllAnalyzer().size());
    }

    @Test(expected = EJBException.class)
    public void createAnalyzer_should_throwIllegalArgumentException_whenAnalyzerNull() {
        adminService.createAnalyzer(null);
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    @UsingDataSet("analyzer.yml")
    public void createAnalyzer_should_throwIllegalArgumentException_whenTokenExisits() {
        Analyzer analyzer = new Analyzer();
        analyzer.setName("Test Name");
        analyzer.setToken("3052541e03642874a071492753417402");

        adminService.createAnalyzer(analyzer);
    }

    @Test(expected = EJBException.class)
    public void createAnalyzer_should_throwIllegalArgumentException_whenNameEmpty() {
        Analyzer analyzer = new Analyzer();
        analyzer.setToken("3052541e03642874a071492753417402");
        adminService.createAnalyzer(analyzer);
    }

    @Test(expected = EJBException.class)
    public void createAnalyzer_should_throwIllegalArgumentException_whenTokenEmpty() {
        Analyzer analyzer = new Analyzer();
        analyzer.setName("Test Name");
        adminService.createAnalyzer(analyzer);
    }

    @Test
    @UsingDataSet("analyzer.yml")
    public void updateAnalyzer_should_updateNameAndToken() {
        Analyzer analyzer = adminService.getAnalyzer(1);
        analyzer.setName("Test Name");
        analyzer.setToken("newtoken");
        adminService.updateAnalyzer(analyzer);

        Analyzer analyzer2 = adminService.getAnalyzer(1);
        Assert.assertEquals("should update name", "Test Name", analyzer2.getName());
        Assert.assertEquals("should update token", "newtoken", analyzer2.getToken());
    }

    @Test(expected = EJBException.class)
    public void updateAnalyzer_should_throwIllegalArgumentException_whenNull() {
        adminService.updateAnalyzer(null);
    }

    @Test(expected = EJBException.class)
    public void updateAnalyzer_should_throwIllegalArgumentException_whenNotExists() {
        adminService.updateAnalyzer(new Analyzer());
    }

    @Test
    @UsingDataSet({"analyzer.yml", "groupset.yml", "groupset_analyzer.yml"})
    public void updateAnalyzer_should_remainIn1Group() {
        Analyzer analyzer = adminService.getAnalyzer(1);
        analyzer.getGroupSets().remove(analyzer.getGroupSets().stream().findFirst().get());
        adminService.updateAnalyzer(analyzer);

        Analyzer analyzer2 = adminService.getAnalyzer(1);
        Assert.assertEquals("should remain in 1 group", 1, analyzer2.getGroupSets().size());
    }

    @Test
    @UsingDataSet({"analyzer.yml", "groupset.yml", "groupset_analyzer.yml"})
    public void updateAnalyzer_should_BeInGroup3() {
        Analyzer analyzer = adminService.getAnalyzer(1);
        analyzer.getGroupSets().clear();
        analyzer.getGroupSets().add(adminService.getGroupSet(3));
        adminService.updateAnalyzer(analyzer);

        Analyzer analyzer2 = adminService.getAnalyzer(1);
        Assert.assertEquals("should be in group 3", "Group #3", analyzer2.getGroupSets().stream().findFirst().get().getName());
    }

    @Test
    @UsingDataSet("analyzer.yml")
    public void deleteAnalyzerTest_should_remain2() {
        Assert.assertEquals("should initially contain 3", 3, adminService.getAllAnalyzer().size());
        adminService.deleteAnalyzer(1);
        Assert.assertEquals("should remain 2", 2, adminService.getAllAnalyzer().size());
    }

    @Test(expected = EJBException.class)
    @UsingDataSet("analyzer.yml")
    public void deleteAnalyzerTest_should_throwIllegalArgumentException_whenIdNotExists() {
        adminService.deleteAnalyzer(4);
    }

    @Test
    @UsingDataSet("analyzer.yml")
    public void deleteAnalyzerTest_withObject_should_remain2() {
        Assert.assertEquals("should initially contain 3", 3, adminService.getAllAnalyzer().size());
        adminService.deleteAnalyzer(adminService.getAnalyzer(1));
        Assert.assertEquals("should remain 2", 2, adminService.getAllAnalyzer().size());
    }

    @Test(expected = EJBException.class)
    @UsingDataSet("analyzer.yml")
    public void deleteAnalyzerTest_withObject_should_throwIllegalArgumentException_whenIdNotExists() {
        adminService.deleteAnalyzer(null);
    }
}
