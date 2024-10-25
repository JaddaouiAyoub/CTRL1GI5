package com.ejbserver;

import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class DateManagerImpl implements DateManager {

    @Override
    public String returnDate() {
        return new Date().toString();
    }
}

