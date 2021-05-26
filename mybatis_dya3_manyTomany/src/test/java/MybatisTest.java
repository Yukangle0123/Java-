

import com.dao.IRoleDao;

import com.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

public class MybatisTest {
    InputStream in = null;
    SqlSession session = null;
    IRoleDao iRoleDao = null;
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        iRoleDao = session.getMapper(IRoleDao.class);
    }
    @After
    public void close() throws IOException {
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public void testFindAll(){
        List<Role> all = iRoleDao.findAll();
        for(Role role : all){
            System.out.println(role);
        }
    }
    public int[] scores = new int[]{1,5,10,25};
    public int waysToChange(int n) {
        int[][] dp = new int[5][n+1];
        for(int i = 1; i <= 4; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= 4; i++){
            for(int j = 1;j <= n;j++){
                if(j - scores[i] < 0){
                    dp[i][j] = dp[i - 1][j] % 1000000007;
                }else{
                    dp[i][j] = (dp[i][j - scores[i - 1]] + dp[i - 1][j])% 1000000007;
                }
            }
        }
        return dp[4][n];
    }
    public int waysToChange2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int s : scores){
            for(int i = 1; i <= n;i++){
                if(i - scores[i] >= 0){
                    dp[i] = dp[i - scores[i]] +dp[i];
                }
            }
        }
        return dp[n];
    }
}
