package research.vn.wcrl.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author hcnlinh
 */
@ContextConfiguration(locations = {"/webcrawler-context-test.xml"})
@TransactionConfiguration
@Transactional
public abstract class AbstractBaseContextTest extends AbstractTransactionalTestNGSpringContextTests
{

}
