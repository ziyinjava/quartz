import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TestBean implements BeanNameAware, BeanFactoryAware,
  ApplicationContextAware, InitializingBean {
	
 private TestBean() {
  System.out.println("Construct");
 }
 
 @PostConstruct
 public void postConstruct() {
  System.out
    .println("@PostConstruct annotation called, org.springframework.context.annotation.CommonAnnotationBeanPostProcessor registor requried.");
 }
 
 @PreDestroy
 public void preDestroy() {
  System.out
    .println("@PreDestroy annotation called, org.springframework.context.annotation.CommonAnnotationBeanPostProcessor registor requried.");
 }
 
 public void initMethod() {
  System.out.println("  configed init Method in spring file called");
 }
 
 public void destroyMethod() {
  System.out.println("  configed destory Method in spring file called");
 }
 
 private String name;
 
 public String getName() {
  System.out.println("getName - " + name);
  return name;
 }
 
 public void setName(String name) {
  System.out.println("setName - " + name);
  this.name = name;
 }
 
 @Override
 public void afterPropertiesSet() throws Exception {
  System.out
    .println("afterPropertiesSet called, bean need implements InitializingBean");
 }
 
 ApplicationContext applicationContext;
 
 @Override
 public void setApplicationContext(ApplicationContext applicationContext)
   throws BeansException {
  this.applicationContext = applicationContext;
  System.out
    .println(" setApplicationContext called, bean need implements ApplicationContextAware");
 }
 
 BeanFactory beanFactory;
 
 @Override
 public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
  this.beanFactory = beanFactory;
  System.out
    .println(" setBeanFactory called, bean need implements setBeanFactoryAware");
 }
 
 String beanName;
 @Override
 public void setBeanName(String beanName) {
  this.beanName = beanName;
  System.out
    .println(" setBeanName called, bean need implements setBeanNameAware");
 }

}
