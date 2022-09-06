package com.jap.ticketing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BusServiceTest {
    BusService busService;
    BusServiceImpl busServiceImpl;

    String fileName="sample.csv";

    @Before
    public void setUp(){
        busServiceImpl=new BusServiceImpl();
        busService=new BusService("KIAS-12/5","KIAS-12UP",57,14,11359,39,"01/09/2018","02:25:52",234,38.4);
    }
    @After
    public void tearDown(){
        busService=null;
        busServiceImpl=null;
    }
    @Test
    public void readFile(){
        List<BusService> actual=busServiceImpl.readFile(fileName);
        assertEquals("Bus service record are not returned correctly",49,actual.size());
    }

    @Test
    public void getdistanceTravelled(){
        List<BusService> actual=busServiceImpl.readFile(fileName);
        assertEquals(49.5,busServiceImpl.getdistanceTravelled(actual).get(0).getTravelled_KM(),0);
    }
    @Test
    public void getTotalAmount(){
        List<BusService> actual=busServiceImpl.readFile(fileName);
        assertEquals(10348,busServiceImpl.getTotalAmount(actual),0);

    }

}
