package de.fhbielefeld.swl.KINewsBoard.WebService.Crawler;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Crawler;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerResultBaseModel;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.NewsEntryBaseModel;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by cem on 03.04.17.
 */
@RunWith(Arquillian.class)
@UsingDataSet("full_data.yml")
@Cleanup(phase = TestExecutionPhase.NONE)
public class NewsResourceTest {

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "WebService.war")
                .addPackage(NewsBoardService.class.getPackage())
                .addPackage(Crawler.class.getPackage())
                .addPackages(true, "de.fhbielefeld.swl.KINewsBoard.WebService")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Test
    @InSequence(-1)
    public void initData() {
        Crawler crawler = em.find(Crawler.class, 1);

        Assert.assertNotNull("should not be null", crawler);
        Assert.assertEquals("should be crawler 1", 1, crawler.getId());
        Assert.assertEquals("should match token", "3052541e03642874a071492753417402", crawler.getToken());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus401_when_noToken(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        Response result = webTarget.request().build("POST").invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 401", 401, result.getStatus());
    }


    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_wrongToken(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .build("POST")
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }


    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_noEntity(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .build("POST")
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }


    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_emptyEntity(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }


    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_noIdGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setContent("This is an content");
        model.setSource("Source of Evil");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_shortIdGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("mt");
        model.setContent("This is an content");
        model.setSource("Source of Evil");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_existingIdGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("mt-21734129");
        model.setContent("This is an content");
        model.setSource("Source of Evil");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_noContentGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id");
        model.setSource("Source of Evil");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_shortContentGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id");
        model.setContent("shortshor");
        model.setSource("Source of Evil");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_noSourceGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id");
        model.setContent("This is an content");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_shortSourceGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id");
        model.setContent("This is an content");
        model.setSource("mn");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_noUrlGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id");
        model.setContent("This is an content");
        model.setSource("Source of Evil");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_shortUrlGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id");
        model.setContent("This is an content");
        model.setSource("Source of Evil");
        model.setUrl("http://ba");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus200_when_minimalPropertiesGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id");
        model.setContent("This is an content");
        model.setSource("Source of Evil");
        model.setUrl("http://back.to/the/root");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 200", 200, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus200_when_allPropertiesGiven(@ArquillianResteasyResource("crawler/news") ResteasyWebTarget webTarget) {
        NewsEntryBaseModel model = new NewsEntryBaseModel();
        model.setId("my-special-id2");
        model.setContent("This is a content");
        model.setSource("Source of Evil");
        model.setUrl("http://back.to/the/root");
        model.setImage("http://back.to/the/root.png");
        //model.setDate(new Date());
        model.setTitle("This is a title");

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 200", 200, result.getStatus());
    }
}
