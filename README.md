# spring-instance-selector

###Usage:

```java
package com.thedevpiece.sis;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public interface Service {
    String getName();
}
```

```
package com.thedevpiece.sis;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ServiceType(name = "service_a")
public class ServiceA implements Service {
    @Override
    public String getName() {
        return "service_a";
    }
}
```

```
package com.thedevpiece.sis;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ServiceType(name = "service_b")
public class ServiceB implements Service {
    @Override
    public String getName() {
        return "service_b";
    }
}
```

```
package com.thedevpiece.sis;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class ServiceTypeQualifier extends QualifierLiteral<ServiceType> implements ServiceType {
    private String name;

    public ServiceTypeQualifier(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
```

```
package com.thedevpiece.sis;

import org.springframework.stereotype.Component;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Component
public class ServiceSample {
    @Factory
    private Instance<Service> services;

    public String getName(String type){
        return services.select(new ServiceTypeQualifier(type)).get().getName();
    }
}
```

