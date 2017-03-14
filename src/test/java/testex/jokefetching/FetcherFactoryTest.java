/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Alexander
 */
@RunWith(MockitoJUnitRunner.class)
public class FetcherFactoryTest {
    
    @Mock EduJoke eduJoke;
    @Mock ChuckNorris chuckNorris;
    @Mock Moma moma;
    @Mock Tambal tambal;
    
    
    @Test
    public void testFetcher(){
        FetcherFactory factory = new FetcherFactory();
        List<IJokeFetcher> result = factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal");
        
        result.forEach(fetcher -> {
            assertThat(fetcher, instanceOf(IJokeFetcher.class));
        });
    }
}
