# Technical Specifications
## Program Flow
### Interceptor
The TenantIdInterceptors PreHandle method executes logic before the request is handled. 
So before anything happens the tenantId is retrieved from the request and is set in the ThreadLocalStorage.
ThreadLocal is used so that the context is bound to the currently executing thread.
After the request is completed the tenantId is erased from the thread because Spring may reuse the thread in the thread pool,
and you don't want to leak this information.

![PlantUML model](http://www.plantuml.com/plantuml/png/7Oun3eCm34LtJW470E-Tc3ls1Y5OO3Mn8-TR5RSlxVIszzxQaHnHop3XvCeZx1ecqLnYv_0oda3h3o9Fd-KGdB55Py-cOCMIhT8pYYHz1UStzUQYnxoPOVwRsUSQ1GBMfFcYcaHfjzpfRf8es3Ctc2_jDx6oxZU_)

### AbstractRoutingDataSource
During the handling of the request the AbstractRoutingDataSource returns the appropriate DataSource to utilize, using the tenantId stored in the ThreadLocalStorage.

![PlantUML model](http://www.plantuml.com/plantuml/png/7OunReL034JxVmeu0BXFHP4g9Jc1CHQiiciZxrWHjm_vrLJptbiwE92jlaQSsdI0tw-ncDJSDQCE9t3r3wBWt_aeE7FBhY5kKCCitkWhQs5RK-M7-XN5Zcbpn_GcQqmjAmhKsEIcniLeT-dqE8ctZc31weddzydWRyyGdQ__f4LjV_OF)

### Tenant data source configuration
A developer using this project is able to set multiple data sources using the application.yml file as described in the README.
The data sources defined in the application.yml file are mapped to the TenantProperties class using the @Configuration and @ConfigurationProperties annotations.
TenantProperties contains logic to create and return actual DataSource objects based the input from the application.yml file.
<br>
In the MultitenancyAutoConfiguration class a DataSource Bean is defined. This DataSource contains all the user defined data sources in a Map.
Spring framework automatically uses it for database operations.

![PlantUML model](http://www.plantuml.com/plantuml/png/7SunheCm48JXdbF00M3xLr6_AieDZBq2A_QkjPvDnEr3gAOPwVlNZc3mMeO91rSUeUSmPOd54xkLyG1Q_oEoy5dsZCCtxsnH1InOebRwzvA3F9tZYweNPB04YUVScsNPvqqLyysmKU8t5ssKD7QwMBhFU9WsDcJkI_l5h2pfsYy0)