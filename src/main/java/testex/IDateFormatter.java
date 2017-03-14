/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Date;

/**
 *
 * @author Alexander
 */
public interface IDateFormatter {
    String getFormattedDate(String timeZone) throws JokeException;
}
