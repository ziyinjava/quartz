import org.quartz.simpl.RAMJobStore;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lidahai.job.ClusterQuartz;


public class Test
{
    public static void main(String[] args)
    {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        ClusterQuartz clusterQuartz = (ClusterQuartz)context.getBean("clusterQuartz");
        clusterQuartz.printUserInfo();

        while (true)
        {

        }
    }
}
