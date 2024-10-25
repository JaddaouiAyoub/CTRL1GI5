package com.ejbserver;

import javax.ejb.Remote;

@Remote
public interface DateManager {
    String returnDate();
}
