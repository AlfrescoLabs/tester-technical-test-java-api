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

Alfresco Content Services 5.2.2 or above

Search Services 1.2.0 or above

Docker (Necessary to create a new Test Environment using the instructions below)

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


