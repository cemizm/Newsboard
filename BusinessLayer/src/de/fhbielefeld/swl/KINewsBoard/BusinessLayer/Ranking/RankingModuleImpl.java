package de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Ranking;

import de.fhbielefeld.swl.KINewsBoard.DataLayer.DataModels.NewsEntry;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

import java.util.List;

public class RankingModuleImpl implements RankingModule {

    @Override
    public Sort getSort() {
        return new Sort(new SortField("date", SortField.Type.LONG, true),
                new SortField("rating", SortField.Type.INT, true),
                SortField.FIELD_SCORE);
    }

    @Override
    public void sortList(List<NewsEntry> entries) {

    }
}
