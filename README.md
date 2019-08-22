# Setup
The automated test project requires the pre-requisites listed below and a running instance of Alfresco Content Repository (ACS) and Search Services at the least.
This will be already setup for you, if you are using an Alfresco provided pc for the technical test.

Please check that you are able to:
1. Compile the project
2. Access the test environment before you move on to the <Challenge> section of the Technical Test.

Test Environment can be accessed on:
1. http://172.29.100.166:8080/alfresco
2. https://search-cdata-nv.dev.alfresco.me/alfresco

<Bring the Test Environment up> section below provides instructions on how a new test environment can be started, when an ACS instance isn't available.

# Prerequisites
Java 11

Maven 3.2.0

Docker: Docker is required to be setup on the machine if it's necessary to create a new Test Environment using the instructions below.
Create an environment only if one isn't already available.

Test Environment with: 
1. Alfresco Content Services 5.2.2 or above 
2. Search Services 1.2.0 or above

is required to run the tests. 
If the test environments (URLs) provided above are not availale / accessible, you would need to set one up using docker and instructions below.

# Maven Setup

1. Download the latest apache-maven-*-bin.zip file
2. Unzip it somewhere on your disk
3. Add <InstallDir>/bin to your PATH (You don't have to worry about M2_HOME environment variable.)

Test the install using: mvn -version

## Configure Maven for Alfresco

First, test that you have access by logging into to: artifacts.alfresco.com with your LDAP / user account.

Then, create the file ~/.m2/settings.xml
On Windows : C:\Users\<username>\.m2\settings.xml) with the following content:

```
<settings>
  <profiles>
    <profile>
      <id>alfresco-internal</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <id>alfresco-internal</id>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
          <name>Alfresco Internal Repository</name>
          <url>https://artifacts.alfresco.com/nexus/content/groups/internal/</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>alfresco-internal</id>
          <name>Alfresco Internal Repository</name>
          <url>https://artifacts.alfresco.com/nexus/content/groups/public</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>alfresco-internal</id>
      <username>Your_LDAP_Login</username>
      <password>Your_LDAP_Password</password>
    </server>
  </servers>

</settings>
```

# Bring the Test Environment up

1. ACS + Search Services Healthcheck: Please ensure that Alfresco Content Repository (ACS) admin console > Search Services uses the right port and shows tracking status.
This could be setup using the packaging > docker > docker-compose.yml
In a command window, cd to the packaging > docker directory and Run docker-compose up
This should start an instance of ACS Repository with Share and Search Services.
    
2. Search services (Solr) Healthcheck can be performed using solr admin console at:

```
    <protocol>://<repo-host-ip>:<solr-port>/solr/#

    e.g. http://localhost:8083/solr/#
```

# Compile the project
Project can be built using the instructions below. 
Ensure that default.properties have the appropriate settings. e.g.
If the environment to be used for tests is: https://search-cdata-nv.dev.alfresco.me/alfresco

Check that the default.properties file is been updated with the following settings.

```
alfresco.scheme=https
alfresco.server=search-cdata-nv.dev.alfresco.me
alfresco.port=443

```

`mvn clean install -DskipTests`

# Run the tests
`mvn clean install`

# Run a specific class of tests
`mvn clean install -Dtest=<class>`

e.g.

`mvn clean install -Dtest=FixMeTest`

# Search API Reference:
https://api-explorer.alfresco.com/api-explorer/#!/search/search

#AFTS Search Documentation:
Conjunction (AND Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-conjunct.html
Disjunction (OR Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-disjunct.html
Negation (NOT Operator): https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-negate.html

# Automating tests
Sample test classes: SetupTest and FixMeTest have been provided.
This should provide an example of data preperation methods and how search api can be tested.
FixMeTest includes some tests for testing Range search, as documented here: https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-ranges.html

# Challenge
There are 3 challenges:
1. Run the FixMeTest test and analyse, fix any failures that you might come across.
2. Using the provided AFTS references above, design (note down, pseudocode) tests to test conjunction, disjunction and negation opertors in a search query. 
3. Implement some of these in SampleTest class. Data preparation example is provided and can be used or amended as per your needs.


