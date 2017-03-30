package de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Ranking;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import org.apache.lucene.search.Sort;

import java.util.List;

/**
 * Schnittstelle zum sortieren von News Beiträgen.
 *
 * Zunächst werden die Felder die durch den Indexer zur Sortierung verwendet
 * werden sollen mit @<code>getSort()</code> abgerufen und der Abfrage hinzugefügt.
 *
 * Nach dem der Indexer die Ergebnisse zurückgeliefert hat, kann die Liste mit
 * @<code>sortList(...)</code> zusätzlich sortiert werden.
 *
 */
public interface RankingModule {

    /**
     * Wird vor der Abfrage an dem Indexer aufgerufen, um die Sortierung zu definieren.
     *
     * Beispiel:
     *
     * @<code>
     *  new Sort(new SortField("date", SortField.Type.LONG, true),
     *           new SortField("rating", SortField.Type.INT, true))
     * </code>
     *
     * Verfügbare Felder:
     * - title
     * - content
     * - date
     * - rating
     * - SortField.FIELD_SCORE
     *
     * Werden keine Suchfelder definiert, wird die Standard Sortierung vom Indexer verwendet.
     *
     * @return Das Sortobjekt das die Sortfelder beinhaltet. Null wenn Standard Sortierung verwendet werden soll.
     *
     */
    Sort getSort();

    /**
     * Wird aufgerufen um das Ergebnis vom Indexer nach weiteren Feldern zu sortieren.
     *
     * @param entries Liste der Newsbeiträge die manuell nachsortiert werden können.
     */
    void sortList(List<NewsEntry> entries);
}
