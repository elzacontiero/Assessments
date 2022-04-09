# Notes

Steps to reproduce

## Create Maven project

```
mvn archetype:generate \
 -DgroupId=org.elzacontiero.m3assessments.vendingmachinespring \
 -DartifactId=Assessment5VendingMachineSpring \
 -DarchetypeArtifactId=maven-archetype-quickstart \
 -DarchetypeVersion=1.4 \
 -DinteractiveMode=false
```

**And add dependencies**:

```xml
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.17</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <version>2.6.5</version>
    </dependency>
```
From [link on Engage](https://academy.engagelms.com/mod/page/view.php?id=71663&noredirect=1), 
follow dependencies, but updated the org.springframework to 
latest version from [here](https://mvnrepository.com/artifact/org.springframework/spring-context/5.3.17).


## class App

We change the class App to instead of creating a new VendingMachine ourselves, 
we instruct Spring to create as a command line application like below:

```java
import org.springframework.boot.SpringApplication;

public class App {
    public static void main(String[] args) {
        SpringApplication.run(VendingMachine.class, args);
    }
}
```
No annotations needed here.

Reference: https://www.baeldung.com/spring-boot-console-app 

## class VendingMachine

[Engage link](https://academy.engagelms.com/mod/page/view.php?id=71663&noredirect=1)

Use annotation `@SpringBootApplication` at class level. 
See [doc](https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html).

Change class signature to 
```java
@SpringBootApplication
public class VendingMachine implements CommandLineRunner {
```
Reference https://www.baeldung.com/spring-boot-console-app

`@Autowire` for the most important constructor at that point.

Change `run()` signature to:
```java
    @Override
    public void run(String... args) {
```

Add constructor as below. The original code used composition on VendingMachine to 
internally instantiate an InventoryDao, but now InventoryDaoImpl is a `@Component`, 
and so it gets magically injected into the constructor below.

```java
    InventoryDao inventory;

    // Dependency injection here
    public VendingMachine(InventoryDao inventory) {
        this.inventory = inventory;
    }
```

As a result, we don't have to manually instantiate the objects with `new`.

## class InventoryDaoFileImpl

Annotate the class with `@Component`

