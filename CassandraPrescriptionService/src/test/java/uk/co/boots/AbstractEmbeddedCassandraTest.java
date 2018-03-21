package uk.co.boots;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners(listeners= {
		CassandraUnitDependencyInjectionTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class
})
@EmbeddedCassandra(timeout = 120000L)
@CassandraDataSet(value="prescriptions-test.cql")
@ActiveProfiles("test")
public abstract class AbstractEmbeddedCassandraTest {

}
