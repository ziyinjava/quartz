import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TraceBeanPostProcessor implements BeanPostProcessor {
	
 @Override
 public Object postProcessAfterInitialization(Object bean, String beanName)
   throws BeansException {
  System.out.println("  postProcessAfterInitialization --");
  return bean;
 }
 
 @Override
 public Object postProcessBeforeInitialization(Object bean, String beanName)
   throws BeansException {
  System.out.println("  postProcessBeforeInitialization --" + "Bean '"
    + beanName + "' created : " + bean.toString());
  return bean;
 }
}
