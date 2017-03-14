/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class FetcherFactory implements IFetcherFactory {

    private final List<String> availableTypes
            = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> jokes = new ArrayList<IJokeFetcher>();
        String[] jokesArr = jokesToFetch.split(",");
        for (String joke : jokesArr) {
            IJokeFetcher j = getJokeFetcher(joke);
            if(j != null)
                jokes.add(j);
        }
        return jokes;
    }
    
    private IJokeFetcher getJokeFetcher(String joke){
        switch (joke) {
            case "EduJoke":            
                return new EduJoke();
            case "ChuckNorris":
                return new ChuckNorris();
            case "Moma":
                return new Moma();
            case "Tambal":
                return new Tambal();
        }
        return null;
    }
}
