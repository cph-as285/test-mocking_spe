/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import static com.jayway.restassured.RestAssured.when;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.hamcrest.Matchers.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Alexander
 */
@RunWith(MockitoJUnitRunner.class)
public class DateFormatterTest {
    
    @Test
    public void testValidInput() throws JokeException {
        DateFormatter df = new DateFormatter(new Date(2017, 11, 23));
        assertThat(df.getFormattedDate("Europe/Copenhagen"), equalTo("23 dec. 3917 12:00 AM"));
    }
    
    @Test(expected = JokeException.class)
    public void testInvalidInput() throws JokeException{
        DateFormatter df = new DateFormatter(new Date(2017, 11, 23));
        df.getFormattedDate("Something not valid");
    }
    
}
