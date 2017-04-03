package de.fhbielefeld.swl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.swl.KINewsBoard.BusinessLayer.NewsBoardService;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import de.fhbielefeld.swl.KINewsBoard.WebService.Shared.ViewModels.AnalyzerResultBaseModel;
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
                .addPackage(Analyzer.class.getPackage())
                .addPackages(true, "de.fhbielefeld.swl.KINewsBoard.WebService")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Test
    @InSequence(-1)
    public void initData() {
        Analyzer analyzer = em.find(Analyzer.class, 1);

        Assert.assertNotNull("should not be null", analyzer);
        Assert.assertEquals("should be anlyzer 1", 1, analyzer.getId());
        Assert.assertEquals("should match token", "3052541e03642874a071492753417402", analyzer.getToken());
    }

    @Test
    @RunAsClient
    public void getNews_should_returnStatus401_when_noAuthToken(@ArquillianResteasyResource("analyzer/news") ResteasyWebTarget webTarget) {

        Response result = webTarget.request().buildGet().invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 401", 401, result.getStatus());
    }

    @Test
    @RunAsClient
    public void getNews_should_returnStatus401_when_wrongAuthToken(@ArquillianResteasyResource("analyzer/news") ResteasyWebTarget webTarget) {

        Response result = webTarget.request().header("token", "NOT EXISTING TOKEN").buildGet().invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 401", 401, result.getStatus());
    }

    @Test
    @RunAsClient
    public void getNews_should_returnStatus200_when_correctToken(@ArquillianResteasyResource("analyzer/news") ResteasyWebTarget webTarget) {

        Response result = webTarget.request().header("token", "3052541e03642874a071492753417402").buildGet().invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 200", 200, result.getStatus());
    }

    @Test
    @RunAsClient
    public void getNews_should_returnOnly6Entries_when_analyzer1(@ArquillianResteasyResource("analyzer/news") ResteasyWebTarget webTarget) {

        Response result = webTarget.request().header("token", "3052541e03642874a071492753417402").buildGet().invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 200", 200, result.getStatus());
        @SuppressWarnings(value = "unchecked")
        List<NewsEntry> newsEntries = result.readEntity(List.class);

        Assert.assertNotNull(newsEntries);
        Assert.assertEquals("should be 6 entries", 6, newsEntries.size());
    }

    @Test
    @RunAsClient
    public void getNews_should_returnAllEntries_when_analyzer2(@ArquillianResteasyResource("analyzer/news") ResteasyWebTarget webTarget) {

        Response result = webTarget.request().header("token", "0303d0f97b596ebcfcbc512e286198d2").buildGet().invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 200", 200, result.getStatus());
        @SuppressWarnings(value = "unchecked")
        List<NewsEntry> newsEntries = result.readEntity(List.class);

        Assert.assertNotNull(newsEntries);
        Assert.assertEquals("should be 8 entries", 8, newsEntries.size());
    }


    @Test
    @RunAsClient
    public void publish_should_returnStatus401_when_noToken(@ArquillianResteasyResource("analyzer/news/nonewsid") ResteasyWebTarget webTarget) {
        Response result = webTarget.request().build("POST").invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 401", 401, result.getStatus());
    }


    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_wrongToken(@ArquillianResteasyResource("analyzer/news/nonewsid") ResteasyWebTarget webTarget) {
        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .build("POST")
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }


    @Test
    @RunAsClient
    public void publish_should_returnStatus500_when_noNewsId(@ArquillianResteasyResource("analyzer/news/") ResteasyWebTarget webTarget) {
        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .build("POST")
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 500", 500, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_wrongNewsId(@ArquillianResteasyResource("analyzer/news/asd") ResteasyWebTarget webTarget) {
        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .build("POST")
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus400_when_alreadyAnalyzed(@ArquillianResteasyResource("analyzer/news/mt-21734129") ResteasyWebTarget webTarget) {
        AnalyzerResultBaseModel model = new AnalyzerResultBaseModel();
        model.setSentenceResults(new ArrayList<>());

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 400", 400, result.getStatus());
    }

    @Test
    @RunAsClient
    public void publish_should_returnStatus200(@ArquillianResteasyResource("analyzer/news/mt-21734520") ResteasyWebTarget webTarget) {
        AnalyzerResultBaseModel model = new AnalyzerResultBaseModel();
        model.setSentenceResults(new ArrayList<>());

        Response result = webTarget.request()
                .header("token", "3052541e03642874a071492753417402")
                .buildPost(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE))
                .invoke();

        Assert.assertNotNull(result);
        Assert.assertEquals("should return status 200", 200, result.getStatus());
    }
}
