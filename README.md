# docker__maven_plugin
to build docker image only:

       <build>
              <plugins>
                  <plugin>
                      <groupId>com.spotify</groupId>
                      <artifactId>dockerfile-maven-plugin</artifactId>
                      <version>1.4.13</version>
                      <executions>
                          <execution>
                              <id>default</id>
                              <goals>
                                  <goal>build</goal>
                                  <goal>push</goal>
                              </goals>
                          </execution>
                      </executions>
                      <configuration>
                          <repository>${project.artifactId}</repository>
                          <tag>latest</tag>
                          <buildArgs>
                              <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                          </buildArgs>
                      </configuration>
                  </plugin>
              </plugins>
              <finalName>${project.artifactId}</finalName>
          </build>

to build and push docker image to dockerhub:

         <build>
                <plugins>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                    </plugin>
                    <plugin>
                        <groupId>com.spotify</groupId>
                        <artifactId>dockerfile-maven-plugin</artifactId>
                        <version>1.4.13</version>
                        <configuration>
                            <!--added useMavenSettingAuth tag and set to be true to read the credential from settings.xml under ~/.m2 folder-->
                            <useMavenSettingsForAuth>true</useMavenSettingsForAuth>
                            <!-- ***********************************************************************************************************-->
                            <repository>thiethaa/${project.artifactId}</repository>
                            <!--change docker image tag -->
                            <tag>${project.version}</tag>
                            <!--*************************-->
                            <buildArgs>
                                <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                            </buildArgs>
                        </configuration>
                        <executions>
                            <execution>
                                <id>default</id>
                                <phase>install</phase>
                                <goals>
                                    <goal>build</goal>
                                    <goal>push</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
                <finalName>${project.artifactId}</finalName>
            </build>
            
 .travis.yaml file use this :

        language: java
        jdk:
          - openjdk8
        
        # Pre-testing installs
        install:
          - echo "nothing to install.."
        
        # Scripts to be run such as tests
        before_script:
          - echo "before script is nothiiiiing...."
        
        script:
          - ./mvnw clean install -Drevision=$(git rev-parse --short HEAD)
        
        #docker push
        after_success:
          - echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
          - docker tag docker-maven-plugin $DOCKER_USERNAME/docker-maven-plugin
          - docker push $DOCKER_USERNAME/docker-maven-plugin