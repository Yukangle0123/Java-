import com.dao.IUserDao;
import com.domain.QueueVo;
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
    IUserDao iUserDao = null;
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        iUserDao = session.getMapper(IUserDao.class);
    }
    @After
    public void close() throws IOException {
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setUsername("小二王");
        user.setSex("男");
        List<User> userByCondition = iUserDao.findUserByCondition(user);
        for(User user1 : userByCondition){
            System.out.println(user1);
        }
    }
    @Test
    public void testFindInIds(){
        QueueVo vo = new QueueVo();
        ArrayList<Integer> list =new ArrayList<Integer>();
        list.add(51);
        list.add(52);
        list.add(53);
        vo.setIds(list);
        List<User> inIds = iUserDao.findInIds(vo);
        for(User u : inIds){
            System.out.println(u);
        }

    }
}
