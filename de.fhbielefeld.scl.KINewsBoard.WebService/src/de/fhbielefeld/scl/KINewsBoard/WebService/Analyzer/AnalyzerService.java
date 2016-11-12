package de.fhbielefeld.scl.KINewsBoard.WebService.Analyzer;

import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.AnalyzerResultModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.Models.NewsModel;
import de.fhbielefeld.scl.KINewsBoard.BusinessLayer.NewsBoardManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by cem on 10.11.16.
 */


import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cem on 10.11.16.
 */

@ApplicationPath("/analyzer")
public class AnalyzerService extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();


        return s;
    }
}