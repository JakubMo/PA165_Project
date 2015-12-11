/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.data;

import cz.muni.fi.pa165.project.dao.EmployeeDao;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests data loader
 * 
 * @author jakub
 */
@ContextConfiguration(classes={CarRentalDataConfiguration.class})
@Transactional
public class LoadDataFacadeImplTest extends AbstractTestNGSpringContextTests{
    
    final static Logger log = LoggerFactory.getLogger(LoadDataFacadeImplTest.class);
    
    @Autowired
    public EmployeeDao employeeDao;
    
    @Autowired
    public LoadDataFacade loadDataFacade;

    @BeforeClass
    public void initialLoad() throws IOException {
        log.info("Start of test. Loading data.");
        loadDataFacade.load();
        log.info("Data loaded");
    }
    
    @Test
    public void employeeLoadedTest() {
        Assert.assertEquals(employeeDao.getAll().size(), 4, "number of employees is wrong");
    }
    
}
