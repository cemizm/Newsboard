package de.fhbielefeld.swl.KINewsBoard.BusinessLayer;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.Analyzer;
import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 31.03.17.
 */
@RunWith(Arquillian.class)
public class NewsBoardServiceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(NewsBoardService.class)
                .addPackage(Analyzer.class.getPackage())
                .addAsManifestResource("test-persistence.xml", "persistence.xml");
    }

    @PersistenceContext(name = "NewsBoardPU")
    private EntityManager entityManager;

    @EJB
    private NewsBoardService newsBoardService;

    /**
     * Run Indexer before tests
     *
     * @throws InterruptedException
     */
    @Before
    public void setup() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }

    @Test
    public void getNewsEntries_should_returnEmptyRresult() {
        List<NewsEntry> newsEntries = newsBoardService.getNewsEntries(1, null, null);

        Assert.assertEquals("should be empty", 0, newsEntries.size());
    }

    @Test(expected = EJBException.class)
    public void getNewsEntries_should_throwIndexOutOfBoundException_when_Page0() {
        newsBoardService.getNewsEntries(0, null, null);
    }

    @Test
    @UsingDataSet({"crawler.yml", "newsentry.yml"})
    public void getNewsEntries_should_return8NewsEntries() {
        List<NewsEntry> newsEntries = newsBoardService.getNewsEntries(1, null, null);

        Assert.assertEquals("should return 8 news entries", 8, newsEntries.size());
    }

    @Test
    @UsingDataSet({"crawler.yml", "newsentry.yml"})
    public void getNewsEntries_should_return3NewsEntries_when_searchForKeyword() {
        List<NewsEntry> newsEntries = newsBoardService.getNewsEntries(1, "Bielefeld", null);

        Assert.assertEquals("should find 3 news entries", 3, newsEntries.size());

        List<String> ids = newsEntries.stream().map(newsEntry -> newsEntry.getId()).collect(Collectors.toList());

        Assert.assertEquals("should contain id 'mt-21734520'", true, ids.contains("mt-21734520"));
        Assert.assertEquals("should contain id 'tw-846280866313748481'", true, ids.contains("tw-846280866313748481"));
        Assert.assertEquals("should contain id 'tw-846709564556951553'", true, ids.contains("tw-846709564556951553"));
    }

    @Test
    @UsingDataSet({"crawler.yml", "newsentry.yml"})
    public void getNewsEntries_should_returnEmptyResult_when_pageGreaterThenAvailable() {
        List<NewsEntry> newsEntries = newsBoardService.getNewsEntries(2, "Bielefeld", null);

        Assert.assertEquals("should return empty result set", 0, newsEntries.size());
    }

    @Test(expected = EJBException.class)
    public void publishNewsEntry_should_throwIllegalArgumentException_when_crawlerAndNewsEntryNull() throws AuthenticationException {
        newsBoardService.publishNewsEntry(null, null);
    }

    @Test(expected = EJBException.class)
    public void publishNewsEntry_should_throwIllegalArgumentException_when_crawlerNull() throws AuthenticationException {
        NewsEntry entry = new NewsEntry();

        entry.setId("mt-21734156");
        entry.setContent("This is the content");
        newsBoardService.publishNewsEntry(null, entry);
    }

    @Test(expected = EJBException.class)
    @UsingDataSet({"crawler.yml"})
    public void publishNewsEntry_should_throwIllegalArgumentException_when_newsEntryNull() throws AuthenticationException {
        newsBoardService.publishNewsEntry(newsBoardService.getCrawlerByToken("0303d0f97b596ebcfcbc512e286198d2"), null);
    }

    @Test(expected = EJBException.class)
    @UsingDataSet({"crawler.yml"})
    public void publishNewsEntry_should_throwIllegalArgumentException_when_newsEntryIdNull() throws AuthenticationException {
        NewsEntry entry = new NewsEntry();

        entry.setContent("This is an content");

        newsBoardService.publishNewsEntry(newsBoardService.getCrawlerByToken("0303d0f97b596ebcfcbc512e286198d2"), entry);
    }

    @Test(expected = EJBException.class)
    @UsingDataSet({"crawler.yml", "newsentry.yml"})
    public void publishNewsEntry_should_throwIllegalArgumentException_when_newsEntryExists() throws AuthenticationException {
        NewsEntry entry = new NewsEntry();

        entry.setId("mt-21734156");
        entry.setContent("This is the content");

        newsBoardService.publishNewsEntry(newsBoardService.getCrawlerByToken("0303d0f97b596ebcfcbc512e286198d2"), entry);
    }

    @UsingDataSet({"crawler.yml"})
    public void publishNewsEntry_should_publishNewsEntry() throws AuthenticationException {
        NewsEntry entry = new NewsEntry();

        entry.setId("test-123");
        entry.setContent("This is the content");

        newsBoardService.publishNewsEntry(newsBoardService.getCrawlerByToken("0303d0f97b596ebcfcbc512e286198d2"), entry);

        Assert.assertEquals("should return 1 news entries", 1, newsBoardService.getNewsEntries(1, null, null).size());

    }
}
