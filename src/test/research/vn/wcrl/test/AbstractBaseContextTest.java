package research.vn.wcrl.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author hcnlinh
 */
@ContextConfiguration(locations = {"/webcrawler-context.xml"})
@TransactionConfiguration
@Transactional
public abstract class AbstractBaseContextTest
{

}
