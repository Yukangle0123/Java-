
import com.dao.IUserDao;
import com.domain.QueryVo;
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
import java.util.Date;
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
    public void testSelectAll(){
        List<User> users = iUserDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("于大大");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("陕西西安");
        System.out.println("插入前："+user);
        iUserDao.addUser(user);
        System.out.println("插入后：" + user);
    }
    @Test
    public void testSelectById(){
        User user = iUserDao.selectById(50);
        System.out.println(user);
    }
    @Test
    public void deleteTest(){
        int b = iUserDao.deleteById(50);
        System.out.println(b);
    }
    @Test
    public void updateTest(){
        User user = new User();
        user.setId(51);
        user.setUsername("于huan");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("陕西西安");
        System.out.println(iUserDao.updateUser(user));
    }
    @Test
    public void selectByName(){
        List<User> users = iUserDao.selectByName("于");
        for(User user : users){
            System.out.println(user);
        }
    }
    @Test
    public void selectTotalTest(){
        System.out.println(iUserDao.selectTotal());
    }
    @Test
    public void selectByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%于%");
        vo.setUser(user);
        List<User> users = iUserDao.selectByVo(vo);
        for(User user1 : users){
            System.out.println(user1);
        }
    }

}
