# Technical Excercise

There are 3 exercises:

1. Run the 'FixMeTest' and analyse and fix any failures that you might come across.
2. 'Search References' section below provides details about a search feature, with an example of search conjunction, disjunction and negation opertors. Using the provided documentation, design tests to test conjunction, disjunction and negation operators in a search term. You can use any format for writing tests and note down designed tests electronically or using Pen and paper. 
3. Implement some of these tests in 'SampleTest' class. Data preparation example is provided and can be used or amended as per your needs. Refer to 'FixMeTest' for example implementation of a test that is written to test Search for Ranges.

# Setup
To test the project setup works for you, you can run 'SetupTest'. Before you run it, you need to be able to:
1. Access the Test Environment: See 'Pre-configured Test Enviroments' section
2. Configure and compile the test Project: See 'Configure the project properties' section

# Pre-configured Test Enviroments

The automated test project requires the 'Pre-requisites' listed below and a running instance of: 
1. Alfresco Content Services 5.2.2 or above 
2. Search Services 1.2.0 or above

This will be already setup for you, if you are using an Alfresco provided pc for the technical test.

Please check that you are able to access the test environment(s) already created for you on:
1. http://172.29.100.166:8080/alfresco (Requires VPN connection to Maidenhead or connection via maidenhead office) OR
2. https://search-cdata-nv.dev.alfresco.me/alfresco

If you are unable to access the URLs above, you would need to set a test environment up using docker. Please see the section 'Prepare Test Environment' for more information.
If you are able to access the URLs above, you can move on to the section 'Configure the project properties' and 'Compile the Project' section below.

# Pre-requisites for running the Project on your own PC.

These should be setup for you already if you are using an Alfresco provided pc. You can move onto 'Configure the project properties' section.
If you are using your own pc, you would need to setup:

- Java 11
- IDE of your choice
- Maven 3.2.0: See: 'Setup Maven' section for more information on setting up Maven on your machine
- Access to Alfresco internal artifacts: See 'Configure Maven for Alfresco' section before for more information on configuring access to Alfresco Internal Artifacts.
- Docker: Only required if you are unable to access provided test environments and need to create a new test environment.

### Setup Maven

1. Download the latest apache-maven-*-bin.zip file
2. Unzip it somewhere on your disk
3. Add `<InstallDir>/bin` to your PATH (You don't have to worry about M2_HOME environment variable.)

Test the install using: mvn -version

### Configure Maven for Alfresco

First, test that you have access by logging into to: artifacts.alfresco.com with your LDAP / user account.
If not, request access by raising an IT ticket.

Then, create the file ~/.m2/settings.xml
On Windows : `C:\Users\<username>\.m2\settings.xml` with the following content:

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

# Prepare Test Environment

1. ACS + Search Services Healthcheck: 
Please ensure that Alfresco Content Repository (ACS) admin console > Search Services uses the right port and shows tracking status.
This could be setup using the packaging > docker > docker-compose.yml
In a command window, run the following commands:

```
cd <technical-test-home>
cd test-java-api\packaging 
docker-compose up
```

This should start an instance of ACS Repository with Share and Search Services.
Check that ACS repository is available on:

e.g. http://localhost:8080/alfresco
    
2. Search services (Solr) Healthcheck can be performed using solr admin console at:

```
    <protocol>://<repo-host-ip>:<solr-port>/solr/#

    e.g. http://localhost:8083/solr/#
```

# Configure the project properties

Check that default.properties have the appropriate settings. e.g.
If the environment to be used for tests is: https://search-cdata-nv.dev.alfresco.me/alfresco

Check that the default.properties file is been updated with the following settings.

```
alfresco.scheme=https
alfresco.server=search-cdata-nv.dev.alfresco.me
alfresco.port=443
```

The technical test project includes 'SetupTest', that can be run to see if the project is configured well and ready to use.

Additionally, FixMeTest have been provided, that includes a few tests that pass and some that fail.
FixMeTest includes some tests for testing Range search, as documented here: https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-ranges.html
This should provide an example of data preperation methods and how search api can be tested. 
This code can be used / referred to while implementing automating tests in 'SampleTest'

# Compile the project

`mvn clean install -DskipTests`

# Run the tests
`mvn clean install`

# Run a specific class of tests
`mvn clean install -Dtest=<class>`

e.g.

`mvn clean install -Dtest=FixMeTest`

# Search References

Search API

```
https://api-explorer.alfresco.com/api-explorer/#!/search/search
```

Search with Conjunction (And), Disjunction (Or) and Negation (Not) operators:

```
1. Conjunction: https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-conjunct.html
2. Disjunction: https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-disjunct.html
3. Negation: https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-negate.html
```

Search for Ranges:

```
1. https://docs.alfresco.com/5.2/concepts/rm-searchsyntax-ranges.html
```