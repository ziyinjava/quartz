import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifecycleTest {
 
 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  String[] beanFiles = new String[]{"beans.xml"};
  ApplicationContext appCxt = new ClassPathXmlApplicationContext(beanFiles);
  
  ((AbstractApplicationContext)appCxt).registerShutdownHook();
  //(ClassPathXmlApplicationContext)appCxt)
 }
}
