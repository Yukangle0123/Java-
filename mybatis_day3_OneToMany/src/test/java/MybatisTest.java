import com.dao.IAccountDao;

import com.domain.Account;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {
    InputStream in = null;
    SqlSession session = null;
    IAccountDao iAccountDao = null;
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        iAccountDao = session.getMapper(IAccountDao.class);
    }
    @After
    public void close() throws IOException {
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public void testFindAll(){
        List<User> all = iAccountDao.findAll();
        for(User user : all){
            System.out.println(user);
        }
    }

}
