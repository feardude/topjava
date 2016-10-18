package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;

public class AbstractJpaUserServiceTest extends AbstractUserServiceTest{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private JpaUtil jpaUtil;

    @Before
    public void setUp() {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }

}
