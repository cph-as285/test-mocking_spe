/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import static org.mockito.BDDMockito.given;
import org.mockito.Matchers;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import testex.jokefetching.ChuckNorris;
import testex.jokefetching.EduJoke;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Moma;
import testex.jokefetching.Tambal;

/**
 *
 * @author Alexander
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    private JokeFetcher jokeFetcher;
    @Mock
    IDateFormatter ifMock;
    @Mock
    IFetcherFactory factory;
    @Mock
    Moma moma;
    @Mock
    ChuckNorris chuck;
    @Mock
    EduJoke edu;
    @Mock
    Tambal tambal;

    @Before
    public void setup() {
        List<IJokeFetcher> fetchers = Arrays.asList(edu, chuck, moma, tambal);
        when(factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
        List<String> types = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
        when(factory.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher(ifMock, factory);
    }

    @Test
    public void testGetAvailableTypes() {
        JokeFetcher jf = new JokeFetcher(ifMock, factory);

        assertThat(jf.getAvailableTypes(), hasItems("EduJoke", "ChuckNorris", "Moma", "Tambal"));
    }

    @Test
    public void testGetAvailableTypesSize() {
        JokeFetcher jf = new JokeFetcher(ifMock, factory);

        assertThat(jf.getAvailableTypes().size(), equalTo(4));
    }

    @Test
    public void checkIfValidToken() {
        JokeFetcher jf = new JokeFetcher(ifMock, factory);
        assertThat(jf.isStringValid("EduJoke,ChuckNorris"), equalTo(true));
    }

    @Test
    public void checkIfValidTokenInvalid() {
        JokeFetcher jf = new JokeFetcher(ifMock, factory);
        assertThat(jf.isStringValid("edu_prog,moma"), equalTo(false));
    }

    @Test
    public void testTimeZoneString() throws JokeException {
        given(ifMock.getFormattedDate(eq("Europe/Copenhagen"))).willReturn("17 feb. 2017 10:56 AM");
        JokeFetcher jf = new JokeFetcher(ifMock, factory);
        assertThat(jf.getJokes("moma", "Europe/Copenhagen").timeZoneString, equalTo("17 feb. 2017 10:56 AM"));
        verify(ifMock, times(1)).getFormattedDate(Matchers.anyObject());
    }
    
    @Test
    public void testGetJokes() throws JokeException{
        Joke mockJoke = new Joke("Joke", "Reference");
        //given(ifMock.getFormattedDate("Europe/Copenhagen")).willReturn("17 feb. 2017 10:56 AM");
        given(chuck.getJoke()).willReturn(mockJoke);        
        given(edu.getJoke()).willReturn(mockJoke);
        given(tambal.getJoke()).willReturn(mockJoke);       
        given(moma.getJoke()).willReturn(mockJoke);

        JokeFetcher jf = new JokeFetcher(ifMock, factory);
        
        List<Joke> jokes = jf.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen").jokes;
        
        assertThat(jokes, hasSize(4));
        
        jokes.forEach(joke -> {
            assertThat(joke.getJoke(), equalTo(mockJoke.getJoke()));            
            assertThat(joke.getReference(), equalTo(mockJoke.getReference()));
        });
        
        verify(chuck, times(1)).getJoke();        
        verify(edu, times(1)).getJoke();
        verify(tambal, times(1)).getJoke();
        verify(moma, times(1)).getJoke();
    }
}
